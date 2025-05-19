package com.gerenciamento.estoque.demo.produtos.dto;

import java.util.List;

public record ProdutosPaginadosDTO(List<ProdutosListagemDTO> content, int pageNumber, int totalPages,
                                   long totalElements) {
}
