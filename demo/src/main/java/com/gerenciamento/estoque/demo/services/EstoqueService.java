package com.gerenciamento.estoque.demo.services;


import com.gerenciamento.estoque.demo.estoque.dto.MovimentacaoHistoricoDTO;
import com.gerenciamento.estoque.demo.estoque.dto.RespostaMovimentacaoDTO;
import com.gerenciamento.estoque.demo.estoque.model.MovimentacaoEstoque;
import com.gerenciamento.estoque.demo.estoque.model.TipoMovimentacao;
import com.gerenciamento.estoque.demo.estoque.repository.EstoqueRepository;
import com.gerenciamento.estoque.demo.infra.exceptions.EstoqueException;
import com.gerenciamento.estoque.demo.produtos.model.Produtos;
import com.gerenciamento.estoque.demo.produtos.repository.ProdutosRepository;
import com.gerenciamento.estoque.demo.user.model.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstoqueService {

    private final ProdutosRepository produtosRepository;
    EstoqueRepository estoqueRepository;


   public EstoqueService(EstoqueRepository estoqueRepository, ProdutosRepository produtosRepository) {
       this.estoqueRepository = estoqueRepository;
       this.produtosRepository = produtosRepository;
   }

    public void registrarMovimentacao(Produtos produto, int quantidade, String motivo, TipoMovimentacao tipo) {
        MovimentacaoEstoque movimentacao = new MovimentacaoEstoque();
        movimentacao.setProduto(produto);
        movimentacao.setQuantidade(quantidade);
        movimentacao.setTipo(tipo);
        movimentacao.setMotivo(motivo);
        movimentacao.setData(LocalDate.now());

       estoqueRepository.save(movimentacao);
    }

        public List<MovimentacaoHistoricoDTO> listarMovimentacoes(Long produtoId, User usuario) {
           List<MovimentacaoEstoque> movimentacoes;

           if(produtoId != null){
               Produtos produto = produtosRepository.findById(produtoId).orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));

                if(!produto.getUsuario().equals(usuario)){
                    throw new EstoqueException("VocÊ não tem permissão para visualizar estoque do produto");
                }

                movimentacoes = estoqueRepository.findByProdutoId(produtoId);
           }else {
               movimentacoes = estoqueRepository.findByProdutoUsuarioId(usuario.getId());
           }
                return movimentacoes.stream().map(movimentacao -> mapToDTO(movimentacao)).collect(Collectors.toList());
        }

    private MovimentacaoHistoricoDTO mapToDTO(MovimentacaoEstoque movimentacao) {
       Produtos produto = movimentacao.getProduto();

       return new MovimentacaoHistoricoDTO(
               produto.getId(),
               produto.getNome(),
               movimentacao.getQuantidade(),
               movimentacao.getTipo(),
               movimentacao.getMotivo(),
               movimentacao.getData());
    }


}

