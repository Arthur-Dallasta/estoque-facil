package br.csi.test;

import br.csi.dao.ProdutoDAO;
import br.csi.model.Produto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class ProdutoTest {
    private ProdutoDAO produtoDAO = new ProdutoDAO();

    @Test
    public void testCadastrarProdutos() {
        System.out.println("\n=== Testando Cadastro de Produtos ===");
        
        // Teste 1: Cadastrar produto válido
        System.out.println("\nTeste 1: Cadastrando produto válido...");
        Produto p1 = new Produto();
        p1.setNome("Notebook Dell");
        p1.setDescricao("Notebook Dell Inspiron 15");
        p1.setPreco(3500.00);
        p1.setQuantidade(10);
        p1.setCategoria("Eletrônicos");
        p1.setAtivo(true);
        boolean resultado1 = produtoDAO.inserir(p1);
        System.out.println("Resultado do cadastro: " + (resultado1 ? "Sucesso" : "Falha"));
        assertTrue(resultado1, "Falha ao cadastrar produto válido");

        // Teste 2: Cadastrar produto com preço zero
        System.out.println("\nTeste 2: Cadastrando produto com preço zero...");
        Produto p2 = new Produto();
        p2.setNome("Mouse");
        p2.setDescricao("Mouse sem fio");
        p2.setPreco(0.0);
        p2.setQuantidade(5);
        p2.setCategoria("Periféricos");
        p2.setAtivo(true);
        boolean resultado2 = produtoDAO.inserir(p2);
        System.out.println("Resultado do cadastro: " + (resultado2 ? "Sucesso" : "Falha"));
        assertTrue(resultado2, "Falha ao cadastrar produto com preço zero");

        // Teste 3: Cadastrar produto com quantidade negativa
        System.out.println("\nTeste 3: Cadastrando produto com quantidade negativa...");
        Produto p3 = new Produto();
        p3.setNome("Teclado");
        p3.setDescricao("Teclado mecânico");
        p3.setPreco(200.00);
        p3.setQuantidade(-5);
        p3.setCategoria("Periféricos");
        p3.setAtivo(true);
        boolean resultado3 = produtoDAO.inserir(p3);
        System.out.println("Resultado do cadastro: " + (resultado3 ? "Sucesso" : "Falha"));
        assertTrue(resultado3, "Falha ao cadastrar produto com quantidade negativa");
    }

    @Test
    public void testListarProdutos() {
        System.out.println("\n=== Testando Listagem de Produtos ===");
        
        // Teste 1: Listar todos os produtos
        System.out.println("\nTeste 1: Listando todos os produtos...");
        ArrayList<Produto> produtos = produtoDAO.listar();
        System.out.println("Quantidade de produtos encontrados: " + produtos.size());
        assertNotNull(produtos, "Lista de produtos não deve ser nula");
        assertTrue(produtos.size() > 0, "Lista de produtos deve conter elementos");

        // Teste 2: Verificar se produtos têm IDs
        System.out.println("\nTeste 2: Verificando IDs dos produtos...");
        for (Produto p : produtos) {
            System.out.println("Produto: " + p.getNome() + " - ID: " + p.getId());
            assertTrue(p.getId() > 0, "Produto deve ter ID válido");
        }

        // Teste 3: Verificar se produtos têm preços válidos
        System.out.println("\nTeste 3: Verificando preços dos produtos...");
        for (Produto p : produtos) {
            System.out.println("Produto: " + p.getNome() + " - Preço: R$" + p.getPreco());
            assertTrue(p.getPreco() >= 0, "Produto deve ter preço válido");
        }
    }

    @Test
    public void testRemoverProdutos() {
        System.out.println("\n=== Testando Remoção de Produtos ===");
        
        // Primeiro, vamos cadastrar um produto para remover
        System.out.println("\nPreparando produto para teste de remoção...");
        Produto p1 = new Produto();
        p1.setNome("Produto Teste 1");
        p1.setDescricao("Descrição Teste 1");
        p1.setPreco(100.00);
        p1.setQuantidade(5);
        p1.setCategoria("Teste");
        p1.setAtivo(true);
        
        boolean inseriu = produtoDAO.inserir(p1);
        System.out.println("Resultado da inserção: " + (inseriu ? "Sucesso" : "Falha"));
        assertTrue(inseriu, "Falha ao inserir produto para teste");
        
        // Listar todos os produtos para debug
        System.out.println("\nListando todos os produtos após inserção:");
        ArrayList<Produto> todosProdutos = produtoDAO.listar();
        for (Produto p : todosProdutos) {
            System.out.println("ID: " + p.getId() + " | Nome: " + p.getNome() + " | Ativo: " + p.isAtivo());
        }
        
        // Buscar o produto para confirmar que foi inserido
        System.out.println("\nVerificando produto após inserção...");
        System.out.println("Buscando produto com ID: " + p1.getId());
        Produto produtoInserido = produtoDAO.buscar(p1.getId());
        System.out.println("Produto encontrado: " + (produtoInserido != null ? "Sim" : "Não"));
        if (produtoInserido != null) {
            System.out.println("Detalhes do produto encontrado:");
            System.out.println("ID: " + produtoInserido.getId());
            System.out.println("Nome: " + produtoInserido.getNome());
            System.out.println("Ativo: " + produtoInserido.isAtivo());
        }
        assertNotNull(produtoInserido, "Produto não foi encontrado após inserção");
        assertTrue(produtoInserido.isAtivo(), "Produto deve estar ativo após inserção");

        // Teste 1: Remover produto existente
        System.out.println("\nTeste 1: Removendo produto existente...");
        boolean excluiu = produtoDAO.excluir(p1.getId());
        System.out.println("Resultado da exclusão: " + (excluiu ? "Sucesso" : "Falha"));
        assertTrue(excluiu, "Falha ao remover produto existente");

        // Verificar se o produto foi realmente desativado
        System.out.println("\nVerificando produto após exclusão...");
        Produto produtoExcluido = produtoDAO.buscar(p1.getId());
        System.out.println("Produto encontrado após exclusão: " + (produtoExcluido != null ? "Sim" : "Não"));
        assertNull(produtoExcluido, "Produto não deveria ser encontrado após exclusão");

        // Teste 2: Tentar remover produto já removido
        System.out.println("\nTeste 2: Tentando remover produto já removido...");
        boolean tentativaExclusao = produtoDAO.excluir(p1.getId());
        System.out.println("Resultado da tentativa de exclusão: " + (tentativaExclusao ? "Sucesso" : "Falha"));
        assertFalse(tentativaExclusao, "Não deveria remover produto já removido");

        // Teste 3: Remover produto com ID inválido
        System.out.println("\nTeste 3: Tentando remover produto com ID inválido...");
        boolean exclusaoInvalida = produtoDAO.excluir(-1);
        System.out.println("Resultado da exclusão com ID inválido: " + (exclusaoInvalida ? "Sucesso" : "Falha"));
        assertFalse(exclusaoInvalida, "Não deveria remover produto com ID inválido");
    }
} 