import java.util.ArrayList;
import java.util.List;

public class Usuario{
    private String nome;
    private int id; //identificador do usuario
    private List<Livro> livrosEmprestados; //lista dos livros emprestados

    public Usuario(String nome, int id){
        this.nome = nome;
        this.id = id;
        this.livrosEmprestados = new ArrayList<>(); //começa a lista vazia
    }
    public String getNome(){
        return nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public List<Livro> getLivrosEmprestados(){
        return livrosEmprestados;
    }

    public void adicionarLivroEmprestado(Livro livro){
        if (livro.isDisponivel()){
            livrosEmprestados.add(livro);
            livro.setDisponivel(false); //muda o livro p indisponivel
    } else {
        System.out.println("O livro " + livro.getTitulo() + "Não está disponivel.");
    }
}
public void removerLivroEmprestado( Livro livro) {
    if (livrosEmprestados.contains(livro)) {
        livrosEmprestados.remove(livro);
        livro.setDisponivel(true); // deixa o livro disponivel dnv
    } else {
        System.out.println("O livro " + livro.getTitulo() + "não está na lista de empréstimos.");
    }
}

    @Override
    public String toString() {
        return "Usuario{" +
        "nome=" + nome + '\'' +
        ", id=" + id +
        ", livrosEmprestados=" + livrosEmprestados +
        '}';
    }


}

