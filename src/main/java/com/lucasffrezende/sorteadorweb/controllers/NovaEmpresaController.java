package com.lucasffrezende.sorteadorweb.controllers;

import com.lucasffrezende.sorteadorweb.models.EmpresaAssociada;
import com.lucasffrezende.sorteadorweb.models.Endereco;
import com.lucasffrezende.sorteadorweb.services.EmpresaAssociadaService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.omnifaces.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;

import static com.lucasffrezende.sorteadorweb.enums.MensagemEnum.MSG_SALVO_SUCESSO;

@Component
@ViewScoped
@Data
public class NovaEmpresaController implements Serializable {

    @Autowired
    private EmpresaAssociadaService empresaAssociadaService;

    private EmpresaAssociada empresaAssociada;

    @PostConstruct
    public void init() {
        empresaAssociada = new EmpresaAssociada();
        empresaAssociada.setEndereco(new Endereco());
    }

    public void salvar() {
        empresaAssociadaService.salvar(empresaAssociada);

        Messages.addGlobalInfo(MSG_SALVO_SUCESSO.getMsg());
    }
}
