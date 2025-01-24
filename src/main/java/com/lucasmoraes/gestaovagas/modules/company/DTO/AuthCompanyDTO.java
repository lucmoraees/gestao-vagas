package com.lucasmoraes.gestaovagas.modules.company.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthCompanyDTO {
    private String username;
    private String password;
}
