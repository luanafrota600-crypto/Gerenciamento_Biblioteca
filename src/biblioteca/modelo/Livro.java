package biblioteca.modelo;

import java.time.Year;
import java.util.Objects;

public class Livro {
    // Atributos privados (encapsulamento)
    private String titulo;
    private String autor;
    private String isbn;
    private int ano;
    private String editora;
    private int quantidade;
    private int quantidadeDisponivel;

    // Atributo estático para contador
    private static int totalLivrosCadastrados = 0;

    // Construtor padrão
    public Livro() {
        totalLivrosCadastrados++;
    }

    // Construtor sobrecarregado
    public Livro(String titulo, String autor, String isbn, int ano, String editora, int quantidade) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.ano = ano;
        this.editora = editora;
        this.quantidade = quantidade;
        this.quantidadeDisponivel = quantidade;
        totalLivrosCadastrados++;
    }

    // Métodos acessores (getters/setters)
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public void setQuantidadeDisponivel(int quantidadeDisponivel) {
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    // Método estático
    public static int getTotalLivrosCadastrados() {
        return totalLivrosCadastrados;
    }

    // Métodos de negócio
    public boolean decrementarDisponivel() {
        if (quantidadeDisponivel > 0) {
            quantidadeDisponivel--;
            return true;
        }
        return false;
    }

    public void incrementarDisponivel() {
        if (quantidadeDisponivel < quantidade) {
            quantidadeDisponivel++;
        }
    }

    public boolean isDisponivel() {
        return quantidadeDisponivel > 0;
    }

    @Override
    public String toString() {
        return String.format("Livro{titulo='%s', autor='%s', isbn='%s', ano=%d, editora='%s', disponiveis=%d/%d}",
                titulo, autor, isbn, ano, editora, quantidadeDisponivel, quantidade);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Livro livro = (Livro) o;
        return Objects.equals(isbn, livro.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }
}