package com.lucasffrezende.sorteadorweb.controllers;

import com.lucasffrezende.sorteadorweb.models.*;
import com.lucasffrezende.sorteadorweb.services.*;
import com.lucasffrezende.sorteadorweb.utils.ListaUtil;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
@Data
public class BuscaSorteioController implements Serializable {

    @Autowired
    private SorteioService sorteioService;
    private List<Sorteio> sorteioList;

    @Autowired
    private ProgramaService programaService;
    private List<Programa> programaList;

    @Autowired
    private UsuarioService usuarioService;
    private List<Usuario> usuarioList;

    @Autowired
    private BrindeService brindeService;
    private List<Brinde> brindeList;

    @Autowired
    private OuvinteService ouvinteService;

    @Autowired
    private OuvinteSorteioService ouvinteSorteioService;

    private OuvinteSorteio ouvinteSorteio;
    private List<OuvinteSorteio> ouvinteSorteioList;

    @PostConstruct
    public void init() {
        ouvinteSorteio = new OuvinteSorteio();
        ouvinteSorteio.setOuvinte(new Ouvinte());
        ouvinteSorteio.setSorteio(new Sorteio());

        programaList = programaService.listar();
        sorteioList = new ArrayList<>();
    }

    public void buscar() {
        sorteioList = sorteioService.listar();

        ListaUtil.verificaTamanhoLista(sorteioList);
    }

}
