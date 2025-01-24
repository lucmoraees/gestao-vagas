package com.lucasmoraes.gestaovagas.modules.candidate.repositories;

import com.lucasmoraes.gestaovagas.modules.candidate.entities.ApplyJobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ApplyJobRepository extends JpaRepository<ApplyJobEntity, UUID> {
}
