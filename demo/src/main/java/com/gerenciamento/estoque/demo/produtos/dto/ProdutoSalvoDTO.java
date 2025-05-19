package com.gerenciamento.estoque.demo.produtos.dto;

import com.gerenciamento.estoque.demo.produtos.model.Produtos;

public record ProdutoSalvoDTO(Long id, String nome, String descricao, int quantidade, Long usuarioId) {

    public ProdutoSalvoDTO(Produtos produtos){
        this(produtos.getId(),
                produtos.getNome(),
                produtos.getDescricao(),
                produtos.getQuantidade(),
                produtos.getUsuario().getId());
    }
}
