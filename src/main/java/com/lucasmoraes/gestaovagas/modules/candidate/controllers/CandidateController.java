package com.lucasmoraes.gestaovagas.modules.candidate.controllers;

import com.lucasmoraes.gestaovagas.modules.candidate.DTO.ProfileCandidateResponseDTO;
import com.lucasmoraes.gestaovagas.modules.candidate.entities.ApplyJobEntity;
import com.lucasmoraes.gestaovagas.modules.candidate.entities.CandidateEntity;
import com.lucasmoraes.gestaovagas.modules.candidate.useCases.ApplyJobCandidateUseCase;
import com.lucasmoraes.gestaovagas.modules.candidate.useCases.CreateCandidateUseCase;
import com.lucasmoraes.gestaovagas.modules.candidate.useCases.ListAllJobsByFilterUseCase;
import com.lucasmoraes.gestaovagas.modules.candidate.useCases.ProfileCandidateUseCase;
import com.lucasmoraes.gestaovagas.modules.company.entities.JobEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/candidate")
@Tag(name = "Candidato", description = "Informações do candidato")
public class CandidateController {
    @Autowired
    private CreateCandidateUseCase createCandidateUseCase;

    @Autowired
    private ProfileCandidateUseCase profileCandidateUseCase;

    @Autowired
    private ListAllJobsByFilterUseCase listAllJobsByFilterUseCase;

    @Autowired
    private ApplyJobCandidateUseCase applyJobCandidateUseCase;

    @PostMapping("")
    @Operation(summary = "Cadastro de candidato")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(array = @ArraySchema(schema = @Schema(implementation = CandidateEntity.class)))
            }),
            @ApiResponse(responseCode = "400", description = "Usuário já existe!")
    })
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidateEntity) {
        try {
            var result = this. createCandidateUseCase.execute(candidateEntity);

            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("")
    @PreAuthorize("hasRole('CANDIDATE')")
    @Operation(summary = "Perfil do candidato")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = ProfileCandidateResponseDTO.class))
            })
    })
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<Object> get(HttpServletRequest request) {
        try {
            var candidateId = request.getAttribute("candidate_id");
            var profile = profileCandidateUseCase.execute(UUID.fromString(candidateId.toString()));
            return ResponseEntity.ok().body(profile);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/job")
    @PreAuthorize("hasRole('CANDIDATE')")
    @Operation(summary = "Listagem de vagas disponível para o candidato")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(array = @ArraySchema(schema = @Schema(implementation = JobEntity.class)))
            }),
            @ApiResponse(responseCode = "400", description = "User not found!")
    })
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<Object> filterJobByFilter(@RequestParam String filter) {
        try {
            var jobs = this.listAllJobsByFilterUseCase.execute(filter);
            return ResponseEntity.ok().body(jobs);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/job/apply")
    @PreAuthorize("hasRole('CANDIDATE')")
    @Operation(summary = "Aplicação em uma vaga pelo candidato")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = ApplyJobEntity.class))
            }),
    })
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<Object> applyJob(HttpServletRequest request, @RequestBody UUID jobId) {
        try {
            var candidateId = request.getAttribute("candidate_id");
            var result = this.applyJobCandidateUseCase.execute(UUID.fromString(candidateId.toString()), jobId);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
