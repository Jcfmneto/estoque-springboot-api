package com.gerenciamento.estoque.demo.services;
import com.gerenciamento.estoque.demo.Produtos.ProdutoDTO;
import com.gerenciamento.estoque.demo.Produtos.ProdutoSalvoDTO;
import com.gerenciamento.estoque.demo.Produtos.Produtos;
import com.gerenciamento.estoque.demo.repositories.ProdutosRepository;
import org.springframework.stereotype.Service;

@Service
public class ProdutosService {

    private final ProdutosRepository produtosRepository;

    public ProdutosService(ProdutosRepository produtosRepository) {
        this.produtosRepository = produtosRepository;
    }

    public ProdutoSalvoDTO cadastrarProduto(ProdutoDTO produtoDTO) {
        Produtos produto = new Produtos(produtoDTO.nome(), produtoDTO.descricao(), produtoDTO.quantidade());
        Produtos salvo = produtosRepository.save(produto);
        return new ProdutoSalvoDTO(salvo);
    }

}
