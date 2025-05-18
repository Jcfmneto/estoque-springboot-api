package com.gerenciamento.estoque.demo.services;
import com.gerenciamento.estoque.demo.Produtos.ProdutoDTO;
import com.gerenciamento.estoque.demo.Produtos.ProdutoSalvoDTO;
import com.gerenciamento.estoque.demo.Produtos.Produtos;
import com.gerenciamento.estoque.demo.repositories.ProdutosRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProdutosService {

    private final ProdutosRepository produtosRepository;

    public ProdutosService(ProdutosRepository produtosRepository) {
        this.produtosRepository = produtosRepository;
    }

    public ProdutoSalvoDTO cadastrarProduto(ProdutoDTO produtoDTO) {
        Produtos produto = new Produtos(produtoDTO);
        Produtos salvo = produtosRepository.save(produto);
        return new ProdutoSalvoDTO(salvo);
    }

    public void deletarProduto(Long id) {
        if(produtosRepository.existsById(id)){
            produtosRepository.deleteById(id);
        }else{ throw new EntityNotFoundException("Produto não encontrado");}
    }

    public void  atualizar(Long id, ProdutoDTO dto) {
         Produtos produto = produtosRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        produto.setNome(dto.nome());
        produto.setDescricao(dto.descricao());
        produto.setQuantidade(dto.quantidade());
        produtosRepository.save(produto);
    }
}
