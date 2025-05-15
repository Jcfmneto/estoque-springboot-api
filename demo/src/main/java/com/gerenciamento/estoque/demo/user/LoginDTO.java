package com.gerenciamento.estoque.demo.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record LoginDTO(

        @Email
        @NotNull
        String email,

        @NotNull
        @Size(min = 6)
        String senha) {
}
