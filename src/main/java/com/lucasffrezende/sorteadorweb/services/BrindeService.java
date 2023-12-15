package com.lucasffrezende.sorteadorweb.services;

import com.lucasffrezende.sorteadorweb.models.Brinde;
import com.lucasffrezende.sorteadorweb.repositories.BrindeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BrindeService {

    @Autowired
    private BrindeRepository brindeRepository;

    public List<Brinde> buscaDinamica(Brinde brinde) {
        return brindeRepository.buscaDinamica(brinde);
    }

    public List<Brinde> buscaPorDescricao(String descricao) {
        return brindeRepository.buscaPorDescricao(descricao);
    }

    public Brinde buscaPorCodigo(Long codigo) {
        return brindeRepository.buscaPorCodigo(codigo);
    }

    public void salvar(Brinde brinde) {
        brindeRepository.salvar(brinde);
    }

    public void delete(Brinde brinde) {
        brindeRepository.delete(brinde);
    }

}
