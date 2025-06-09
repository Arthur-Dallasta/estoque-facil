package br.csi.controller;

import br.csi.model.Produto;
import br.csi.service.ProdutoService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;

import java.io.IOException;
import java.util.List;

@WebServlet("/produto")
public class ProdutoServlet extends HttpServlet {
    
    private ProdutoService produtoService;
    
    @Override
    public void init() throws ServletException {
        produtoService = new ProdutoService();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if (action == null) {
            action = "listar";
        }
        
        switch (action) {
            case "listar":
                listarProdutos(request, response);
                break;
            case "form":
                mostrarFormulario(request, response);
                break;
            case "editar":
                editarProduto(request, response);
                break;
            case "excluir":
                excluirProduto(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Ação inválida");
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if (action == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Ação não especificada");
            return;
        }
        
        switch (action) {
            case "cadastrar":
                cadastrarProduto(request, response);
                break;
            case "atualizar":
                atualizarProduto(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Ação inválida");
        }
    }
    
    private void listarProdutos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Produto> produtos = produtoService.listarProdutos();
        request.setAttribute("produtos", produtos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("produtos.jsp");
        dispatcher.forward(request, response);
    }
    
    private void mostrarFormulario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("cadastro-produto.jsp");
        dispatcher.forward(request, response);
    }
    
    private void editarProduto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Produto produto = produtoService.buscarProduto(id);
        
        if (produto != null) {
            request.setAttribute("produto", produto);
            RequestDispatcher dispatcher = request.getRequestDispatcher("editar-produto.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Produto não encontrado");
        }
    }
    
    private void excluirProduto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        
        if (produtoService.excluirProduto(id)) {
            response.sendRedirect("produto?action=listar");
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao excluir produto");
        }
    }
    
    private void cadastrarProduto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Produto produto = new Produto();
        produto.setNome(request.getParameter("nome"));
        produto.setDescricao(request.getParameter("descricao"));
        produto.setPreco(Double.parseDouble(request.getParameter("preco")));
        produto.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));
        produto.setCategoria(request.getParameter("categoria"));
        
        if (produtoService.cadastrarProduto(produto)) {
            response.sendRedirect("produto?action=listar");
        } else {
            request.setAttribute("erro", "Erro ao cadastrar produto");
            RequestDispatcher dispatcher = request.getRequestDispatcher("cadastro-produto.jsp");
            dispatcher.forward(request, response);
        }
    }
    
    private void atualizarProduto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Produto produto = new Produto();
        produto.setId(Integer.parseInt(request.getParameter("id")));
        produto.setNome(request.getParameter("nome"));
        produto.setDescricao(request.getParameter("descricao"));
        produto.setPreco(Double.parseDouble(request.getParameter("preco")));
        produto.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));
        produto.setCategoria(request.getParameter("categoria"));
        
        if (produtoService.atualizarProduto(produto)) {
            response.sendRedirect("produto?action=listar");
        } else {
            request.setAttribute("erro", "Erro ao atualizar produto");
            request.setAttribute("produto", produto);
            RequestDispatcher dispatcher = request.getRequestDispatcher("editar-produto.jsp");
            dispatcher.forward(request, response);
        }
    }
} 