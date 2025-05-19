package com.gerenciamento.estoque.demo.controllers;

import com.gerenciamento.estoque.demo.estoque.dto.MovimentacaoHistoricoDTO;
import com.gerenciamento.estoque.demo.services.EstoqueService;
import com.gerenciamento.estoque.demo.user.model.User;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimentacoes")
@SecurityRequirement(name = "bearer-key")
public class MovimentacoesController {

    private final EstoqueService estoqueService;

    public MovimentacoesController(EstoqueService estoqueService) {
        this.estoqueService = estoqueService;
    }

    @GetMapping()
    public ResponseEntity<List<MovimentacaoHistoricoDTO>> listarMovimentacoes(
            @RequestParam(required = false) Long produtoId,
            @AuthenticationPrincipal User usuario) {

        var movimentacoes = estoqueService.listarMovimentacoes(produtoId, usuario);
        return ResponseEntity.ok(movimentacoes);
    }
}