package com.exemplo.biblioteca;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LivroDAOTest {
    @Test
    public void testInserirLivro() {
        LivroDAO livroDAO = new LivroDAO();

        // Criando um objeto Livro corretamente
        Livro livro = new Livro(1984, "George Orwell", "LIV001", 1949); 
        
        // Chamando o método corretamente
        livroDAO.inserirLivro(livro);

        // Se chegou até aqui sem erros, o teste passou
        assertTrue(true);
    }
}


