package com.lucasffrezende.sorteadorweb.controllers;

import com.lucasffrezende.sorteadorweb.models.Endereco;
import com.lucasffrezende.sorteadorweb.models.Ouvinte;
import com.lucasffrezende.sorteadorweb.models.Pessoa;
import com.lucasffrezende.sorteadorweb.services.OuvinteService;
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
public class BuscaOuvinteController implements Serializable {

    @Autowired
    private OuvinteService ouvinteService;

    private Ouvinte ouvinte;
    private List<Ouvinte> ouvinteList;

    @PostConstruct
    public void init() {
        ouvinte = new Ouvinte();
        ouvinte.setPessoa(new Pessoa());
        ouvinte.getPessoa().setEndereco(new Endereco());

        ouvinteList = new ArrayList<>();
    }

    public void buscar() {
        ouvinteList = ouvinteService.buscaDinamica(ouvinte);

        ListaUtil.verificaTamanhoLista(ouvinteList);
    }

    public void editar() {
        ouvinteService.editar(ouvinte);

        GrowlView.showInfo("Sucesso", MSG_SALVO_SUCESSO.getMsg());
    }

    public void delete() {
        ouvinteService.delete(ouvinte);

        ouvinteList.remove(ouvinte);
        GrowlView.showWarn("Sucesso", MSG_EXCLUIDO_SUCESSO.getMsg());
    }

}
