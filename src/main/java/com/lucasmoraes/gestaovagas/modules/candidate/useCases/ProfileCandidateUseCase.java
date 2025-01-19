package com.lucasmoraes.gestaovagas.modules.candidate.useCases;

import com.lucasmoraes.gestaovagas.modules.candidate.DTO.ProfileCandidateResponseDTO;
import com.lucasmoraes.gestaovagas.modules.candidate.entities.CandidateEntity;
import com.lucasmoraes.gestaovagas.modules.candidate.repositories.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ProfileCandidateUseCase {
    @Autowired
    CandidateRepository cadCandidateRepository;

    public ProfileCandidateResponseDTO execute(UUID candidateId) {
        var candidate = this.cadCandidateRepository.findById(candidateId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        var candidateDTO = ProfileCandidateResponseDTO.builder()
                .id(candidate.getId())
                .description(candidate.getDescription())
                .name(candidate.getName())
                .username(candidate.getUsername())
                .email(candidate.getEmail())
                .build();

        return candidateDTO;
    }
}
