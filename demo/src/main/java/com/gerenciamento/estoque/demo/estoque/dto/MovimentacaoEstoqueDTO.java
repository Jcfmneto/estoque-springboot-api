package com.gerenciamento.estoque.demo.estoque.dto;


import jakarta.validation.constraints.NotNull;

public record MovimentacaoEstoqueDTO(

        @NotNull
        int quantidade,

        String motivo) {
}
