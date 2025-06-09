package br.csi.service;

import br.csi.dao.MovimentacaoDAO;
import br.csi.dao.ProdutoDAO;
import br.csi.model.Movimentacao;
import br.csi.model.Produto;

public class MovimentacaoService {
    private MovimentacaoDAO movimentacaoDAO = new MovimentacaoDAO();
    private ProdutoDAO produtoDAO = new ProdutoDAO();

    public boolean registrarMovimentacao(Movimentacao mov) {
        Produto produto = produtoDAO.buscar(mov.getProdutoId());
        if (produto == null) return false;

        if (mov.getTipo().equalsIgnoreCase("ENTRADA")) {
            produto.setQuantidade(produto.getQuantidade() + mov.getQuantidade());
        } else if (mov.getTipo().equalsIgnoreCase("SAIDA")) {
            if (produto.getQuantidade() < mov.getQuantidade()) {
                return false; // Não pode sair mais do que tem em estoque
            }
            produto.setQuantidade(produto.getQuantidade() - mov.getQuantidade());
        } else {
            return false;
        }

        boolean okMov = movimentacaoDAO.inserir(mov);
        boolean okProd = produtoDAO.atualizar(produto);
        return okMov && okProd;
    }
} 