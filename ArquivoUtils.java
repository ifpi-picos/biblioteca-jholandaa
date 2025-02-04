import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class ArquivoUtils {
    
    // Salvar como TXT
    public static void salvarComoTXT(String nomeArquivo, List<Livro> livros, List<Usuario> usuarios) {
        try (FileWriter writer = new FileWriter(nomeArquivo + ".txt")) {
            writer.write("=== Livros ===\n");
            for (Livro livro : livros) {
                writer.write("Título: " + livro.getTitulo() + " | Autor: " + livro.getAutor() + " | ID: " + livro.getIdentificador() + "\n");
            }
            writer.write("\n=== Usuários ===\n");
            for (Usuario usuario : usuarios) {
                writer.write("Nome: " + usuario.getNome() + " | ID: " + usuario.getId() + "\n");
            }
            writer.close();
            System.out.println("✅ Arquivo salvo como " + nomeArquivo + ".txt");
        } catch (IOException e) {
            System.err.println("❌ Erro ao salvar TXT: " + e.getMessage());
        }
    }

    // Salvar como XML
    public static void salvarComoXML(String nomeArquivo, List<Livro> livros, List<Usuario> usuarios) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            Element root = document.createElement("Biblioteca");
            document.appendChild(root);

            Element livrosElement = document.createElement("Livros");
            root.appendChild(livrosElement);
            for (Livro livro : livros) {
                Element livroElement = document.createElement("Livro");
                
                Element titulo = document.createElement("Titulo");
                titulo.appendChild(document.createTextNode(livro.getTitulo()));
                livroElement.appendChild(titulo);

                Element autor = document.createElement("Autor");
                autor.appendChild(document.createTextNode(livro.getAutor()));
                livroElement.appendChild(autor);

                Element id = document.createElement("ID");
                id.appendChild(document.createTextNode(livro.getIdentificador()));
                livroElement.appendChild(id);

                livrosElement.appendChild(livroElement);
            }

            Element usuariosElement = document.createElement("Usuarios");
            root.appendChild(usuariosElement);
            for (Usuario usuario : usuarios) {
                Element usuarioElement = document.createElement("Usuario");

                Element nome = document.createElement("Nome");
                nome.appendChild(document.createTextNode(usuario.getNome()));
                usuarioElement.appendChild(nome);

                Element id = document.createElement("ID");
                id.appendChild(document.createTextNode(String.valueOf(usuario.getId())));
                usuarioElement.appendChild(id);

                usuariosElement.appendChild(usuarioElement);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(nomeArquivo + ".xml"));

            transformer.transform(source, result);
            System.out.println("✅ Arquivo salvo como " + nomeArquivo + ".xml");

        } catch (Exception e) {
            System.err.println("❌ Erro ao salvar XML: " + e.getMessage());
        }
    }
}

