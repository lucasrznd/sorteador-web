package com.lucasffrezende.sorteadorweb.controllers;

import com.lucasffrezende.sorteadorweb.models.*;
import com.lucasffrezende.sorteadorweb.services.*;
import com.lucasffrezende.sorteadorweb.utils.GrowlView;
import com.lucasffrezende.sorteadorweb.utils.ListaUtil;
import jakarta.annotation.PostConstruct;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.omnifaces.util.Messages;
import org.primefaces.model.DualListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.*;

import static com.lucasffrezende.sorteadorweb.enums.MensagemEnum.*;

@Component
@ViewScoped
@Data
public class NovoSorteioController implements Serializable {

    @Autowired
    private SorteioService sorteioService;

    private Sorteio sorteio;
    private Sorteio sorteioSelecionado;
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

    @Autowired
    private OuvinteService ouvinteService;

    private List<Ouvinte> ouvintesDisponiveis;
    private List<Ouvinte> ouvintesSelecionados;
    private DualListModel ouvintesModel;

    @PostConstruct
    public void init() {
        sorteio = new Sorteio();
        sorteio.setPrograma(new Programa());
        sorteio.setBrinde(new Brinde());
        sorteio.setUsuario(new Usuario());
        sorteio.setOuvinteSet(new HashSet<>());
        sorteio.setResultado(new ResultadoSorteio());

        sorteioList = new ArrayList<>();
        programaList = programaService.listar();
        ouvintesDisponiveis = ouvinteService.listar();
        ouvintesSelecionados = new ArrayList<>();
        ouvintesModel = new DualListModel(ouvintesDisponiveis, ouvintesSelecionados);
    }

    public void salvar() {
        ouvintesSelecionados = ouvintesModel.getTarget();
        Set<Ouvinte> ouvinteSet = new HashSet<>(ouvintesSelecionados);

        sorteio.setOuvinteSet(ouvinteSet);
        sorteioService.salvar(sorteio);

        Messages.addGlobalInfo(MSG_SALVO_SUCESSO.getMsg());
    }

    public void buscar() {
        sorteioList = sorteioService.buscaDinamica(sorteio);

        ListaUtil.verificaTamanhoLista(sorteioList);
    }

    public void importar(Sorteio sorteioSelecionado) {
        this.sorteio = sorteioService.buscaPorCodigo(sorteioSelecionado.getCodigo());

        List<Object> participantes = new ArrayList<>();

        ouvintesModel.setTarget(Arrays.asList(sorteio.getOuvinteSet().toArray()));
        for (Object participante : ouvintesModel.getTarget()) {
            for (Object ouvinte : ouvintesModel.getSource()) {
                if (ouvinte.equals(participante)) {
                    participantes.add(ouvinte);
                }
            }
        }

        ouvintesModel.getSource().removeAll(participantes);
        GrowlView.showInfo("Sucesso", MSG_IMPORT_SUCESSO.getMsg());
    }

    public List<Usuario> buscarUsuario(String nome) {
        usuarioList = usuarioService.buscaPorNomeUsuario(nome);

        if (usuarioList == null) {
            GrowlView.showInfo("Falha", MSG_NENHUM_REGISTRO.getMsg());
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
