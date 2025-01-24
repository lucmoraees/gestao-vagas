package com.lucasmoraes.gestaovagas.modules.candidate.useCases;

import com.lucasmoraes.gestaovagas.exceptions.JobNotFoundException;
import com.lucasmoraes.gestaovagas.exceptions.UserNotFoundException;
import com.lucasmoraes.gestaovagas.modules.candidate.entities.ApplyJobEntity;
import com.lucasmoraes.gestaovagas.modules.candidate.entities.CandidateEntity;
import com.lucasmoraes.gestaovagas.modules.candidate.repositories.ApplyJobRepository;
import com.lucasmoraes.gestaovagas.modules.candidate.repositories.CandidateRepository;
import com.lucasmoraes.gestaovagas.modules.company.entities.JobEntity;
import com.lucasmoraes.gestaovagas.modules.company.repositories.JobRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ApplyJobEntityCandidateUseCaseTest {
    @InjectMocks
    private ApplyJobCandidateUseCase applyJobCandidateUseCase;

    @Mock
    private CandidateRepository candidateRepository;

    @Mock
    private JobRepository jobRepository;

    @Mock
    private ApplyJobRepository applyJobRepository;

    @Test
    @DisplayName("Should not be able to apply job with candidate not found")
    public void should_not_be_able_to_apply_job_with_candidate_not_found() {
        try {
            applyJobCandidateUseCase.execute(null, null);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(UserNotFoundException.class);
        }
    }

    @Test
    @DisplayName("Should not be able to apply job with job not found")
    public void should_not_be_able_to_apply_job_with_job_not_found() {
        var candidateId = UUID.randomUUID();

        var candidate = new CandidateEntity();
        candidate.setId(candidateId);

        when(candidateRepository.findById(candidateId)).thenReturn(Optional.of(candidate));

        try {
            applyJobCandidateUseCase.execute(candidateId, null);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(JobNotFoundException.class);
        }
    }

    @Test
    @DisplayName("Should be able to apply job")
    public void should_be_able_to_apply_job() {
        var candidateId = UUID.randomUUID();
        var jobId = UUID.randomUUID();

        var applyJob = new ApplyJobEntity();
        applyJob.setCandidateId(candidateId);
        applyJob.setJobId(jobId);

        var applyJobCreated = new ApplyJobEntity();
        applyJobCreated.setId(UUID.randomUUID());
        applyJobCreated.setCandidateId(candidateId);
        applyJobCreated.setJobId(jobId);


        when(candidateRepository.findById(candidateId)).thenReturn(Optional.of(new CandidateEntity()));
        when(jobRepository.findById(jobId)).thenReturn(Optional.of(new JobEntity()));
        when(applyJobRepository.save(applyJob)).thenReturn(applyJobCreated);

        var result = applyJobCandidateUseCase.execute(candidateId, jobId);

        assertThat(result).hasFieldOrProperty("id");
        assertNotNull(result.getId());
    }
}
