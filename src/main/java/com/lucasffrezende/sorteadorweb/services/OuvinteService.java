package com.lucasffrezende.sorteadorweb.services;

import com.lucasffrezende.sorteadorweb.models.Ouvinte;
import com.lucasffrezende.sorteadorweb.repositories.OuvinteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OuvinteService {

    @Autowired
    private OuvinteRepository ouvinteRepository;

    public List<Ouvinte> listar() {
        return ouvinteRepository.listar();
    }

    public List<Ouvinte> buscaDinamica(Ouvinte ouvinte) {
        return ouvinteRepository.buscaDinamica(ouvinte);
    }

    public Ouvinte buscaPorCodigo(Long codigo) {
        return ouvinteRepository.buscaPorCodigo(codigo);
    }

    public void salvar(Ouvinte ouvinte) {
        ouvinteRepository.salvar(ouvinte);
    }

    public void delete(Ouvinte ouvinte) {
        ouvinteRepository.delete(ouvinte);
    }

}
