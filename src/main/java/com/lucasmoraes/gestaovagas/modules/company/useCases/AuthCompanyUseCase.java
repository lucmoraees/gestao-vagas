package com.lucasmoraes.gestaovagas.modules.company.useCases;

import com.auth0.jwt.algorithms.Algorithm;
import com.lucasmoraes.gestaovagas.modules.company.DTO.AuthCompanyDTO;
import com.lucasmoraes.gestaovagas.modules.company.DTO.AuthCompanyResponseDTO;
import com.lucasmoraes.gestaovagas.modules.company.repositories.CompanyRepository;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

@Service
public class AuthCompanyUseCase {
    @Value("${security.token.secret}")
    private String secretKey;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthCompanyResponseDTO execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
        var company = this.companyRepository.findByUsername(authCompanyDTO.getUsername());

        if (!company.isPresent()) {
            throw new UsernameNotFoundException("Company not found!");
        }

        Boolean passwordMatchs = this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.get().getPassword());

        if (!passwordMatchs) {
            throw new AuthenticationException("Username/password incorrect!");
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        var expiresIn = Instant.now().plus(Duration.ofHours(2));

        String token = JWT.create().withIssuer("lucasmoraes")
                .withExpiresAt(expiresIn)
                .withSubject(company.get().getId().toString())
                .withClaim("roles", Arrays.asList("COMPANY"))
                .sign(algorithm);

        var authCompanyResponseDTO = AuthCompanyResponseDTO.builder()
                .access_token(token)
                .expires_in(expiresIn.toEpochMilli())
                .build();

        return authCompanyResponseDTO;
    }
}
