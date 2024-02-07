package com.lucasffrezende.sorteadorweb.controllers;

import com.lucasffrezende.sorteadorweb.models.Endereco;
import com.lucasffrezende.sorteadorweb.models.Pessoa;
import com.lucasffrezende.sorteadorweb.models.Usuario;
import com.lucasffrezende.sorteadorweb.services.UsuarioService;
import com.lucasffrezende.sorteadorweb.utils.GrowlView;
import com.lucasffrezende.sorteadorweb.utils.ListaUtil;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.lucasffrezende.sorteadorweb.enums.MensagemEnum.MSG_EXCLUIDO_SUCESSO;
import static com.lucasffrezende.sorteadorweb.enums.MensagemEnum.MSG_SALVO_SUCESSO;

@Component
@ViewScoped
@Data
public class BuscaUsuarioController implements Serializable {

    @Autowired
    private UsuarioService usuarioService;

    private Usuario usuario;
    private List<Usuario> usuarioList;

    @PostConstruct
    public void init() {
        usuario = new Usuario();
        usuario.setPessoa(new Pessoa());
        usuario.getPessoa().setEndereco(new Endereco());

        usuarioList = new ArrayList<>();
    }

    public void buscar() {
        usuarioList = usuarioService.buscaPorNomeUsuario(usuario.getPessoa().getNome());

        ListaUtil.verificaTamanhoLista(usuarioList);
    }

    public void editar() {
        usuarioService.editar(usuario);

        GrowlView.showInfo("Sucesso", MSG_SALVO_SUCESSO.getMsg());
    }

    public void delete() {
        usuarioService.delete(usuario);

        usuarioList.remove(usuario);
        GrowlView.showWarn("Sucesso", MSG_EXCLUIDO_SUCESSO.getMsg());
    }

}
