package com.lucasmoraes.gestaovagas.modules.candidate.useCases;

import com.lucasmoraes.gestaovagas.exceptions.JobNotFoundException;
import com.lucasmoraes.gestaovagas.exceptions.UserNotFoundException;
import com.lucasmoraes.gestaovagas.modules.candidate.entities.ApplyJobEntity;
import com.lucasmoraes.gestaovagas.modules.candidate.repositories.ApplyJobRepository;
import com.lucasmoraes.gestaovagas.modules.candidate.repositories.CandidateRepository;
import com.lucasmoraes.gestaovagas.modules.company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ApplyJobCandidateUseCase {
    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ApplyJobRepository applyJobRepository;

    public ApplyJobEntity execute(UUID candidateId, UUID jobid) {
        this.candidateRepository.findById(candidateId)
                .orElseThrow(() -> {
                    throw new UserNotFoundException();
                });

        this.jobRepository.findById(jobid)
                .orElseThrow(() -> {
                    throw new JobNotFoundException();
                });

        var applyJob = ApplyJobEntity.builder()
                .candidateId(candidateId)
                .jobId(jobid)
                .build();

        applyJob = applyJobRepository.save(applyJob);
        
        return applyJob;
    }
}
