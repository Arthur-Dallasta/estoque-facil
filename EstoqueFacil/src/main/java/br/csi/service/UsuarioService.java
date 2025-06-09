package br.csi.service;

import br.csi.dao.UsuarioDAO;
import br.csi.model.Usuario;

import java.util.ArrayList;

public class UsuarioService {
    
    private UsuarioDAO usuarioDAO;
    
    public UsuarioService() {
        this.usuarioDAO = new UsuarioDAO();
    }
    
    public boolean cadastrarUsuario(Usuario usuario) {
        return usuarioDAO.inserir(usuario);
    }
    
    public boolean atualizarUsuario(Usuario usuario) {
        return usuarioDAO.atualizar(usuario);
    }
    
    public Usuario autenticar(String email, String senha) {
        return usuarioDAO.autenticar(email, senha);
    }
    
    public ArrayList<Usuario> listar() {
        return usuarioDAO.listar();
    }
    
    public Usuario buscarUsuario(int id) {
        return usuarioDAO.buscar(id);
    }
    
    public boolean excluirUsuario(int id) {
        return usuarioDAO.excluir(id);
    }
}
