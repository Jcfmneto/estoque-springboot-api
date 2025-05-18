package com.gerenciamento.estoque.demo.controllers;


import com.gerenciamento.estoque.demo.Produtos.ProdutoDTO;
import com.gerenciamento.estoque.demo.Produtos.ProdutoSalvoDTO;
import com.gerenciamento.estoque.demo.services.ProdutosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produtos")
public class ProdutosController {

   private final ProdutosService produtosService;

   public ProdutosController(ProdutosService produtosService) {
       this.produtosService = produtosService;
   }

    @PostMapping()
    public ResponseEntity<ProdutoSalvoDTO> save(@RequestBody ProdutoDTO dto) {
        ProdutoSalvoDTO produtoSalvo = produtosService.cadastrarProduto(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoSalvo);
    }
    @DeleteMapping()
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        produtosService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping()
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ProdutoDTO dto) {
        produtosService.atualizar(id, dto);
        return null;
    }

   }

