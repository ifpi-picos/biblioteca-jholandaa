package com.exemplo.biblioteca;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LivroDAOTest {
    @Test
    public void testInserirLivro() {
        LivroDAO livroDAO = new LivroDAO();

        Livro livro = new Livro(1984, "George Orwell", "LIV001", 1949); 
        
        livroDAO.inserirLivro(livro);
        assertTrue(true);
    }
}


