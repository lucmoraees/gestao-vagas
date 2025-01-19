package com.lucasmoraes.gestaovagas.modules.candidate.useCases;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.lucasmoraes.gestaovagas.modules.candidate.DTO.AuthCandidateRequestDTO;
import com.lucasmoraes.gestaovagas.modules.candidate.DTO.AuthCandidateResponseDTO;
import com.lucasmoraes.gestaovagas.modules.candidate.repositories.CandidateRepository;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

@Service
public class AuthCandidateUseCase {
    @Value("${security.token.secret.candidate}")
    private String secretKey;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthCandidateResponseDTO execute(AuthCandidateRequestDTO authCandidateRequestDTO) throws AuthenticationException {
        var candidate = this.candidateRepository.findByUsername(authCandidateRequestDTO.username());

        if (!candidate.isPresent()) {
            throw new UsernameNotFoundException("Username/password incorrect!");
        }

        var passwordMatch = passwordEncoder.matches(authCandidateRequestDTO.password(), candidate.get().getPassword());

        if(!passwordMatch) {
            throw new AuthenticationException("Username/password incorrect!");
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        var expiresIn = Instant.now().plus(Duration.ofHours(2));
        String token = JWT.create().withIssuer("lucasmoraes")
                .withExpiresAt(expiresIn)
                .withSubject(candidate.get().getId().toString())
                .withClaim("roles", Arrays.asList("CANDIDATE"))
                .sign(algorithm);

        var authCandidateResponseDTO = AuthCandidateResponseDTO.builder()
                .access_token(token)
                .expires_in(expiresIn.toEpochMilli())
                .build();

        return authCandidateResponseDTO;
    }
}
