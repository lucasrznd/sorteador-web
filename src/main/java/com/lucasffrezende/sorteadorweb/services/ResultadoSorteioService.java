package com.lucasffrezende.sorteadorweb.services;

import com.lucasffrezende.sorteadorweb.models.ResultadoSorteio;
import com.lucasffrezende.sorteadorweb.models.Sorteio;
import com.lucasffrezende.sorteadorweb.repositories.ResultadoSorteioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResultadoSorteioService {

    @Autowired
    private ResultadoSorteioRepository resultadoSorteioRepository;

    public List<ResultadoSorteio> listar() {
        return resultadoSorteioRepository.listar();
    }

    public List<ResultadoSorteio> buscaDinamica(ResultadoSorteio resultadoSorteio) {
        return resultadoSorteioRepository.buscaDinamica(resultadoSorteio);
    }

    public ResultadoSorteio buscaPorSorteio(Sorteio sorteio) {
        return resultadoSorteioRepository.buscaPorSorteio(sorteio);
    }

    public List<ResultadoSorteio> ganhadoresDeHoje() {
        return resultadoSorteioRepository.ganhadoresDeHoje();
    }

    public void salvar(ResultadoSorteio resultadoSorteio) {
        resultadoSorteioRepository.salvar(resultadoSorteio);
    }

    public void delete(ResultadoSorteio resultadoSorteio) {
        resultadoSorteioRepository.delete(resultadoSorteio);
    }

}
