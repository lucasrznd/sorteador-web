package com.lucasffrezende.sorteadorweb.controllers;

import com.lucasffrezende.sorteadorweb.models.Brinde;
import com.lucasffrezende.sorteadorweb.models.EmpresaAssociada;
import com.lucasffrezende.sorteadorweb.models.TipoBrinde;
import com.lucasffrezende.sorteadorweb.services.BrindeService;
import com.lucasffrezende.sorteadorweb.services.EmpresaAssociadaService;
import com.lucasffrezende.sorteadorweb.services.TipoBrindeService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.omnifaces.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.lucasffrezende.sorteadorweb.enums.MensagemEnum.MSG_SALVO_SUCESSO;

@Component
@ViewScoped
@Data
public class NovoBrindeController implements Serializable {

    @Autowired
    private BrindeService brindeService;

    private Brinde brinde;

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

        tipoBrindeList = tipoBrindeService.listar();
        empresaAssociadaList = new ArrayList<>();
    }

    public void salvar() {
        brindeService.salvar(brinde);

        Messages.addGlobalInfo(MSG_SALVO_SUCESSO.getMsg());
    }

    public List<EmpresaAssociada> buscarEmpresaAssociada(String nome) {
        empresaAssociadaList = empresaAssociadaService.buscaPorNome(nome);

        return empresaAssociadaList;
    }

}
