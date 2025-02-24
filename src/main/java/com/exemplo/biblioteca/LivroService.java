package com.exemplo.biblioteca;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LivroService {
    public static void listarLivros() {
        String sql = "SELECT * FROM livros";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            System.out.println("Lista de Livros:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                                   " | Título: " + rs.getString("titulo") +
                                   " | Autor: " + rs.getString("autor") +
                                   " | Ano: " + rs.getInt("ano_publicacao"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar livros: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        listarLivros();
    }
}
