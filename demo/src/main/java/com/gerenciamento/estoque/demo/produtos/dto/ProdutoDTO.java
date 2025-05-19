package com.gerenciamento.estoque.demo.produtos.dto;

import com.gerenciamento.estoque.demo.user.model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProdutoDTO(

        @NotBlank
        String nome,

        String descricao,

        @NotNull
        int quantidade,

        @NotNull
        User usuario
) {
}
