package com.gerenciamento.estoque.demo.controllers;


import com.gerenciamento.estoque.demo.Produtos.ProdutoDTO;
import com.gerenciamento.estoque.demo.Produtos.Produtos;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/produtos")
public class ProdutosController {

    public ResponseEntity<Produtos> save(@RequestBody ProdutoDTO dto) {
        produtosService.cadastrarProduto(dto.nome(), dto.descricao(), dto.quantidade());

        return ResponseEntity.created(URI.create("/produtos/" + dto.nome())).build();
    }
}
