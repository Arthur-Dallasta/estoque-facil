package br.csi.dao;

import br.csi.model.Usuario;
import br.csi.util.ConectarBancoDados;

import java.sql.*;
import java.util.ArrayList;

public class UsuarioDAO {
    
    public boolean inserir(Usuario usuario) {
        String sql = "INSERT INTO usuario (nome, email, senha, telefone, nivel_acesso, ativo) VALUES (?, ?, ?, ?, ?, ?) RETURNING id";
        
        try (Connection conn = ConectarBancoDados.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getTelefone());
            stmt.setString(5, usuario.getNivelAcesso());
            stmt.setBoolean(6, usuario.isAtivo());
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                usuario.setId(rs.getInt(1));
                return true;
            }
            return false;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean atualizar(Usuario usuario) {
        String sql = "UPDATE usuario SET nome = ?, email = ?, telefone = ? WHERE id = ?";
        
        try (Connection conn = ConectarBancoDados.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getTelefone());
            stmt.setInt(4, usuario.getId());
            
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public Usuario autenticar(String email, String senha) {
        String sql = "SELECT * FROM usuario WHERE email = ? AND senha = ? AND ativo = true";
        
        try (Connection conn = ConectarBancoDados.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, email);
            stmt.setString(2, senha);
            
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setTelefone(rs.getString("telefone"));
                usuario.setNivelAcesso(rs.getString("nivel_acesso"));
                usuario.setAtivo(rs.getBoolean("ativo"));
                return usuario;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    public ArrayList<Usuario> listar() {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuario WHERE ativo = true";
        
        try (Connection conn = ConectarBancoDados.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setTelefone(rs.getString("telefone"));
                usuario.setNivelAcesso(rs.getString("nivel_acesso"));
                usuario.setAtivo(rs.getBoolean("ativo"));
                usuarios.add(usuario);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return usuarios;
    }
    
    public Usuario buscar(int id) {
        String sql = "SELECT * FROM usuario WHERE id = ? AND ativo = true";
        
        try (Connection conn = ConectarBancoDados.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setTelefone(rs.getString("telefone"));
                usuario.setNivelAcesso(rs.getString("nivel_acesso"));
                usuario.setAtivo(rs.getBoolean("ativo"));
                return usuario;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    public boolean excluir(int id) {
        String sql = "UPDATE usuario SET ativo = false WHERE id = ?";
        
        try (Connection conn = ConectarBancoDados.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
