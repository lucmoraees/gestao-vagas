package com.lucasmoraes.gestaovagas.modules.company.DTO;

import lombok.Data;

@Data
public class CreateJobDTO {
    private String description;
    private String benefits;
    private String level;
}
