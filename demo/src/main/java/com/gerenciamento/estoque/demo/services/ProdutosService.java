package com.gerenciamento.estoque.demo.services;

import com.gerenciamento.estoque.demo.estoque.dto.MovimentacaoEstoqueDTO;
import com.gerenciamento.estoque.demo.estoque.dto.RespostaMovimentacaoDTO;
import com.gerenciamento.estoque.demo.estoque.model.TipoMovimentacao;
import com.gerenciamento.estoque.demo.infra.exceptions.EstoqueException;
import com.gerenciamento.estoque.demo.produtos.dto.ProdutoDTO;
import com.gerenciamento.estoque.demo.produtos.dto.ProdutoSalvoDTO;
import com.gerenciamento.estoque.demo.produtos.dto.ProdutosListagemDTO;
import com.gerenciamento.estoque.demo.produtos.dto.ProdutosPaginadosDTO;
import com.gerenciamento.estoque.demo.produtos.model.Produtos;
import com.gerenciamento.estoque.demo.produtos.repository.ProdutosRepository;
import com.gerenciamento.estoque.demo.user.model.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProdutosService {

    private final ProdutosRepository produtosRepository;

    private final EstoqueService estoqueService;

    public ProdutosService(ProdutosRepository produtosRepository, EstoqueService estoqueService) {
        this.produtosRepository = produtosRepository;
        this.estoqueService = estoqueService;
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

    public RespostaMovimentacaoDTO entradaEstoque(MovimentacaoEstoqueDTO dto, Long id, User usuario) {
        Produtos produto = produtosRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));
        validarPropriedadeDoProduto(produto, usuario);
        if(dto.quantidade() <= 0){
            throw new EstoqueException("Adicione um valor válido");
        }
            produto.setQuantidade(produto.getQuantidade() + dto.quantidade());
            produtosRepository.save(produto);

            estoqueService.registrarMovimentacao(produto, dto.quantidade(), dto.motivo(), TipoMovimentacao.ENTRADA);

        return new RespostaMovimentacaoDTO(
                "Entrada realizada com sucesso",
                produto.getId(),
                produto.getNome(),
                produto.getQuantidade(),
                dto.quantidade(),
                dto.motivo(),
                TipoMovimentacao.ENTRADA,
                LocalDate.now()
        );

    }

    public RespostaMovimentacaoDTO baixaEstoque(MovimentacaoEstoqueDTO dto, Long id, User usuario) {
        Produtos produto = produtosRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));
        validarPropriedadeDoProduto(produto, usuario);
        if(dto.quantidade() > produto.getQuantidade()){
            throw new EstoqueException("Adicione um valor válido");
        }

        produto.setQuantidade(produto.getQuantidade() - dto.quantidade());
        produtosRepository.save(produto);

        estoqueService.registrarMovimentacao(produto, dto.quantidade(),  dto.motivo(), TipoMovimentacao.SAIDA);

        return new RespostaMovimentacaoDTO(
                "Saida realizada com sucesso",
                produto.getId(),
                produto.getNome(),
                produto.getQuantidade(),
                dto.quantidade(),
                dto.motivo(),
                TipoMovimentacao.SAIDA,
                LocalDate.now()
        );
    }

    private void validarPropriedadeDoProduto(Produtos produto, User usuario) {
        if (!usuario.getId().equals(produto.getUsuario().getId())) {
            throw new AccessDeniedException("Acesso negado");
        }
    }

}
