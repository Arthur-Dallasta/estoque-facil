package br.csi.dao;

import br.csi.model.Produto;
import br.csi.util.ConectarBancoDados;

import java.sql.*;
import java.util.ArrayList;

public class ProdutoDAO {
    
    public boolean inserir(Produto produto) {
        String sql = "INSERT INTO produto (nome, descricao, preco, quantidade, categoria, ativo) VALUES (?, ?, ?, ?, ?, ?) RETURNING id";
        
        try (Connection conn = ConectarBancoDados.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDescricao());
            stmt.setDouble(3, produto.getPreco());
            stmt.setInt(4, produto.getQuantidade());
            stmt.setString(5, produto.getCategoria());
            stmt.setBoolean(6, produto.isAtivo());
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                produto.setId(rs.getInt(1));
                return true;
            }
            return false;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean atualizar(Produto produto) {
        String sql = "UPDATE produto SET nome = ?, descricao = ?, preco = ?, quantidade = ?, categoria = ? WHERE id = ?";
        
        try (Connection conn = ConectarBancoDados.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDescricao());
            stmt.setDouble(3, produto.getPreco());
            stmt.setInt(4, produto.getQuantidade());
            stmt.setString(5, produto.getCategoria());
            stmt.setInt(6, produto.getId());
            
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean excluir(int id) {
        String sql = "UPDATE produto SET ativo = false WHERE id = ? AND ativo = true";
        
        try (Connection conn = ConectarBancoDados.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public Produto buscar(int id) {
        String sql = "SELECT * FROM produto WHERE id = ? AND ativo = true";
        
        try (Connection conn = ConectarBancoDados.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setQuantidade(rs.getInt("quantidade"));
                produto.setCategoria(rs.getString("categoria"));
                produto.setAtivo(rs.getBoolean("ativo"));
                return produto;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    public ArrayList<Produto> listar() {
        ArrayList<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produto WHERE ativo = true";
        
        try (Connection conn = ConectarBancoDados.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setQuantidade(rs.getInt("quantidade"));
                produto.setCategoria(rs.getString("categoria"));
                produto.setAtivo(rs.getBoolean("ativo"));
                produtos.add(produto);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return produtos;
    }
} 