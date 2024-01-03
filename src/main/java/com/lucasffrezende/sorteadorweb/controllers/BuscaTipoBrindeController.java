package com.lucasffrezende.sorteadorweb.controllers;

import com.lucasffrezende.sorteadorweb.models.TipoBrinde;
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
public class BuscaTipoBrindeController implements Serializable {

    @Autowired
    private TipoBrindeService tipoBrindeService;

    private TipoBrinde tipoBrinde;
    private List<TipoBrinde> tipoBrindeList;

    @PostConstruct
    public void init() {
        tipoBrinde = new TipoBrinde();

        tipoBrindeList = new ArrayList<>();
    }

    public void buscar() {
        tipoBrindeList = tipoBrindeService.buscar(tipoBrinde.getTipo());

        ListaUtil.verificaTamanhoLista(tipoBrindeList);
    }

    public void editar() {
        tipoBrindeService.salvar(tipoBrinde);

        GrowlView.showInfo("Sucesso", MSG_SALVO_SUCESSO.getMsg());
    }

    public void delete() {
        tipoBrindeService.delete(tipoBrinde);

        tipoBrindeList.remove(tipoBrinde);
        GrowlView.showWarn("Sucesso", MSG_EXCLUIDO_SUCESSO.getMsg());
    }

}
