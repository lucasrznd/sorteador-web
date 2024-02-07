package com.lucasffrezende.sorteadorweb.controllers;

import com.lucasffrezende.sorteadorweb.models.*;
import com.lucasffrezende.sorteadorweb.services.*;
import com.lucasffrezende.sorteadorweb.utils.GrowlView;
import com.lucasffrezende.sorteadorweb.utils.StringUtil;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.omnifaces.util.Messages;
import org.primefaces.model.DualListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

import static com.lucasffrezende.sorteadorweb.enums.MensagemEnum.*;

@Component
@ViewScoped
@Data
public class NovoSorteioController implements Serializable {

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

    @Autowired
    private OuvinteService ouvinteService;

    private List<Ouvinte> ouvintesDisponiveis;
    private List<Ouvinte> ouvintesSelecionados;
    private DualListModel ouvintesModel;

    @Autowired
    private ResultadoSorteioService resultadoSorteioService;
    private ResultadoSorteio resultadoSorteio;
    private ResultadoSorteio resultadoSorteioTemporario;

    @Autowired
    private UsuarioProgramaService usuarioProgramaService;

    @Autowired
    private LoginService loginService;

    private String tipoImportacao;
    private String mensagemGanhador;

    @PostConstruct
    public void init() {
        sorteio = new Sorteio();
        sorteio.setPrograma(new Programa());
        sorteio.setBrinde(new Brinde());
        sorteio.setUsuario(new Usuario());
        sorteio.setOuvinteSet(new HashSet<>());
        sorteio.getBrinde().setTipoBrinde(new TipoBrinde());

        resultadoSorteio = new ResultadoSorteio();
        resultadoSorteio.setSorteio(new Sorteio());
        resultadoSorteio.setOuvinte(new Ouvinte());

        sorteioList = new ArrayList<>();
        programaList = programaService.listar();
        ouvintesDisponiveis = ouvinteService.listar();
        ouvintesSelecionados = new ArrayList<>();
        ouvintesModel = new DualListModel(ouvintesDisponiveis, ouvintesSelecionados);
        tipoImportacao = "Sorteio";
    }

    public void salvar() {
        // Obter usuario logado
        LoginController loginController = LoginController.getInstance();
        Usuario usuarioLogado = loginService.obterUsuario(loginController.getUsuarioLogado());

        ouvintesSelecionados = ouvintesModel.getTarget();
        Set<Ouvinte> ouvinteSet = new HashSet<>(ouvintesSelecionados);

        sorteio.setDataHora(LocalDateTime.now());
        sorteio.setOuvinteSet(ouvinteSet);
        sorteio.setUsuario(usuarioLogado);
        sorteioService.salvar(sorteio);

        // Salva a associacao na tabela resultado sorteio
        resultadoSorteio.setSorteio(sorteio);
        resultadoSorteio.setOuvinte(null);

        // Verifica se o resultadoSorteio para o sorteio informado ja existe
        ResultadoSorteio resultadoSorteioSalvo = resultadoSorteioService.buscaPorSorteio(sorteio);
        if (resultadoSorteioSalvo != null) {
            resultadoSorteioSalvo.setSorteio(sorteio);
            resultadoSorteioSalvo.setOuvinte(null);
            resultadoSorteioService.salvar(resultadoSorteioSalvo);
        } else {
            resultadoSorteioService.salvar(resultadoSorteio);
        }

        Messages.addGlobalInfo(MSG_SALVO_SUCESSO.getMsg());
    }

    public void buscar() {
        if (tipoImportacao.equals("Participantes")) {
            sorteioList = sorteioService.buscaDinamicaSorteioParticipantes(sorteio);
        } else {
            sorteioList = sorteioService.buscaDinamicaSorteioAtivo(sorteio);
        }
        if (sorteioList.isEmpty()) {
            GrowlView.showWarn("Falha", MSG_NENHUM_REGISTRO.getMsg());
        }
    }

    public void importar(Sorteio sorteioSelecionado) {
        this.sorteio = sorteioService.buscaPorCodigo(sorteioSelecionado.getCodigo());

        List<Object> participantes = new ArrayList<>();

        // Se o ouvinte já estava participando, remove o nome dele na lista de disponiveis
        ouvintesModel.setTarget(Arrays.asList(sorteio.getOuvinteSet().toArray()));
        for (Object participante : ouvintesModel.getTarget()) {
            for (Object ouvinte : ouvintesModel.getSource()) {
                if (ouvinte.equals(participante)) {
                    participantes.add(ouvinte);
                }
            }
        }

        ouvintesModel.getSource().removeAll(participantes);

        if (tipoImportacao.equals("Participantes")) {
            sorteio = new Sorteio();
            sorteio.setPrograma(new Programa());
            sorteio.setBrinde(new Brinde());
            sorteio.setUsuario(new Usuario());
            sorteio.setOuvinteSet(new HashSet<>());
            sorteio.getBrinde().setTipoBrinde(new TipoBrinde());
        }
        GrowlView.showInfo("Sucesso", MSG_IMPORT_SUCESSO.getMsg());
    }

    public void sortear() {
        resultadoSorteioTemporario = new ResultadoSorteio();

        // Se um ouvinte ja ganhou um sorteio no determinado dia, sera removido da lista
        List<ResultadoSorteio> ganhadoresDeHoje = resultadoSorteioService.ganhadoresDeHoje();
        List<Ouvinte> ganhadoresList = new ArrayList<>();
        for (ResultadoSorteio ganhador : ganhadoresDeHoje) {
            ganhadoresList.add(ganhador.getOuvinte());
        }

        ouvintesModel.getTarget().removeAll(ganhadoresList);

        // Realiza o sorteio
        Ouvinte ouvinte = sorteioService.sortear(ouvintesModel.getTarget());

        resultadoSorteio = resultadoSorteioService.buscaPorSorteio(this.sorteio);
        resultadoSorteio.setDataHora(LocalDateTime.now());
        resultadoSorteio.setOuvinte(ouvinte);
        resultadoSorteio.getSorteio().setAtivo(false);

        resultadoSorteioService.salvar(resultadoSorteio);
        resultadoSorteioTemporario = resultadoSorteio;

        UsuarioPrograma usuarioPrograma = usuarioProgramaService.buscaPorPrograma(sorteio.getPrograma());
        this.mensagemGanhador = StringUtil.mensagemGanhador(resultadoSorteio, usuarioPrograma);

        init();
    }

    public List<Brinde> buscarBrinde(String descricao) {
        brindeList = brindeService.buscaPorDescricao(descricao);

        if (brindeList == null) {
            Messages.addFlashGlobalWarn(MSG_NENHUM_REGISTRO.getMsg());
        }
        return brindeList;
    }

    public String brindeDoGanhador() {
        return resultadoSorteioTemporario.getSorteio().getBrinde().getDescricao() + " | " + resultadoSorteioTemporario.getSorteio().getBrinde().getEmpresaAssociada().getNome();
    }

}
