package biblioteca.repositorio;

import biblioteca.modelo.Usuario;
import java.util.*;

public class UsuarioRepositorio {
    private Map<String, Usuario> usuarios;

    public UsuarioRepositorio() {
        this.usuarios = new HashMap<>();
    }

    public void adicionar(Usuario usuario) {
        usuarios.put(usuario.getCpf(), usuario);
    }

    public Usuario buscarPorCpf(String cpf) {
        return usuarios.get(cpf);
    }

    public List<Usuario> listarTodos() {
        return new ArrayList<>(usuarios.values());
    }

    public boolean remover(String cpf) {
        return usuarios.remove(cpf) != null;
    }

    public boolean existeCpf(String cpf) {
        return usuarios.containsKey(cpf);
    }
}