package com.lucasmoraes.gestaovagas.modules.company.useCases;

import com.lucasmoraes.gestaovagas.exceptions.UserFoundException;
import com.lucasmoraes.gestaovagas.modules.company.entities.CompanyEntity;
import com.lucasmoraes.gestaovagas.modules.company.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreateCompanyUseCase {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CompanyEntity execute(CompanyEntity companeyEntity) {
        Optional<CompanyEntity> companey = companyRepository
                .findByUsernameOrEmail(companeyEntity.getUsername(), companeyEntity.getEmail());

        if (companey.isPresent()) {
            throw new UserFoundException();
        }

        var password = this.passwordEncoder.encode(companeyEntity.getPassword());
        companeyEntity.setPassword(password);

        return this.companyRepository.save(companeyEntity);
    }
}
