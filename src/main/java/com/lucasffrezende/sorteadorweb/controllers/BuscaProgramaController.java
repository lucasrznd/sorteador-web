package com.lucasffrezende.sorteadorweb.controllers;

import com.lucasffrezende.sorteadorweb.models.Pessoa;
import com.lucasffrezende.sorteadorweb.models.Programa;
import com.lucasffrezende.sorteadorweb.models.Usuario;
import com.lucasffrezende.sorteadorweb.models.UsuarioPrograma;
import com.lucasffrezende.sorteadorweb.services.UsuarioProgramaService;
import com.lucasffrezende.sorteadorweb.services.UsuarioService;
import com.lucasffrezende.sorteadorweb.utils.GrowlView;
import com.lucasffrezende.sorteadorweb.utils.ListaUtil;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.omnifaces.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.lucasffrezende.sorteadorweb.enums.MensagemEnum.*;

@Component
@ViewScoped
@Data
public class BuscaProgramaController implements Serializable {

    @Autowired
    private UsuarioService usuarioService;

    private List<Usuario> usuarioList;

    @Autowired
    private UsuarioProgramaService usuarioProgramaService;

    private UsuarioPrograma usuarioPrograma;
    private List<UsuarioPrograma> usuarioProgramaList;

    @PostConstruct
    public void init() {
        usuarioPrograma = new UsuarioPrograma();
        usuarioPrograma.setPrograma(new Programa());
        usuarioPrograma.setUsuario(new Usuario());
        usuarioPrograma.getUsuario().setPessoa(new Pessoa());

        usuarioList = new ArrayList<>();
        usuarioProgramaList = new ArrayList<>();
    }

    public void buscar() {
        usuarioProgramaList = usuarioProgramaService.buscaDinamica(usuarioPrograma);

        ListaUtil.verificaTamanhoLista(usuarioProgramaList);
    }

    public List<Usuario> buscarLocutor(String nome) {
        usuarioList = usuarioService.buscaPorNomeLocutor(nome);

        if (usuarioList == null) {
            Messages.addFlashGlobalWarn(MSG_NENHUM_REGISTRO.getMsg());
        }
        return usuarioList;
    }

    public void editar() {
        usuarioProgramaService.editar(usuarioPrograma);

        GrowlView.showInfo("Sucesso", MSG_SALVO_SUCESSO.getMsg());
    }

    public void delete() {
        usuarioProgramaService.delete(usuarioPrograma);

        usuarioProgramaList.remove(usuarioPrograma.getPrograma());
        GrowlView.showWarn("Sucesso", MSG_EXCLUIDO_SUCESSO.getMsg());
    }

}
