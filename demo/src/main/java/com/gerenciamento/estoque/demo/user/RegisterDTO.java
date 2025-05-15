package com.gerenciamento.estoque.demo.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterDTO(

        @Email
        @NotBlank
        String email,

        @NotBlank
        @Size(min = 6)
        String senha

) {
}
