package com.lucasmoraes.gestaovagas.modules.company.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthCompanyDTO {
    private String username;
    private String password;
}
