package com.lucasmoraes.gestaovagas.modules.company.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateJobDTO {
    @Schema(example = "Vaga para pessoa desenvolvedora Junior", requiredMode = Schema.RequiredMode.REQUIRED)
    private String description;

    @Schema(example = "Gympass, Vale Alimentação, Vale Refeição e Vale Transporte", requiredMode = Schema.RequiredMode.REQUIRED)
    private String benefits;

    @Schema(example = "JUNIOR", requiredMode = Schema.RequiredMode.REQUIRED)
    private String level;
}
