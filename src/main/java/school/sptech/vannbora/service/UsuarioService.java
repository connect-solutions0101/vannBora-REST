package school.sptech.vannbora.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import school.sptech.vannbora.entidade.Usuario;
import school.sptech.vannbora.repository.UsuarioRepository;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository repository;

    public List<Usuario> listar() {
        return repository.findAll();
    }

    public Usuario buscarPorEmailESenha(String email, String senha) {
        return repository.findByEmailAndSenha(email, senha);
    }

    public Usuario cadastrar(Usuario usuario) {
        return repository.save(usuario);
    }

    public Usuario atualizar(int id, Usuario usuario) {
        usuario.setId(id);
        return repository.save(usuario);
    }

    public void deletar(int id) {
        repository.deleteById(id);
    }
}
