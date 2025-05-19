package com.gerenciamento.estoque.demo.produtos.model;


import com.gerenciamento.estoque.demo.produtos.dto.ProdutoDTO;
import com.gerenciamento.estoque.demo.user.model.User;
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
    private long id;

    @Column(unique = true, nullable = false, length = 100)
    private String nome;

    @Column(unique = false, nullable = true,  length = 200)
    private String descricao;

    @Column(unique = false, nullable = false)
    private int quantidade;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private User usuario;



    public Produtos(ProdutoDTO produtoDTO, User usuario) {
        this.nome = produtoDTO.nome();
        this.descricao = produtoDTO.descricao();
        this.quantidade = produtoDTO.quantidade();
        this.usuario = usuario;
    }
}
