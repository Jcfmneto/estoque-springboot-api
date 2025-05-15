package com.gerenciamento.estoque.demo.Produtos;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table
@Getter
@NoArgsConstructor
@AllArgsConstructor
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

}
