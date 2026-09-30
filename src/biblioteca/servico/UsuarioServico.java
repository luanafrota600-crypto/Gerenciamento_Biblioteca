package biblioteca.servico;

import biblioteca.modelo.Usuario;
import biblioteca.repositorio.UsuarioRepositorio;
import java.util.List;

public class UsuarioServico {
    private UsuarioRepositorio usuarioRepositorio;

    public UsuarioServico() {
        this.usuarioRepositorio = new UsuarioRepositorio();
    }

    public boolean cadastrarUsuario(String nome, String cpf, String email) {
        if (usuarioRepositorio.existeCpf(cpf)) {
            System.out.println("Erro: Já existe um usuário com este CPF!");
            return false;
        }

        Usuario usuario = new Usuario(nome, cpf, email);
        usuarioRepositorio.adicionar(usuario);
        System.out.println("Usuário cadastrado com sucesso!");
        return true;
    }

    public Usuario consultarUsuarioPorCpf(String cpf) {
        Usuario usuario = usuarioRepositorio.buscarPorCpf(cpf);
        if (usuario == null) {
            System.out.println("Usuário não encontrado!");
        }
        return usuario;
    }

    public void listarTodosUsuarios() {
        List<Usuario> usuarios = usuarioRepositorio.listarTodos();
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
        } else {
            System.out.println("\n=== LISTA DE USUÁRIOS ===");
            for (Usuario usuario : usuarios) {
                System.out.println(usuario);
            }
        }
    }

    public boolean removerUsuario(String cpf) {
        if (usuarioRepositorio.remover(cpf)) {
            System.out.println("Usuário removido com sucesso!");
            return true;
        }
        System.out.println("Usuário não encontrado!");
        return false;
    }

    public UsuarioRepositorio getUsuarioRepositorio() {
        return usuarioRepositorio;
    }
}