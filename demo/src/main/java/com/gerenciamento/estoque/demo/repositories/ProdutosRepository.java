package com.gerenciamento.estoque.demo.repositories;

import com.gerenciamento.estoque.demo.Produtos.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProdutosRepository extends JpaRepository<Produtos,Long> {
    Optional<Produtos> findById(Long id);
}
