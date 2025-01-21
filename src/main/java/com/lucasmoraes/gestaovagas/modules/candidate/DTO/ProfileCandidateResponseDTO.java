package com.lucasmoraes.gestaovagas.modules.candidate.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileCandidateResponseDTO {
    private UUID id;

    @Schema(example = "Desenvolvedor Java")
    private String description;

    @Schema(example = "maria")
    private String username;

    @Schema(example = "maria@gmail.com")
    private String email;

    @Schema(example = "maria")
    private String name;
}
