package com.lucasffrezende.sorteadorweb.controllers;

import com.lucasffrezende.sorteadorweb.models.*;
import com.lucasffrezende.sorteadorweb.services.BrindeService;
import com.lucasffrezende.sorteadorweb.services.ProgramaService;
import com.lucasffrezende.sorteadorweb.services.ResultadoSorteioService;
import com.lucasffrezende.sorteadorweb.services.UsuarioProgramaService;
import com.lucasffrezende.sorteadorweb.utils.GrowlView;
import com.lucasffrezende.sorteadorweb.utils.ListaUtil;
import com.lucasffrezende.sorteadorweb.utils.StringUtil;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.omnifaces.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.lucasffrezende.sorteadorweb.enums.MensagemEnum.MSG_EXCLUIDO_SUCESSO;
import static com.lucasffrezende.sorteadorweb.enums.MensagemEnum.MSG_NENHUM_REGISTRO;

@Component
@ViewScoped
@Data
public class BuscaResultadoSorteioController implements Serializable {

    @Autowired
    private ResultadoSorteioService resultadoSorteioService;

    private ResultadoSorteio resultadoSorteio;
    private List<ResultadoSorteio> resultadoSorteioList;

    @Autowired
    private ProgramaService programaService;

    private List<Programa> programaList;

    @Autowired
    private BrindeService brindeService;
    private List<Brinde> brindeList;

    @Autowired
    private UsuarioProgramaService usuarioProgramaService;

    private String mensagemGanhador;

    @PostConstruct
    public void init() {
        resultadoSorteio = new ResultadoSorteio();
        resultadoSorteio.setSorteio(new Sorteio());
        resultadoSorteio.getSorteio().setPrograma(new Programa());
        resultadoSorteio.setOuvinte(new Ouvinte());
        resultadoSorteio.getOuvinte().setPessoa(new Pessoa());

        programaList = programaService.listar();
        resultadoSorteioList = new ArrayList<>();
    }

    public void buscar() {
        resultadoSorteioList = resultadoSorteioService.buscaDinamica(resultadoSorteio);

        ListaUtil.verificaTamanhoLista(resultadoSorteioList);
    }

    public List<Brinde> buscarBrinde(String descricao) {
        brindeList = brindeService.buscaPorDescricao(descricao);

        if (brindeList == null) {
            Messages.addFlashGlobalWarn(MSG_NENHUM_REGISTRO.getMsg());
        }
        return brindeList;
    }

    public String gerarMensagemGanhador(ResultadoSorteio resultadoSorteio) {
        UsuarioPrograma usuarioPrograma = usuarioProgramaService.buscaPorPrograma(resultadoSorteio.getSorteio().getPrograma());
        this.mensagemGanhador = StringUtil.mensagemGanhador(resultadoSorteio.getSorteio(), usuarioPrograma);
        return mensagemGanhador;
    }

    public void delete() {
        resultadoSorteioService.delete(resultadoSorteio);

        resultadoSorteioList.remove(resultadoSorteio);
        GrowlView.showWarn("Sucesso", MSG_EXCLUIDO_SUCESSO.getMsg());
    }

}
