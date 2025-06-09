package br.csi.controller;

import br.csi.model.Usuario;
import br.csi.service.UsuarioService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.RequestDispatcher;

import java.io.IOException;

@WebServlet("/usuario")
public class UsuarioServlet extends HttpServlet {
    
    private UsuarioService usuarioService;
    
    @Override
    public void init() throws ServletException {
        usuarioService = new UsuarioService();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if (action == null) {
            action = "form";
        }
        
        switch (action) {
            case "form":
                mostrarFormulario(request, response);
                break;
            case "logout":
                fazerLogout(request, response);
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
                cadastrarUsuario(request, response);
                break;
            case "atualizar":
                atualizarUsuario(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Ação inválida");
        }
    }
    
    private void mostrarFormulario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("cadastro.jsp");
        dispatcher.forward(request, response);
    }
    
    private void cadastrarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Usuario usuario = new Usuario();
        usuario.setNome(request.getParameter("nome"));
        usuario.setEmail(request.getParameter("email"));
        usuario.setSenha(request.getParameter("senha"));
        usuario.setTelefone(request.getParameter("telefone"));
        usuario.setNivelAcesso("FUNCIONARIO"); // Por padrão, novos usuários são funcionários
        usuario.setAtivo(true);
        
        if (usuarioService.cadastrarUsuario(usuario)) {
            response.sendRedirect("login.jsp");
        } else {
            request.setAttribute("erro", "Erro ao cadastrar usuário");
            RequestDispatcher dispatcher = request.getRequestDispatcher("cadastro.jsp");
            dispatcher.forward(request, response);
        }
    }
    
    private void atualizarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        
        if (usuario == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        usuario.setNome(request.getParameter("nome"));
        usuario.setTelefone(request.getParameter("telefone"));
        
        if (usuarioService.atualizarUsuario(usuario)) {
            session.setAttribute("usuario", usuario);
            response.sendRedirect("index.jsp");
        } else {
            request.setAttribute("erro", "Erro ao atualizar usuário");
            RequestDispatcher dispatcher = request.getRequestDispatcher("perfil.jsp");
            dispatcher.forward(request, response);
        }
    }
    
    private void fazerLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect("login.jsp");
    }
}
