package com.gerenciamento.estoque.demo.services;

import com.gerenciamento.estoque.demo.estoque.model.MovimentacaoEstoque;
import com.gerenciamento.estoque.demo.estoque.model.TipoMovimentacao;
import com.gerenciamento.estoque.demo.estoque.repository.EstoqueRepository;
import com.gerenciamento.estoque.demo.produtos.model.Produtos;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EstoqueService {

   EstoqueRepository estoqueRepository;


   public EstoqueService(EstoqueRepository estoqueRepository) {
       this.estoqueRepository = estoqueRepository;
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
}
