package com.lucasmoraes.gestaovagas.modules.candidate;

import java.util.UUID;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class CandidateEntity {
    private UUID id;

    private String name;

    @NotBlank()
    @Pattern(regexp = "\\s+", message = "O campo (username) não deve conter espaços.")
    private String userName;

    @Email(message = "O camnpo (email) deve conter um e-mail vállido!")
    private String email;

    @Length(min = 10, max = 100, message = "S senha deve conter entre 10 e 100 caractéres")
    private String password;

    private String description;

    private String curriculum;
}
