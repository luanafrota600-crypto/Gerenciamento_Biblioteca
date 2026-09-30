package biblioteca.repositorio;

import biblioteca.modelo.Livro;
import java.util.*;

public class LivroRepositorio {
    // Map simulando banco de dados
    private Map<String, Livro> livros;

    public LivroRepositorio() {
        this.livros = new HashMap<>();
    }

    public void adicionar(Livro livro) {
        livros.put(livro.getIsbn(), livro);
    }

    public Livro buscarPorIsbn(String isbn) {
        return livros.get(isbn);
    }

    public List<Livro> listarTodos() {
        return new ArrayList<>(livros.values());
    }

    public boolean remover(String isbn) {
        return livros.remove(isbn) != null;
    }

    public List<Livro> buscarPorAutor(String autor) {
        List<Livro> resultado = new ArrayList<>();
        for (Livro livro : livros.values()) {
            if (livro.getAutor().toLowerCase().contains(autor.toLowerCase())) {
                resultado.add(livro);
            }
        }
        return resultado;
    }

    public boolean existeIsbn(String isbn) {
        return livros.containsKey(isbn);
    }
}