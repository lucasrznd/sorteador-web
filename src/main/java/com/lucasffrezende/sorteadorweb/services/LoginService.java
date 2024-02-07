package com.lucasffrezende.sorteadorweb.services;

import com.lucasffrezende.sorteadorweb.models.Usuario;
import com.lucasffrezende.sorteadorweb.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario autenticar(String login, String senha) {
        return usuarioRepository.autenticar(login, senha);
    }

    public Usuario obterUsuario(Usuario usuario) {
        Usuario usuarioLogado = usuarioRepository.buscaPorCodigo(usuario.getCodigo());
        return usuarioLogado;
    }

}
