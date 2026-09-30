package biblioteca.servico;

import biblioteca.modelo.Emprestimo;
import biblioteca.modelo.Livro;
import biblioteca.modelo.Usuario;
import biblioteca.repositorio.LivroRepositorio;
import biblioteca.repositorio.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoServico {
    private LivroRepositorio livroRepositorio;
    private UsuarioRepositorio usuarioRepositorio;
    private List<Emprestimo> emprestimosAtivos;
    private List<Emprestimo> historicoEmprestimos;

    public EmprestimoServico(LivroRepositorio livroRepositorio, UsuarioRepositorio usuarioRepositorio) {
        this.livroRepositorio = livroRepositorio;
        this.usuarioRepositorio = usuarioRepositorio;
        this.emprestimosAtivos = new ArrayList<>();
        this.historicoEmprestimos = new ArrayList<>();
    }

    public boolean realizarEmprestimo(String isbn, String cpf) {
        Livro livro = livroRepositorio.buscarPorIsbn(isbn);
        Usuario usuario = usuarioRepositorio.buscarPorCpf(cpf);

        if (livro == null) {
            System.out.println("Erro: Livro não encontrado!");
            return false;
        }

        if (usuario == null) {
            System.out.println("Erro: Usuário não encontrado!");
            return false;
        }

        // Verificar se tem exemplar disponível
        if (!livro.isDisponivel()) {
            System.out.println("Erro: Não há exemplares disponíveis deste livro!");
            return false;
        }

        // Realizar empréstimo
        Emprestimo emprestimo = new Emprestimo(livro, usuario);
        livro.decrementarDisponivel();
        emprestimosAtivos.add(emprestimo);
        historicoEmprestimos.add(emprestimo);
        usuario.adicionarEmprestimo(emprestimo);

        System.out.println("Empréstimo realizado com sucesso!");
        return true;
    }

    public boolean registrarDevolucao(String isbn, String cpf) {
        for (Emprestimo emprestimo : emprestimosAtivos) {
            if (emprestimo.getLivro().getIsbn().equals(isbn) &&
                    emprestimo.getUsuario().getCpf().equals(cpf) &&
                    !emprestimo.isDevolvido()) {

                emprestimo.registrarDevolucao();
                emprestimosAtivos.remove(emprestimo);
                System.out.println("Devolução registrada com sucesso!");
                return true;
            }
        }
        System.out.println("Erro: Empréstimo não encontrado!");
        return false;
    }

    public void listarLivrosEmprestados() {
        if (emprestimosAtivos.isEmpty()) {
            System.out.println("Nenhum livro emprestado no momento.");
        } else {
            System.out.println("\n=== LIVROS EMPRESTADOS ===");
            for (Emprestimo emprestimo : emprestimosAtivos) {
                System.out.println(emprestimo);
            }
        }
    }

    public void listarLivrosDisponiveis() {
        List<Livro> todosLivros = livroRepositorio.listarTodos();
        System.out.println("\n=== LIVROS DISPONÍVEIS ===");
        boolean encontrou = false;

        for (Livro livro : todosLivros) {
            if (livro.isDisponivel()) {
                System.out.println(livro);
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhum livro disponível no momento.");
        }
    }

    public void exibirHistoricoGeral() {
        if (historicoEmprestimos.isEmpty()) {
            System.out.println("Nenhum empréstimo realizado ainda.");
        } else {
            System.out.println("\n=== HISTÓRICO COMPLETO DE EMPRÉSTIMOS ===");
            for (Emprestimo emprestimo : historicoEmprestimos) {
                System.out.println(emprestimo);
            }
        }
    }
}