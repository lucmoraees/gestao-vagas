package com.lucasmoraes.gestaovagas.modules.company.controllers;

import com.lucasmoraes.gestaovagas.modules.company.DTO.AuthCompanyDTO;
import com.lucasmoraes.gestaovagas.modules.company.DTO.AuthCompanyResponseDTO;
import com.lucasmoraes.gestaovagas.modules.company.entities.CompanyEntity;
import com.lucasmoraes.gestaovagas.modules.company.useCases.AuthCompanyUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
@Tag(name = "Companhias")
public class AuthCompanyController {
    @Autowired
    private AuthCompanyUseCase authCompanyUseCase;

    @PostMapping("/auth")
    @Operation(summary = "Autenticação da empresa")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = AuthCompanyResponseDTO.class))
            })
    })
    public ResponseEntity authCompany(@RequestBody AuthCompanyDTO authCompanyDTO) {
        try {
            AuthCompanyResponseDTO response = this.authCompanyUseCase.execute(authCompanyDTO);

            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
