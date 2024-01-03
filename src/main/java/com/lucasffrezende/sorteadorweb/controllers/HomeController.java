package com.lucasffrezende.sorteadorweb.controllers;

import com.lucasffrezende.sorteadorweb.models.ResultadoSorteio;
import com.lucasffrezende.sorteadorweb.services.ResultadoSorteioService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
@Data
public class HomeController implements Serializable {

    @Autowired
    private ResultadoSorteioService resultadoSorteioService;
    private List<ResultadoSorteio> resultadoSorteioList;

    @PostConstruct
    public void init() {
        resultadoSorteioList = resultadoSorteioService.listar();
    }

}
