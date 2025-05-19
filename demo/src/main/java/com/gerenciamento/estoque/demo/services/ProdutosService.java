package com.gerenciamento.estoque.demo.services;

import com.gerenciamento.estoque.demo.Produtos.*;
import com.gerenciamento.estoque.demo.repositories.ProdutosRepository;
import com.gerenciamento.estoque.demo.user.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutosService {

    private final ProdutosRepository produtosRepository;

    public ProdutosService(ProdutosRepository produtosRepository) {
        this.produtosRepository = produtosRepository;
    }

    public ProdutoSalvoDTO cadastrarProduto(ProdutoDTO produtoDTO, User usuario) {
        Produtos produto = new Produtos(produtoDTO, usuario);
        Produtos salvo = produtosRepository.save(produto);
        return new ProdutoSalvoDTO(salvo);
    }

    public void deletarProduto(Long id, User usuario) {
        Produtos produto = produtosRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));
        validarPropriedadeDoProduto(produto, usuario);
        produtosRepository.delete(produto);
    }

    public ProdutoSalvoDTO atualizar(Long id, ProdutoDTO dto, User usuario) {
        Produtos produto = produtosRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));
        validarPropriedadeDoProduto(produto, usuario);

        produto.setNome(dto.nome());
        produto.setDescricao(dto.descricao());
        produto.setQuantidade(dto.quantidade());

        Produtos produtoSalvo = produtosRepository.save(produto);
        return new ProdutoSalvoDTO(produtoSalvo);
    }

    public ProdutosPaginadosDTO listarProdutos(int pagina, int itens, User usuario) {
        var produtosPaginados = produtosRepository.findAllByUsuarioId(PageRequest.of(pagina, itens), usuario.getId());
        List<ProdutosListagemDTO> produtosListagemDTO = produtosPaginados.stream()
                .map(ProdutosListagemDTO::new)
                .toList();
        return new ProdutosPaginadosDTO(
                produtosListagemDTO,
                produtosPaginados.getNumber(),
                produtosPaginados.getTotalPages(),
                produtosPaginados.getTotalElements()
        );
    }

    private void validarPropriedadeDoProduto(Produtos produto, User usuario) {
        if (!usuario.getId().equals(produto.getUsuario().getId())) {
            throw new AccessDeniedException("Acesso negado");
        }
    }
}
