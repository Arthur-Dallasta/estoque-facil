package br.csi.dao;

import br.csi.model.Produto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProdutoDAOTest {
    
    private ProdutoDAO produtoDAO = new ProdutoDAO();
    
    @Test
    public void testListarProdutos() {
        System.out.println("=== Testando Listagem de Produtos ===");
        var produtos = produtoDAO.listar();
        
        assertNotNull(produtos, "A lista de produtos não deveria ser nula");
        assertFalse(produtos.isEmpty(), "A lista de produtos não deveria estar vazia");
        
        System.out.println("Produtos encontrados: " + produtos.size());
        for (Produto p : produtos) {
            System.out.println("ID: " + p.getId() + 
                             " | Nome: " + p.getNome() + 
                             " | Preço: R$" + p.getPreco() + 
                             " | Quantidade: " + p.getQuantidade());
        }
    }
    
    @Test
    public void testBuscarProduto() {
        System.out.println("\n=== Testando Busca de Produto ===");
        Produto produto = produtoDAO.buscar(1);
        
        assertNotNull(produto, "Deveria ter encontrado o produto");
        System.out.println("Produto encontrado: " + produto.getNome());
    }
} 