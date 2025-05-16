package com.gerenciamento.estoque.demo.Produtos;

public record ProdutoSalvoDTO( Long id, String nome, String descricao, int quantidade) {

    public ProdutoSalvoDTO(Produtos produtos){
        this(produtos.getId(), produtos.getNome(), produtos.getDescricao(), produtos.getQuantidade());
    }
}
