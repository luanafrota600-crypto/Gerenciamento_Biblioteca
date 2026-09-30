package biblioteca.servico;

import biblioteca.modelo.Livro;
import biblioteca.repositorio.LivroRepositorio;
import java.util.List;

public class LivroServico {
    private LivroRepositorio livroRepositorio;

    public LivroServico() {
        this.livroRepositorio = new LivroRepositorio();
    }

    public boolean cadastrarLivro(String titulo, String autor, String isbn, int ano, String editora, int quantidade) {
        if (livroRepositorio.existeIsbn(isbn)) {
            System.out.println("Erro: Já existe um livro com este ISBN!");
            return false;
        }

        Livro livro = new Livro(titulo, autor, isbn, ano, editora, quantidade);
        livroRepositorio.adicionar(livro);
        System.out.println("Livro cadastrado com sucesso!");
        return true;
    }

    public Livro consultarLivroPorIsbn(String isbn) {
        Livro livro = livroRepositorio.buscarPorIsbn(isbn);
        if (livro == null) {
            System.out.println("Livro não encontrado!");
        }
        return livro;
    }

    public void listarTodosLivros() {
        List<Livro> livros = livroRepositorio.listarTodos();
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado.");
        } else {
            System.out.println("\n=== LISTA DE LIVROS ===");
            for (Livro livro : livros) {
                System.out.println(livro);
            }
        }
    }

    public boolean removerLivro(String isbn) {
        if (livroRepositorio.remover(isbn)) {
            System.out.println("Livro removido com sucesso!");
            return true;
        }
        System.out.println("Livro não encontrado!");
        return false;
    }

    public void buscarLivrosPorAutor(String autor) {
        List<Livro> livros = livroRepositorio.buscarPorAutor(autor);
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro encontrado para o autor: " + autor);
        } else {
            System.out.println("\n=== LIVROS DO AUTOR: " + autor + " ===");
            for (Livro livro : livros) {
                System.out.println(livro);
            }
        }
    }

    public LivroRepositorio getLivroRepositorio() {
        return livroRepositorio;
    }
}