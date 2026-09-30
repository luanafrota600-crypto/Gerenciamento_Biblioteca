package biblioteca.aplicacao;

import biblioteca.servico.*;
import biblioteca.modelo.*;
import java.util.Scanner;

public class Main {
    private static LivroServico livroServico = new LivroServico();
    private static UsuarioServico usuarioServico = new UsuarioServico();
    private static EmprestimoServico emprestimoServico = new EmprestimoServico(
            livroServico.getLivroRepositorio(),
            usuarioServico.getUsuarioRepositorio()
    );
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;

        do {
            exibirMenu();
            System.out.print("Escolha uma opção: ");
            opcao = lerInteiro();

            processarOpcao(opcao);

        } while (opcao != 0);

        System.out.println("\nSistema encerrado. Total de livros cadastrados: " + Livro.getTotalLivrosCadastrados());
        scanner.close();
    }

    // Lê um inteiro sem quebrar o programa se o usuário digitar texto
    private static int lerInteiro() {
        while (true) {
            String linha = scanner.nextLine().trim();
            try {
                return Integer.parseInt(linha);
            } catch (NumberFormatException e) {
                System.out.print("Valor inválido. Digite um número: ");
            }
        }
    }

    private static void exibirMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("        GERENCIADOR DE BIBLIOTECA");
        System.out.println("=".repeat(50));
        System.out.println("1 - Cadastrar Livro");
        System.out.println("2 - Consultar Livro por ISBN");
        System.out.println("3 - Listar todos os Livros");
        System.out.println("4 - Remover Livro");
        System.out.println("5 - Buscar Livros por Autor");
        System.out.println("6 - Cadastrar Usuário");
        System.out.println("7 - Consultar Usuário por CPF");
        System.out.println("8 - Listar todos os Usuários");
        System.out.println("9 - Remover Usuário");
        System.out.println("10 - Realizar Empréstimo");
        System.out.println("11 - Registrar Devolução");
        System.out.println("12 - Listar Livros Emprestados");
        System.out.println("13 - Listar Livros Disponíveis");
        System.out.println("14 - Exibir Histórico de Empréstimos");
        System.out.println("0 - Sair");
        System.out.println("=".repeat(50));
    }

    private static void processarOpcao(int opcao) {
        switch (opcao) {
            case 1:
                cadastrarLivro();
                break;
            case 2:
                consultarLivro();
                break;
            case 3:
                livroServico.listarTodosLivros();
                break;
            case 4:
                removerLivro();
                break;
            case 5:
                buscarLivrosPorAutor();
                break;
            case 6:
                cadastrarUsuario();
                break;
            case 7:
                consultarUsuario();
                break;
            case 8:
                usuarioServico.listarTodosUsuarios();
                break;
            case 9:
                removerUsuario();
                break;
            case 10:
                realizarEmprestimo();
                break;
            case 11:
                registrarDevolucao();
                break;
            case 12:
                emprestimoServico.listarLivrosEmprestados();
                break;
            case 13:
                emprestimoServico.listarLivrosDisponiveis();
                break;
            case 14:
                emprestimoServico.exibirHistoricoGeral();
                break;
            case 0:
                System.out.println("\nEncerrando sistema...");
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }

    private static void cadastrarLivro() {
        System.out.println("\n--- CADASTRO DE LIVRO ---");
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Ano: ");
        int ano = lerInteiro();
        System.out.print("Editora: ");
        String editora = scanner.nextLine();
        System.out.print("Quantidade de exemplares: ");
        int quantidade = lerInteiro();

        livroServico.cadastrarLivro(titulo, autor, isbn, ano, editora, quantidade);
    }

    private static void consultarLivro() {
        System.out.println("\n--- CONSULTAR LIVRO ---");
        System.out.print("ISBN do livro: ");
        String isbn = scanner.nextLine();
        Livro livro = livroServico.consultarLivroPorIsbn(isbn);
        if (livro != null) {
            System.out.println(livro);
        }
    }

    private static void removerLivro() {
        System.out.println("\n--- REMOVER LIVRO ---");
        System.out.print("ISBN do livro: ");
        String isbn = scanner.nextLine();
        livroServico.removerLivro(isbn);
    }

    private static void buscarLivrosPorAutor() {
        System.out.println("\n--- BUSCAR POR AUTOR ---");
        System.out.print("Nome do autor: ");
        String autor = scanner.nextLine();
        livroServico.buscarLivrosPorAutor(autor);
    }

    private static void cadastrarUsuario() {
        System.out.println("\n--- CADASTRO DE USUÁRIO ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("E-mail: ");
        String email = scanner.nextLine();

        usuarioServico.cadastrarUsuario(nome, cpf, email);
    }

    private static void consultarUsuario() {
        System.out.println("\n--- CONSULTAR USUÁRIO ---");
        System.out.print("CPF do usuário: ");
        String cpf = scanner.nextLine();
        Usuario usuario = usuarioServico.consultarUsuarioPorCpf(cpf);
        if (usuario != null) {
            System.out.println(usuario);
            System.out.println("Histórico de empréstimos: " + usuario.getHistoricoEmprestimos().size() + " empréstimo(s)");
        }
    }

    private static void removerUsuario() {
        System.out.println("\n--- REMOVER USUÁRIO ---");
        System.out.print("CPF do usuário: ");
        String cpf = scanner.nextLine();
        usuarioServico.removerUsuario(cpf);
    }

    private static void realizarEmprestimo() {
        System.out.println("\n--- REALIZAR EMPRÉSTIMO ---");
        System.out.print("ISBN do livro: ");
        String isbn = scanner.nextLine();
        System.out.print("CPF do usuário: ");
        String cpf = scanner.nextLine();

        emprestimoServico.realizarEmprestimo(isbn, cpf);
    }

    private static void registrarDevolucao() {
        System.out.println("\n--- REGISTRAR DEVOLUÇÃO ---");
        System.out.print("ISBN do livro: ");
        String isbn = scanner.nextLine();
        System.out.print("CPF do usuário: ");
        String cpf = scanner.nextLine();

        emprestimoServico.registrarDevolucao(isbn, cpf);
    }
}