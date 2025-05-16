package com.gerenciamento.estoque.demo.controllers;


import com.gerenciamento.estoque.demo.Produtos.ProdutoDTO;
import com.gerenciamento.estoque.demo.Produtos.ProdutoSalvoDTO;
import com.gerenciamento.estoque.demo.Produtos.Produtos;
import com.gerenciamento.estoque.demo.services.ProdutosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerTypePredicate;

import java.net.URI;

@RestController
@RequestMapping("/produtos")
public class ProdutosController {

    @Autowired
    private ProdutosService produtosService;

    @PostMapping()
    public ResponseEntity<ProdutoSalvoDTO> save(@RequestBody ProdutoDTO dto) {
        ProdutoSalvoDTO produtoSalvo = produtosService.cadastrarProduto(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoSalvo);
    }
}
