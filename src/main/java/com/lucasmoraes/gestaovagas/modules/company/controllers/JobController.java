package com.lucasmoraes.gestaovagas.modules.company.controllers;

import com.lucasmoraes.gestaovagas.modules.company.DTO.CreateJobDTO;
import com.lucasmoraes.gestaovagas.modules.company.entities.JobEntity;
import com.lucasmoraes.gestaovagas.modules.company.useCases.CreateJobUseCase;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/company/job")
@Tag(name = "Vagas", description = "Informações das vagas")
public class JobController {
    @Autowired
    CreateJobUseCase createJobUseCase;

    @PostMapping("")
    @PreAuthorize("hasRole('COMPANY')")
    @Operation(summary = "Cadastro de vaga")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = JobEntity.class))
            })
    })
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<Object> create(@Valid @RequestBody CreateJobDTO createJobDTO, HttpServletRequest request) {
        try {
            var companyId = request.getAttribute("company_id");

            JobEntity jobEntity = JobEntity.builder()
                                            .benefits(createJobDTO.getBenefits())
                                            .companyId(UUID.fromString(companyId.toString()))
                                            .description(createJobDTO.getDescription())
                                            .level(createJobDTO.getLevel())
                                            .build();

            var result = createJobUseCase.execute(jobEntity);

            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
