package com.lucasffrezende.sorteadorweb.services;

import com.lucasffrezende.sorteadorweb.models.Sorteio;
import com.lucasffrezende.sorteadorweb.repositories.SorteioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SorteioService {

    @Autowired
    private SorteioRepository sorteioRepository;

    public List<Sorteio> listar() {
        return sorteioRepository.listar();
    }

    public Sorteio obterUltimoSorteio() {
        return sorteioRepository.obterUltimoSorteio();
    }

    public void salvar(Sorteio sorteio) {
        sorteioRepository.salvar(sorteio);
    }

    public void delete(Sorteio sorteio) {
        sorteioRepository.delete(sorteio);
    }

}
