package com.gerenciamento.estoque.demo.Produtos;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class Produtos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(unique = true, nullable = false, length = 100)
    String nome;

    @Column(unique = false, nullable = false,  length = 200)
    String descricao;

    @Column(unique = false, nullable = false)
    int quantidade;

    public Produtos(ProdutoDTO produtoDTO) {
        this.nome = produtoDTO.nome();
        this.descricao = produtoDTO.descricao();
        this.quantidade = produtoDTO.quantidade();
    }
}
