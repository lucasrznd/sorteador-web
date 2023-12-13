package com.lucasffrezende.sorteadorweb.controllers;

import com.lucasffrezende.sorteadorweb.models.Endereco;
import com.lucasffrezende.sorteadorweb.models.Ouvinte;
import com.lucasffrezende.sorteadorweb.models.Pessoa;
import com.lucasffrezende.sorteadorweb.services.OuvinteService;
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
public class NovoOuvinteController implements Serializable {

    @Autowired
    private OuvinteService ouvinteService;

    private Ouvinte ouvinte;

    @PostConstruct
    public void init() {
        ouvinte = new Ouvinte();
        ouvinte.setPessoa(new Pessoa());
        ouvinte.getPessoa().setEndereco(new Endereco());
    }

    public void salvar() {
        ouvinteService.salvar(ouvinte);

        Messages.addGlobalInfo(MSG_SALVO_SUCESSO.getMsg());
    }

}
