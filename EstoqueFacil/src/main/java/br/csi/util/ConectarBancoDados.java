package br.csi.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectarBancoDados {
    private static final String URL = "jdbc:postgresql://localhost:5432/estoque_facil";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "1234";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver do PostgreSQL não encontrado", e);
        }
    }
} 