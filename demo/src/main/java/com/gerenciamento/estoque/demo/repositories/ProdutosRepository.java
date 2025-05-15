package com.gerenciamento.estoque.demo.repositories;

import com.gerenciamento.estoque.demo.Produtos.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutosRepository extends JpaRepository<Produtos,Long> {
}
