package com.exemplo.biblioteca;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmprestimoDAO {

    public boolean emprestarLivro(int usuarioId, int livroId) {
        String verificarUsuario = "SELECT id FROM usuarios WHERE id = ?";
        String verificarDisponibilidade = "SELECT disponivel FROM livros WHERE id = ?";
        String emprestarLivro = "INSERT INTO emprestimos (usuario_id, livro_id) VALUES (?, ?)";
        String atualizarLivro = "UPDATE livros SET disponivel = FALSE WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement checkUserStmt = conn.prepareStatement(verificarUsuario);
             PreparedStatement checkStmt = conn.prepareStatement(verificarDisponibilidade);
             PreparedStatement insertStmt = conn.prepareStatement(emprestarLivro);
             PreparedStatement updateStmt = conn.prepareStatement(atualizarLivro)) {

            checkUserStmt.setInt(1, usuarioId);
            ResultSet rsUser = checkUserStmt.executeQuery();
            if (!rsUser.next()) {
                System.out.println(" Usuário não encontrado!");
                return false;
            }

            checkStmt.setInt(1, livroId);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next() && !rs.getBoolean("disponivel")) {
                System.out.println("O livro já está emprestado!");
                return false;
            }

            insertStmt.setInt(1, usuarioId);
            insertStmt.setInt(2, livroId);
            insertStmt.executeUpdate();

            updateStmt.setInt(1, livroId);
            updateStmt.executeUpdate();

            System.out.println("Empréstimo realizado com sucesso!");
            return true;

        } catch (SQLException e) {
            System.err.println("Erro ao emprestar livro: " + e.getMessage());
            return false;
        }
    }

    public boolean devolverLivro(int usuarioId, int livroId) {
        String verificarEmprestimo = "SELECT * FROM emprestimos WHERE usuario_id = ? AND livro_id = ? AND data_devolucao IS NULL";
        String atualizarEmprestimo = "UPDATE emprestimos SET data_devolucao = CURRENT_TIMESTAMP WHERE usuario_id = ? AND livro_id = ?";
        String atualizarLivro = "UPDATE livros SET disponivel = TRUE WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(verificarEmprestimo);
             PreparedStatement updateEmprestimoStmt = conn.prepareStatement(atualizarEmprestimo);
             PreparedStatement updateLivroStmt = conn.prepareStatement(atualizarLivro)) {

            checkStmt.setInt(1, usuarioId);
            checkStmt.setInt(2, livroId);
            ResultSet rs = checkStmt.executeQuery();
            if (!rs.next()) {
                System.out.println(" Nenhum empréstimo encontrado para este usuário e livro.");
                return false;
            }

            updateEmprestimoStmt.setInt(1, usuarioId);
            updateEmprestimoStmt.setInt(2, livroId);
            updateEmprestimoStmt.executeUpdate();

            updateLivroStmt.setInt(1, livroId);
            updateLivroStmt.executeUpdate();

            System.out.println("Livro devolvido com sucesso!");
            return true;

        } catch (SQLException e) {
            System.err.println("Erro ao devolver livro: " + e.getMessage());
            return false;
        }
    }
}
