package com.lucasmoraes.gestaovagas.modules.company.repositories;

import com.lucasmoraes.gestaovagas.modules.company.entities.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JobRepository extends JpaRepository<JobEntity, UUID> {
    List<JobEntity> findByDescriptionContainingIgnoreCase(String filter);
    List<JobEntity> findByCompanyId(UUID companyId);
}
