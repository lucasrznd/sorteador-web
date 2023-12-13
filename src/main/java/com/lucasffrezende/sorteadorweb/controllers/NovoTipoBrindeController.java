package com.lucasffrezende.sorteadorweb.controllers;

import com.lucasffrezende.sorteadorweb.models.TipoBrinde;
import com.lucasffrezende.sorteadorweb.services.TipoBrindeService;
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
public class NovoTipoBrindeController implements Serializable {

    @Autowired
    private TipoBrindeService tipoBrindeService;

    private TipoBrinde tipoBrinde;

    @PostConstruct
    public void init() {
        tipoBrinde = new TipoBrinde();
    }

    public void salvar() {
        tipoBrindeService.salvar(tipoBrinde);

        Messages.addGlobalInfo(MSG_SALVO_SUCESSO.getMsg());
    }

}
