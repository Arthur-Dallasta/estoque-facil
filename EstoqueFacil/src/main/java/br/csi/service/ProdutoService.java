package br.csi.service;

import br.csi.dao.ProdutoDAO;
import br.csi.model.Produto;

import java.util.ArrayList;

public class ProdutoService {
    private ProdutoDAO produtoDAO;
    
    public ProdutoService() {
        this.produtoDAO = new ProdutoDAO();
    }
    
    public boolean cadastrarProduto(Produto produto) {
        if (produto.getNome() == null || produto.getNome().trim().isEmpty()) {
            return false;
        }
        if (produto.getPreco() <= 0) {
            return false;
        }
        if (produto.getQuantidade() < 0) {
            return false;
        }
        
        produto.setAtivo(true);
        return produtoDAO.inserir(produto);
    }
    
    public boolean atualizarProduto(Produto produto) {
        if (produto.getId() <= 0) {
            return false;
        }
        if (produto.getNome() == null || produto.getNome().trim().isEmpty()) {
            return false;
        }
        if (produto.getPreco() <= 0) {
            return false;
        }
        if (produto.getQuantidade() < 0) {
            return false;
        }
        
        return produtoDAO.atualizar(produto);
    }
    
    public boolean excluirProduto(int id) {
        if (id <= 0) {
            return false;
        }
        return produtoDAO.excluir(id);
    }
    
    public ArrayList<Produto> listarProdutos() {
        return produtoDAO.listar();
    }
    
    public Produto buscarProduto(int id) {
        if (id <= 0) {
            return null;
        }
        return produtoDAO.buscar(id);
    }
} 