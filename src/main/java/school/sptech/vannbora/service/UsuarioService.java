package school.sptech.vannbora.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import school.sptech.vannbora.entidade.Usuario;
import school.sptech.vannbora.interfaces.ISorter;
import school.sptech.vannbora.repository.UsuarioRepository;

@Service
public class UsuarioService implements ISorter<Usuario> {

    @Autowired
    private UsuarioRepository repository;

    public ResponseEntity<List<Usuario>> listar() {
        List<Usuario> usuarios = repository.findAll();
        return ResponseEntity.status(200).body(usuarios);
    }

    public ResponseEntity<Usuario> buscarPorEmailESenha(String email, String senha) {
        Usuario usuario = repository.findByEmailAndSenha(email, senha);
        if (usuario != null) {
            return ResponseEntity.status(200).body(usuario);
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    public ResponseEntity<Usuario> cadastrar(Usuario usuario) {
        Usuario novoUsuario = repository.save(usuario);
        return ResponseEntity.status(201).body(novoUsuario);
    }

    public ResponseEntity<Usuario> atualizar(int id, Usuario usuario) {
        usuario.setId(id);
        Usuario usuarioAtualizado = repository.save(usuario);
        return ResponseEntity.status(200).body(usuarioAtualizado);
    }

    public ResponseEntity<Void> deletar(int id) {
        repository.deleteById(id);
        return ResponseEntity.status(204).build();
    }

    @Override
    public void sort(Usuario[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int idxMaior = i;
            for (int j = i+1; j < array.length; j++) {
              if (array[j].getDataNascimento().isBefore(array[idxMaior].getDataNascimento())) {
                idxMaior = j;
              }
            }
            var aux = array[i];
            array[i] = array[idxMaior];
            array[idxMaior] = aux;
          }
    }
}
