package com.lucasffrezende.sorteadorweb.services;

import com.lucasffrezende.sorteadorweb.models.Usuario;
import com.lucasffrezende.sorteadorweb.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> buscaPorNomeUsuario(String nome) {
        return usuarioRepository.buscaPorNomeUsuario(nome);
    }

    public List<Usuario> buscaPorNomeLocutor(String nome) {
        return usuarioRepository.buscaPorNomeLocutor(nome);
    }

    public Usuario buscaPorCodigo(Long codigo) {
        return usuarioRepository.buscaPorCodigo(codigo);
    }

    public void salvar(Usuario usuario) {
        usuarioRepository.salvar(usuario);
    }

    public void editar(Usuario usuario) {
        usuarioRepository.editar(usuario);
    }

    public void delete(Usuario usuario) {
        usuarioRepository.delete(usuario);
    }

}
