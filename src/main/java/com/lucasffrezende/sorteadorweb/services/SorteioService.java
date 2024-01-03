package com.lucasffrezende.sorteadorweb.services;

import com.lucasffrezende.sorteadorweb.models.Ouvinte;
import com.lucasffrezende.sorteadorweb.models.Sorteio;
import com.lucasffrezende.sorteadorweb.repositories.SorteioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class SorteioService {

    @Autowired
    private SorteioRepository sorteioRepository;

    public List<Sorteio> listar() {
        return sorteioRepository.listar();
    }

    public List<Sorteio> buscaDinamica(Sorteio sorteio) {
        return sorteioRepository.buscaDinamica(sorteio);
    }

    public List<Sorteio> buscaDinamicaSorteioAtivo(Sorteio sorteio) {
        return sorteioRepository.buscaDinamicaSorteioAtivo(sorteio);
    }

    public List<Sorteio> buscaDinamicaSorteioParticipantes(Sorteio sorteio) {
        return sorteioRepository.buscaDinamicaSorteioParticipantes(sorteio);
    }

    public Sorteio buscaPorCodigo(Long codigo) {
        return sorteioRepository.buscaPorCodigo(codigo);
    }

    public void salvar(Sorteio sorteio) {
        sorteio.setAtivo(true);

        sorteioRepository.salvar(sorteio);
    }

    public void delete(Sorteio sorteio) {
        sorteioRepository.delete(sorteio);
    }

    public Ouvinte sortear(List<Ouvinte> ouvinteList) {
        Random random = new Random();

        Integer qtdParticipantes = ouvinteList.size();
        Integer indiceSorteador = random.nextInt(qtdParticipantes);

        Ouvinte ouvinte = ouvinteList.get(indiceSorteador);
        ouvinteList.remove(ouvinte);

        return ouvinte;
    }

}
