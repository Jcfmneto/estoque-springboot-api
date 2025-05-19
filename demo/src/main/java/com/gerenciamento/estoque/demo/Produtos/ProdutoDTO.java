package com.gerenciamento.estoque.demo.Produtos;

import com.gerenciamento.estoque.demo.user.User;
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
