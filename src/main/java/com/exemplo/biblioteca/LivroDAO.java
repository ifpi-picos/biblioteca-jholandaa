package com.exemplo.biblioteca;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {
    public void inserirLivro(Livro livro) {
        String sql = "INSERT INTO livros (titulo, autor, ano_publicacao, disponivel) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, livro.getTitulo());
            pstmt.setString(2, livro.getAutor());
            pstmt.setInt(3, livro.getAnoPublicacao());
            pstmt.setBoolean(4, true);

            pstmt.executeUpdate();
            System.out.println("Livro cadastrado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar livro: " + e.getMessage());
        }
    }

    public List<Livro> buscarLivrosDisponiveis() {
        String sql = "SELECT * FROM livros WHERE disponivel = TRUE";
        List<Livro> livros = new ArrayList<>();
    
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
    
            while (rs.next()) {
                Livro livro = new Livro(
                    rs.getInt("id"),
                    rs.getString("titulo"),
                    rs.getString("autor"),
                    rs.getInt("ano_publicacao")
                );
                livros.add(livro);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar livros disponíveis: " + e.getMessage());
        }
    
        return livros;
    }
    
}






