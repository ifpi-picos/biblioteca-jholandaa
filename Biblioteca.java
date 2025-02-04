import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private List<Livro> livros;
    private List<Usuario> usuarios;

    public Biblioteca() {
        this.livros = new ArrayList<>();
        this.usuarios = new ArrayList<>();
    }

    public void cadastrarLivro(Livro livro) {
        livros.add(livro);
        System.out.println("Livro cadastrado: " + livro.getTitulo());
    }

    public void consultarLivrosDisponiveis() {
        System.out.println("Livros disponíveis: ");
        for (Livro livro : livros) {
            if (livro.isDisponivel()) {
                System.out.println(livro);
            }
        }
    }

    public void cadastrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
        System.out.println("Usuário cadastrado: " + usuario.getNome());
    }

    public void emprestarLivro(int idUsuario, String identificadorLivro) {
        Usuario usuario = buscarUsuarioPorId(idUsuario);
        Livro livro = buscarLivroPorIdentificador(identificadorLivro);

        if (usuario != null && livro != null && livro.isDisponivel()) {
            usuario.adicionarLivroEmprestado(livro);
            livro.setDisponivel(false);
            System.out.println("Livro emprestado: " + livro.getTitulo() + " para o usuário: " + usuario.getNome());
        } else {
            System.out.println("Usuário não encontrado ou livro indisponível.");
        }
    }

    public void devolverLivro(int idUsuario, String identificadorLivro) {
        Usuario usuario = buscarUsuarioPorId(idUsuario);
        Livro livro = buscarLivroPorIdentificador(identificadorLivro);

        if (usuario != null && livro != null && usuario.getLivrosEmprestados().contains(livro)) {
            usuario.removerLivroEmprestado(livro);
            livro.setDisponivel(true);
            System.out.println("Livro devolvido: " + livro.getTitulo() + " pelo usuário: " + usuario.getNome());
        } else {
            System.out.println("Usuário ou livro não encontrado.");
        }
    }

    private Usuario buscarUsuarioPorId(int id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId() == id) {
                return usuario;
            }
        }
        return null;
    }

    private Livro buscarLivroPorIdentificador(String identificador) {
        for (Livro livro : livros) {
            if (livro.getIdentificador().equals(identificador)) {
                return livro;
            }
        }
        return null;
    }

    
    public List<Livro> getLivros() {
        return livros;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }
}
