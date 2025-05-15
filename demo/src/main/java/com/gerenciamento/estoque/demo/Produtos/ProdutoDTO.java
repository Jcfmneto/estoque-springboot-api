package com.gerenciamento.estoque.demo.Produtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProdutoDTO(

        @NotBlank
        String nome,

        String descricao,

        @NotNull
        int quantidade
) {
}
