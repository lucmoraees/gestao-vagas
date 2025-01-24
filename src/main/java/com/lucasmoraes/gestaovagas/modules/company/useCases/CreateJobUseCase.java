package com.lucasmoraes.gestaovagas.modules.company.useCases;

import com.lucasmoraes.gestaovagas.exceptions.CompanyNotFoundException;
import com.lucasmoraes.gestaovagas.modules.company.entities.JobEntity;
import com.lucasmoraes.gestaovagas.modules.company.repositories.CompanyRepository;
import com.lucasmoraes.gestaovagas.modules.company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreateJobUseCase {
    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public JobEntity execute(JobEntity jobEntity) {
        companyRepository.findById(jobEntity.getCompanyId()).orElseThrow(() -> {
            throw new CompanyNotFoundException();
        });

        return this.jobRepository.save(jobEntity);
    }
}
