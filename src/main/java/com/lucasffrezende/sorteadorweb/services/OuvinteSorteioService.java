package com.lucasffrezende.sorteadorweb.services;

import com.lucasffrezende.sorteadorweb.models.OuvinteSorteio;
import com.lucasffrezende.sorteadorweb.models.Sorteio;
import com.lucasffrezende.sorteadorweb.repositories.OuvinteSorteioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OuvinteSorteioService {

    @Autowired
    private OuvinteSorteioRepository ouvinteSorteioRepository;

    public List<OuvinteSorteio> listar() {
        return ouvinteSorteioRepository.listar();
    }

    public List<OuvinteSorteio> buscaDinamica() {
        return ouvinteSorteioRepository.buscaDinamica();
    }

    public List<OuvinteSorteio> buscaPorSorteio(Sorteio sorteio) {
        return ouvinteSorteioRepository.buscaPorSorteio(sorteio);
    }

    public OuvinteSorteio buscaPorCodigo(Long codigo) {
        return ouvinteSorteioRepository.buscaPorCodigo(codigo);
    }

    public void salvar(OuvinteSorteio ouvinteSorteio) {
        ouvinteSorteioRepository.salvar(ouvinteSorteio);
    }

    public void delete(OuvinteSorteio ouvinteSorteio) {
        ouvinteSorteioRepository.delete(ouvinteSorteio);
    }

}
