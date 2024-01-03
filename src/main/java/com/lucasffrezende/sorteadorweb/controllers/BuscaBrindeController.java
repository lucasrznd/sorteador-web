package com.lucasffrezende.sorteadorweb.controllers;

import com.lucasffrezende.sorteadorweb.models.Brinde;
import com.lucasffrezende.sorteadorweb.models.EmpresaAssociada;
import com.lucasffrezende.sorteadorweb.models.TipoBrinde;
import com.lucasffrezende.sorteadorweb.services.BrindeService;
import com.lucasffrezende.sorteadorweb.services.EmpresaAssociadaService;
import com.lucasffrezende.sorteadorweb.services.TipoBrindeService;
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
public class BuscaBrindeController implements Serializable {

    @Autowired
    private BrindeService brindeService;

    private Brinde brinde;
    private List<Brinde> brindeList;

    @Autowired
    private TipoBrindeService tipoBrindeService;
    private List<TipoBrinde> tipoBrindeList;

    @Autowired
    private EmpresaAssociadaService empresaAssociadaService;
    private List<EmpresaAssociada> empresaAssociadaList;

    @PostConstruct
    public void init() {
        brinde = new Brinde();
        brinde.setTipoBrinde(new TipoBrinde());
        brinde.setEmpresaAssociada(new EmpresaAssociada());

        brindeList = new ArrayList<>();
        tipoBrindeList = tipoBrindeService.listar();
    }

    public void buscar() {
        brindeList = brindeService.buscaDinamica(brinde);

        ListaUtil.verificaTamanhoLista(brindeList);
    }

    public List<EmpresaAssociada> buscarEmpresaAssociada(String nome) {
        empresaAssociadaList = empresaAssociadaService.buscaPorNome(nome);

        return empresaAssociadaList;
    }

    public void editar() {
        brindeService.salvar(brinde);

        GrowlView.showInfo("Sucesso", MSG_SALVO_SUCESSO.getMsg());
    }

    public void delete() {
        brindeService.delete(brinde);

        brindeList.remove(brinde);
        GrowlView.showWarn("Sucesso", MSG_EXCLUIDO_SUCESSO.getMsg());
    }

}
