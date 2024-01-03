package com.lucasffrezende.sorteadorweb.controllers;

import com.lucasffrezende.sorteadorweb.models.Pessoa;
import com.lucasffrezende.sorteadorweb.models.Programa;
import com.lucasffrezende.sorteadorweb.models.Usuario;
import com.lucasffrezende.sorteadorweb.models.UsuarioPrograma;
import com.lucasffrezende.sorteadorweb.services.UsuarioProgramaService;
import com.lucasffrezende.sorteadorweb.services.UsuarioService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.omnifaces.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.lucasffrezende.sorteadorweb.enums.MensagemEnum.MSG_NENHUM_REGISTRO;
import static com.lucasffrezende.sorteadorweb.enums.MensagemEnum.MSG_SALVO_SUCESSO;

@Component
@ViewScoped
@Data
public class NovoProgramaController implements Serializable {

    @Autowired
    private UsuarioService usuarioService;

    private List<Usuario> usuarioList;

    @Autowired
    private UsuarioProgramaService usuarioProgramaService;

    private UsuarioPrograma usuarioPrograma;

    @PostConstruct
    public void init() {
        usuarioPrograma = new UsuarioPrograma();
        usuarioPrograma.setPrograma(new Programa());
        usuarioPrograma.setUsuario(new Usuario());
        usuarioPrograma.getUsuario().setPessoa(new Pessoa());

        usuarioList = new ArrayList<>();
    }

    public void salvar() {
        usuarioProgramaService.salvar(usuarioPrograma);

        Messages.addGlobalInfo(MSG_SALVO_SUCESSO.getMsg());
    }

    public List<Usuario> buscarLocutor(String nome) {
        usuarioList = usuarioService.buscaPorNomeLocutor(nome);

        if (usuarioList == null) {
            Messages.addFlashGlobalWarn(MSG_NENHUM_REGISTRO.getMsg());
        }
        return usuarioList;
    }

}
