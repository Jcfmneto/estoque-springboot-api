package com.gerenciamento.estoque.demo.estoque.dto;

import com.gerenciamento.estoque.demo.estoque.model.MovimentacaoEstoque;
import com.gerenciamento.estoque.demo.estoque.model.TipoMovimentacao;

import java.time.LocalDate;

public record RespostaMovimentacaoDTO(String menssagem, Long id, String nome, int quantidadeAtual,
                                      int quantidadeAdicionada, String motivo, TipoMovimentacao tipoMovimentacao,
                                      LocalDate now) { }
