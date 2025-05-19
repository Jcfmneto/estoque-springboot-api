package com.gerenciamento.estoque.demo.estoque.repository;

import com.gerenciamento.estoque.demo.estoque.model.MovimentacaoEstoque;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstoqueRepository extends JpaRepository<MovimentacaoEstoque, Long> {
    List<MovimentacaoEstoque> findByProdutoId(Long produtoId);

    List<MovimentacaoEstoque> findByProdutoUsuarioId(Long usuarioId);
}
