package com.lucasffrezende.sorteadorweb.controllers;

import com.lucasffrezende.sorteadorweb.models.Endereco;
import com.lucasffrezende.sorteadorweb.models.Pessoa;
import com.lucasffrezende.sorteadorweb.models.Usuario;
import com.lucasffrezende.sorteadorweb.services.UsuarioService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.omnifaces.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;

import static com.lucasffrezende.sorteadorweb.enums.MensagemEnum.MSG_SALVO_SUCESSO;

@Component
@ViewScoped
@Data
public class NovoUsuarioController implements Serializable {

    @Autowired
    private UsuarioService usuarioService;

    private Usuario usuario;

    @PostConstruct
    public void init() {
        usuario = new Usuario();
        usuario.setPessoa(new Pessoa());
        usuario.getPessoa().setEndereco(new Endereco());
    }

    public void salvar() {
        usuarioService.salvar(usuario);

        init();
        Messages.addGlobalInfo(MSG_SALVO_SUCESSO.getMsg());
    }

}
