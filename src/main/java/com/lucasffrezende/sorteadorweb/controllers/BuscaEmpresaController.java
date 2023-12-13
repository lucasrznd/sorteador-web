package com.lucasffrezende.sorteadorweb.controllers;

import com.lucasffrezende.sorteadorweb.models.EmpresaAssociada;
import com.lucasffrezende.sorteadorweb.models.Endereco;
import com.lucasffrezende.sorteadorweb.services.EmpresaAssociadaService;
import com.lucasffrezende.sorteadorweb.utils.GrowlView;
import com.lucasffrezende.sorteadorweb.utils.ListaUtil;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.omnifaces.util.Faces;
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
public class BuscaEmpresaController implements Serializable {

    @Autowired
    private EmpresaAssociadaService empresaAssociadaService;

    private EmpresaAssociada empresaAssociada;
    private List<EmpresaAssociada> empresaAssociadaList;

    @PostConstruct
    public void init() {
        empresaAssociada = new EmpresaAssociada();
        empresaAssociada.setEndereco(new Endereco());

        empresaAssociadaList = new ArrayList<>();
    }

    public void buscar() {
        empresaAssociadaList = empresaAssociadaService.buscaDinamica(empresaAssociada);

        ListaUtil.verificaTamanhoLista(empresaAssociadaList);
    }

    public void editar() {
        empresaAssociadaService.salvar(empresaAssociada);

        GrowlView.showInfo("Sucesso", MSG_SALVO_SUCESSO.getMsg());
    }

    public void delete() {
        empresaAssociadaService.delete(empresaAssociada);

        empresaAssociadaList.remove(empresaAssociada);
        GrowlView.showWarn("Sucesso", MSG_EXCLUIDO_SUCESSO.getMsg());
    }

}
