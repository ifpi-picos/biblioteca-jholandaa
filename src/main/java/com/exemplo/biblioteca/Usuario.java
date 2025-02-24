package com.exemplo.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private int id;
    private String nome;
    private List<Livro> livrosEmprestados;
    private String email; 
    private String senha;

    public Usuario(int id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.livrosEmprestados = new ArrayList<>();
        this.email = email;
        this.senha = senha;
    }
    public String getEmail() {
        return email;
    }
public String getSenha() {
        return senha;
    }
    public int getId() { return id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public List<Livro> getLivrosEmprestados() { return livrosEmprestados; }

    public void adicionarLivroEmprestado(Livro livro) {
        if (livro.isDisponivel()) {
            livrosEmprestados.add(livro);
            livro.setDisponivel(false);
        } else {
            System.out.println("O livro " + livro.getTitulo() + " não está disponível.");
        }
    }

    public void removerLivroEmprestado(Livro livro) {
        if (livrosEmprestados.contains(livro)) {
            livrosEmprestados.remove(livro);
            livro.setDisponivel(true);
        } else {
            System.out.println("O livro " + livro.getTitulo() + " não está na lista de empréstimos.");
        }
    }

    @Override
    public String toString() {
        return "Usuário{" +
                "ID=" + id +
                ", Nome='" + nome + '\'' +
                ", Livros Emprestados=" + livrosEmprestados +
                '}';
    }
}


