package com.lucasmoraes.gestaovagas.modules.candidate.useCases;

import com.lucasmoraes.gestaovagas.exceptions.UserFoundException;
import com.lucasmoraes.gestaovagas.modules.candidate.entities.CandidateEntity;
import com.lucasmoraes.gestaovagas.modules.candidate.repositories.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreateCandidateUseCase {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CandidateRepository candidateRepository;

    public CandidateEntity execute(CandidateEntity candidateEntity) {
        Optional<CandidateEntity> candidate = candidateRepository
                .findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail());

        if (candidate.isPresent()) {
            throw new UserFoundException();
        }

        var password = this.passwordEncoder.encode(candidateEntity.getPassword());
        candidateEntity.setPassword(password);

        return this.candidateRepository.save(candidateEntity);
    }
}
