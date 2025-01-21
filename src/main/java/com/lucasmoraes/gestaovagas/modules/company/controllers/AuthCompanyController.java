package com.lucasmoraes.gestaovagas.modules.company.controllers;

import com.lucasmoraes.gestaovagas.modules.company.DTO.AuthCompanyDTO;
import com.lucasmoraes.gestaovagas.modules.company.DTO.AuthCompanyResponseDTO;
import com.lucasmoraes.gestaovagas.modules.company.useCases.AuthCompanyUseCase;
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
public class AuthCompanyController {
    @Autowired
    private AuthCompanyUseCase authCompanyUseCase;

    @PostMapping("/auth")
    public ResponseEntity authCompany(@RequestBody AuthCompanyDTO authCompanyDTO) {
        try {
            AuthCompanyResponseDTO response = this.authCompanyUseCase.execute(authCompanyDTO);

            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
