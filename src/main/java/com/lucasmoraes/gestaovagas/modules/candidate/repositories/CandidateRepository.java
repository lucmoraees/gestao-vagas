package com.lucasmoraes.gestaovagas.modules.candidate.repositories;

import java.util.Optional;
import java.util.UUID;

import com.lucasmoraes.gestaovagas.modules.candidate.entities.CandidateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<CandidateEntity, UUID> {
    Optional<CandidateEntity> findByUserNameOrEmail(String username, String email);
}
