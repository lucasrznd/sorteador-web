package com.lucasffrezende.sorteadorweb.controllers;

import com.lucasffrezende.sorteadorweb.models.*;
import com.lucasffrezende.sorteadorweb.services.BrindeService;
import com.lucasffrezende.sorteadorweb.services.ProgramaService;
import com.lucasffrezende.sorteadorweb.services.SorteioService;
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
import java.util.HashSet;
import java.util.List;

import static com.lucasffrezende.sorteadorweb.enums.MensagemEnum.*;

@Component
@ViewScoped
@Data
public class BuscaSorteioController implements Serializable {

    @Autowired
    private SorteioService sorteioService;

    private Sorteio sorteio;
    private List<Sorteio> sorteioList;

    @Autowired
    private ProgramaService programaService;
    private List<Programa> programaList;

    @Autowired
    private UsuarioService usuarioService;
    private List<Usuario> usuarioList;

    @Autowired
    private BrindeService brindeService;
    private List<Brinde> brindeList;

    @PostConstruct
    public void init() {
        sorteio = new Sorteio();
        sorteio.setPrograma(new Programa());
        sorteio.setBrinde(new Brinde());
        sorteio.setUsuario(new Usuario());
        sorteio.setOuvinteSet(new HashSet<>());
        sorteio.setResultado(new ResultadoSorteio());

        programaList = programaService.listar();
    }

    public void buscar() {
        sorteioList = sorteioService.buscaDinamica(sorteio);

        ListaUtil.verificaTamanhoLista(sorteioList);
    }

    public List<Usuario> buscarUsuario(String nome) {
        usuarioList = usuarioService.buscaPorNomeUsuario(nome);

        if (usuarioList == null) {
            Messages.addFlashGlobalWarn(MSG_NENHUM_REGISTRO.getMsg());
        }
        return usuarioList;
    }

    public List<Brinde> buscarBrinde(String descricao) {
        brindeList = brindeService.buscaPorDescricao(descricao);

        if (brindeList == null) {
            Messages.addFlashGlobalWarn(MSG_NENHUM_REGISTRO.getMsg());
        }
        return brindeList;
    }

    public void editar() {
        sorteioService.salvar(sorteio);

        GrowlView.showInfo("Sucesso", MSG_SALVO_SUCESSO.getMsg());
    }

    public void delete() {
        sorteioService.delete(sorteio);

        sorteioList.remove(sorteio);
        GrowlView.showWarn("Sucesso", MSG_EXCLUIDO_SUCESSO.getMsg());
    }

}
