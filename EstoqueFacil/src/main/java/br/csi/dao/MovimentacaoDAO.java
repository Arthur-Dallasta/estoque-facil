package br.csi.dao;

import br.csi.model.Movimentacao;
import br.csi.util.ConectarBancoDados;

import java.sql.*;
import java.util.ArrayList;

public class MovimentacaoDAO {
    public boolean inserir(Movimentacao mov) {
        String sql = "INSERT INTO movimentacao (tipo, produto_id, usuario_id, quantidade, observacao) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConectarBancoDados.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, mov.getTipo());
            stmt.setInt(2, mov.getProdutoId());
            stmt.setInt(3, mov.getUsuarioId());
            stmt.setInt(4, mov.getQuantidade());
            stmt.setString(5, mov.getObservacao());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Movimentacao> listarPorProduto(int produtoId) {
        ArrayList<Movimentacao> lista = new ArrayList<>();
        String sql = "SELECT * FROM movimentacao WHERE produto_id = ? ORDER BY data_movimentacao DESC";
        try (Connection conn = ConectarBancoDados.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, produtoId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Movimentacao m = new Movimentacao();
                m.setId(rs.getInt("id"));
                m.setTipo(rs.getString("tipo"));
                m.setProdutoId(rs.getInt("produto_id"));
                m.setUsuarioId(rs.getInt("usuario_id"));
                m.setQuantidade(rs.getInt("quantidade"));
                m.setDataMovimentacao(rs.getTimestamp("data_movimentacao"));
                m.setObservacao(rs.getString("observacao"));
                lista.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
} 