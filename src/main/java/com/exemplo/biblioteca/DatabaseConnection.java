package com.exemplo.biblioteca;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
public class DatabaseConnection {
    private static Connection connection;

    static {
        reconnect();
    }

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                reconnect();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar conexão com o banco de dados.", e);
        }
        return connection;
    }

    private static void reconnect() {
        Properties properties = new Properties();
        try (InputStream input = DatabaseConnection.class.getClassLoader().getResourceAsStream("database.properties")) {
            if (input == null) {
                throw new RuntimeException("Arquivo database.properties não encontrado!");
            }
            properties.load(input);

            String url = properties.getProperty("database.url");
            String username = properties.getProperty("database.username");
            String password = properties.getProperty("database.password");
            String driver = properties.getProperty("database.driver");

            if (url == null || username == null || password == null || driver == null) {
                throw new RuntimeException("Propriedades obrigatórias não encontradas no arquivo database.properties!");
            }

            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Conectado ao PostgreSQL com sucesso!");

        } catch (Exception e) {
            throw new RuntimeException("Erro ao conectar ao banco de dados.", e);
        }
    }

    public static void createTables() {
        
            String sqlLivros = "CREATE TABLE IF NOT EXISTS livros (" +
                               "id SERIAL PRIMARY KEY, " +
                               "titulo VARCHAR(255) NOT NULL, " +
                               "autor VARCHAR(255) NOT NULL, " +
                               "ano_publicacao INT, " +
                               "disponivel BOOLEAN DEFAULT TRUE" + 
                               ");";
        
            String sqlUsuarios = "CREATE TABLE IF NOT EXISTS usuarios (" +
                                 "id SERIAL PRIMARY KEY, " +
                                 "nome VARCHAR(255) NOT NULL" +
                                 ");";
        
             String sqlEmprestimos = "CREATE TABLE IF NOT EXISTS emprestimos (" +
                                "id SERIAL PRIMARY KEY, " +
                                 "usuario_id INT REFERENCES usuarios(id), " +
                                 "livro_id INT REFERENCES livros(id), " +
                                 "data_emprestimo TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                                 "data_devolucao TIMESTAMP NULL" +
                                 ");";
         
        
            String alterarLivros = "ALTER TABLE livros ADD COLUMN IF NOT EXISTS disponivel BOOLEAN DEFAULT TRUE;";
        
            try (Connection conn = getConnection();
                 Statement stmt = conn.createStatement()) {
                stmt.execute(sqlLivros);
                stmt.execute(sqlUsuarios);
                stmt.execute(sqlEmprestimos);
                stmt.execute(alterarLivros); // Garante que a coluna 'disponivel' exista
                System.out.println("tabelas criadas e atualizadas com sucesso!");
            } catch (SQLException e) {
                System.err.println("erro ao criar/atualizar as tabelas: " + e.getMessage());
            }
        }
        
}
