package com.gerenciamento.estoque.demo.estoque.dto;

import com.gerenciamento.estoque.demo.estoque.model.TipoMovimentacao;

import java.time.LocalDate;

public record MovimentacaoHistoricoDTO(
        Long produtoId,
        int quantidade,
        TipoMovimentacao tipo,
        String nome,
        String motivo,
        LocalDate data
){
    public MovimentacaoHistoricoDTO(Long produtoId, String nomeProduto, int quantidade, TipoMovimentacao tipo, String motivo, LocalDate data) {
        this(produtoId, quantidade, tipo, nomeProduto, motivo, data);

    }
}