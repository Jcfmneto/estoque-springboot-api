package com.gerenciamento.estoque.demo.controllers;

import com.gerenciamento.estoque.demo.estoque.dto.MovimentacaoEstoqueDTO;
import com.gerenciamento.estoque.demo.estoque.dto.RespostaMovimentacaoDTO;
import com.gerenciamento.estoque.demo.produtos.dto.ProdutoDTO;
import com.gerenciamento.estoque.demo.produtos.dto.ProdutoSalvoDTO;
import com.gerenciamento.estoque.demo.produtos.dto.ProdutosPaginadosDTO;
import com.gerenciamento.estoque.demo.services.ProdutosService;
import com.gerenciamento.estoque.demo.user.model.User;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produtos")
@SecurityRequirement(name = "bearer-key")
public class ProdutosController {

    private final ProdutosService produtosService;

    public ProdutosController(ProdutosService produtosService) {
        this.produtosService = produtosService;
    }

    @PostMapping()
    public ResponseEntity<ProdutoSalvoDTO> salvarProduto(@RequestBody @Valid ProdutoDTO dto,
                                                         @AuthenticationPrincipal User usuario) {
        ProdutoSalvoDTO produtoSalvo = produtosService.cadastrarProduto(dto, usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoSalvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id,
                                               @AuthenticationPrincipal User usuario) {
        produtosService.deletarProduto(id, usuario);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoSalvoDTO> atualizarProduto(@PathVariable Long id,
                                                            @RequestBody @Valid ProdutoDTO dto,
                                                            @AuthenticationPrincipal User usuario) {
        ProdutoSalvoDTO produtoSalvo = produtosService.atualizar(id, dto, usuario);
        return ResponseEntity.status(HttpStatus.OK).body(produtoSalvo);
    }

    @GetMapping()
    public ResponseEntity<ProdutosPaginadosDTO> listarProdutos(@RequestParam(defaultValue = "0") int pagina,
                                                               @RequestParam(defaultValue = "10") int itens,
                                                               @AuthenticationPrincipal User usuario) {
        var produtosPaginados = produtosService.listarProdutos(pagina, itens, usuario);
        return ResponseEntity.status(HttpStatus.OK).body(produtosPaginados);
    }

    @PostMapping("{id}/entrada")
    public ResponseEntity<RespostaMovimentacaoDTO> entradaEstoque(@PathVariable Long id,
                                                                  @AuthenticationPrincipal User usuario,
                                                                  @RequestBody @Valid MovimentacaoEstoqueDTO dto) {
        var resposta = produtosService.entradaEstoque(dto, id, usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }

    @PostMapping("{id}/saida")
    public ResponseEntity<RespostaMovimentacaoDTO> saidaEstoque(@PathVariable Long id,
                                                                @AuthenticationPrincipal User usuario,
                                                                @RequestBody @Valid MovimentacaoEstoqueDTO dto) {
        var resposta = produtosService.baixaEstoque(dto, id, usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }
}
