package com.lucasffrezende.sorteadorweb.controllers;

import com.lucasffrezende.sorteadorweb.models.*;
import com.lucasffrezende.sorteadorweb.services.*;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.omnifaces.util.Messages;
import org.primefaces.model.DualListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.lucasffrezende.sorteadorweb.enums.MensagemEnum.MSG_NENHUM_REGISTRO;
import static com.lucasffrezende.sorteadorweb.enums.MensagemEnum.MSG_SALVO_SUCESSO;

@Component
@ViewScoped
@Data
public class NovoSorteioController implements Serializable {

    @Autowired
    private SorteioService sorteioService;

    @Autowired
    private ProgramaService programaService;
    private List<Programa> programaList;

    @Autowired
    private UsuarioService usuarioService;
    private List<Usuario> usuarioList;

    @Autowired
    private BrindeService brindeService;
    private List<Brinde> brindeList;

    @Autowired
    private OuvinteService ouvinteService;

    @Autowired
    private OuvinteSorteioService ouvinteSorteioService;

    private OuvinteSorteio ouvinteSorteio;
    private List<OuvinteSorteio> ouvinteSorteioList;

    private List<Ouvinte> ouvintesDisponiveis;
    private List<Ouvinte> ouvintesSelecionados;
    private DualListModel ouvintesModel;

    @PostConstruct
    public void init() {
        ouvinteSorteio = new OuvinteSorteio();
        ouvinteSorteio.setOuvinte(new Ouvinte());
        ouvinteSorteio.setSorteio(new Sorteio());

        programaList = programaService.listar();
        ouvintesDisponiveis = ouvinteService.listar();
        ouvintesSelecionados = new ArrayList<>();
        ouvintesModel = new DualListModel(ouvintesDisponiveis, ouvintesSelecionados);
    }

    public void salvar() {
        // Coloca os selecionados da DualListModel no Arraylist de selecionados
        ouvintesSelecionados = ouvintesModel.getTarget();
        OuvinteSorteio novoOuvinteSorteio = null;

        for (Ouvinte ouvinte : ouvintesSelecionados) {
            if (!ouvinteJaParticipaDoSorteio(ouvinte)) {
                novoOuvinteSorteio = new OuvinteSorteio();

                novoOuvinteSorteio.setSorteio(ouvinteSorteio.getSorteio());
                novoOuvinteSorteio.getSorteio().setDataHora(LocalDateTime.now());
                novoOuvinteSorteio.setOuvinte(ouvinte);

                ouvinteSorteioService.salvar(novoOuvinteSorteio);
            }
        }

        ouvinteSorteio = novoOuvinteSorteio;
        Messages.addGlobalInfo(MSG_SALVO_SUCESSO.getMsg());
    }

    private boolean ouvinteJaParticipaDoSorteio(Ouvinte ouvinte) {
        List<OuvinteSorteio> ouvinteSorteioList = ouvinteSorteioService.listar();

        for (OuvinteSorteio ouvinteSort : ouvinteSorteioList) {
            if (ouvinteSort.getOuvinte().getCodigo().equals(ouvinte.getCodigo()) &&
                    ouvinteSort.getSorteio().getCodigo().equals(ouvinteSorteio.getSorteio().getCodigo())) {
                return true;  // O ouvinte já participa do sorteio existente
            }
        }

        return false;  // O ouvinte ainda não participa do sorteio existente
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

}
