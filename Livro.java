public class Livro {
    private String titulo;
    private String autor;
    private String identificador; //para cada livro
    private boolean disponivel;

    public Livro(String titulo, String autor, String identificador) {
        this.titulo = titulo;
        this.autor = autor;
        this.identificador = identificador;
        this.disponivel = true; //todo livro é disponivel no inicio
    }
    public String getTitulo(){
        return titulo;
    }
public void setTitutlo(String titulo){
    this.titulo = titulo;
}
public String getAutor(){
    return autor;
}
public void setAutor(String autor){
    this.autor = autor;
}
public String getIdentificador(){
    return identificador;
}
public void setIdentificador(String identificador){
    this.identificador = identificador;
}
public boolean isDisponivel(){
    return disponivel;
}
public void setDisponivel(boolean disponivel){
    this.disponivel = disponivel;
}
//alguns metodos adicionais
public void alterarStatus(boolean novoStatus){
    this.disponivel = novoStatus;
}

@Override
public String toString(){
    return "Livro{" +
    "Titulo= " + titulo + '\'' +
    ", autor= '" + autor + '\'' +
    ", identidicador='" + identificador + '\'' +
    ", disponivel=" + (disponivel ? "Sim" : "Não") + '}';
}

}