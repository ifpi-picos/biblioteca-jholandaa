package com.exemplo.biblioteca;
import java.util.Scanner;
import java.util.List;

public class SistemaBiblioteca {
    private static final Scanner scanner = new Scanner(System.in);
    private static final LivroDAO livroDAO = new LivroDAO();
    private static final UsuarioDAO usuarioDAO = new UsuarioDAO();
    private static final EmprestimoDAO emprestimoDAO = new EmprestimoDAO();

    public static void main(String[] args) {
        DatabaseConnection.createTables();
        System.out.println("\n=== Bem-vindo ao Sistema da Biblioteca ===");
        System.out.println("Antes de continuar, cadastre-se no sistema.");
        cadastrarUsuario();

        executar();
    }

    public static void executar() {
        while (true) {
            System.out.println("\n=== Sistema de Biblioteca ===");
            System.out.println("1. Cadastrar livro");
            System.out.println("2. Consultar livros disponíveis");
            System.out.println("3. Pegar livro emprestado");
            System.out.println("4. Devolver livro");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            
            int opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    cadastrarLivro();
                    break;
                case 2:
                    consultarLivrosDisponiveis();
                    break;
                case 3:
                    pegarLivroEmprestado();
                    break;
                case 4:
                    devolverLivro();
                    break;
                case 0:
                    System.out.println("Sistema encerrado.");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void cadastrarLivro() {
        System.out.print("Título do livro: ");
        String titulo = scanner.nextLine();
    
        System.out.print("Autor do livro: ");
        String autor = scanner.nextLine();
    
        System.out.print("Ano de publicação: ");
        int anoPublicacao = scanner.nextInt();
        scanner.nextLine(); 
    
        Livro livro = new Livro(0, titulo, autor, anoPublicacao);
        livroDAO.inserirLivro(livro);
    }
    

    private static void consultarLivrosDisponiveis() {
        List<Livro> livros = livroDAO.buscarLivrosDisponiveis();
        
        if (livros.isEmpty()) {
            System.out.println(" Nenhum livro disponível no momento.");
        } else {
            System.out.println("\nLivros disponíveis:");
            for (Livro livro : livros) {
                System.out.println("ID: " + livro.getId() + " | " +
                                   "Título: " + livro.getTitulo() + " | " +
                                   "Autor: " + livro.getAutor() + " | " +
                                   "Ano: " + livro.getAnoPublicacao());
            }
        }
    }

    private static void cadastrarUsuario() {
        System.out.print("Nome do usuário: ");
        String nome = scanner.nextLine();
    
        System.out.print("ID do usuário: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 
    
        System.out.print("Email do usuário: ");
        String email = scanner.nextLine();
    
        Usuario usuario = new Usuario(id, nome, email, "123456");
        usuarioDAO.inserirUsuario(usuario);
        
        System.out.println("Usuário cadastrado com sucesso!");
    }
    

    private static void pegarLivroEmprestado() {
        System.out.print("ID do usuário: ");
        int usuarioId = scanner.nextInt();

        System.out.print("ID do livro: ");
        int livroId = scanner.nextInt();
        scanner.nextLine();

        boolean sucesso = emprestimoDAO.emprestarLivro(usuarioId, livroId);

        if (sucesso) {
            System.out.println("Livro emprestado com sucesso!");
        } else {
            System.out.println("Não foi possível emprestar o livro. Verifique se está disponível.");
        }
    }

    private static void devolverLivro() {
        System.out.print("ID do usuário: ");
        int usuarioId = scanner.nextInt();

        System.out.print("ID do livro: ");
        int livroId = scanner.nextInt();
        scanner.nextLine();
        boolean sucesso = emprestimoDAO.devolverLivro(usuarioId, livroId);

        if (sucesso) {
            System.out.println("Livro devolvido com sucesso!");
        } else {
            System.out.println("Erro ao devolver o livro. Verifique os dados.");
        }
    }
}



