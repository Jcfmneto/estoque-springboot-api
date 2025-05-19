package com.gerenciamento.estoque.demo.estoque.repository;

import com.gerenciamento.estoque.demo.estoque.model.MovimentacaoEstoque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstoqueRepository extends JpaRepository<MovimentacaoEstoque, Long> {
}
