package com.gerenciamento.estoque.demo.produtos.repository;

import com.gerenciamento.estoque.demo.produtos.model.Produtos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProdutosRepository extends JpaRepository<Produtos,Long> {
    Optional<Produtos> findById(Long id);

    Page<Produtos> findAllByUsuarioId(Pageable pageable, Long usuarioId);
}
