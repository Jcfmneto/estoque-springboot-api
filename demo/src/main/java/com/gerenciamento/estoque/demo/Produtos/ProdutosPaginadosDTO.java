package com.gerenciamento.estoque.demo.Produtos;

import java.util.List;

public record ProdutosPaginadosDTO(List<ProdutosListagemDTO> content, int pageNumber, int totalPages,
                                   long totalElements) {
}
