import java.util.Scanner;

public class SistemaBiblioteca {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            // m1enu interativo
            System.out.println("\n=== Sistema de Biblioteca ===");
            System.out.println("1. Cadastrar livro");
            System.out.println("2. Consultar livros disponíveis");
            System.out.println("3. Cadastrar usuário");
            System.out.println("4. Pegar livro emprestado");
            System.out.println("5. Devolver livro");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1 -> {
                    // aadastrar livro
                    System.out.print("Título do livro: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Autor do livro: ");
                    String autor = scanner.nextLine();
                    System.out.print("Identificador do livro: ");
                    String identificador = scanner.nextLine();
                    Livro livro = new Livro(titulo, autor, identificador);
                    biblioteca.cadastrarLivro(livro);
                }
                case 2 -> {
                    // consultar livros disponiveis
                    biblioteca.consultarLivrosDisponiveis();
                }
                case 3 -> {
                    // cadastrar usuario
                    System.out.print("Nome do usuário: ");
                    String nome = scanner.nextLine();
                    System.out.print("ID do usuário: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    Usuario usuario = new Usuario(nome, id);
                    biblioteca.cadastrarUsuario(usuario);
                }
                case 4 -> {
                    // emprestar livro
                    System.out.print("ID do usuário: ");
                    int idUsuario = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Identificador do livro: ");
                    String identificadorLivro = scanner.nextLine();
                    biblioteca.emprestarLivro(idUsuario, identificadorLivro);
                }
                case 5 -> {
                    // devolver livro
                    System.out.print("ID do usuário: ");
                    int idUsuario = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Identificador do livro: ");
                    String identificadorLivro = scanner.nextLine();
                    biblioteca.devolverLivro(idUsuario, identificadorLivro);
                }
                case 0 -> System.out.println("Encerrando o sistema. Até logo!");
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);

        scanner.close();
    }
    
}
