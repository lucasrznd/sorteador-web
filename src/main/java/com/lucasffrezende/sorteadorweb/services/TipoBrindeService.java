package com.lucasffrezende.sorteadorweb.services;

import com.lucasffrezende.sorteadorweb.models.TipoBrinde;
import com.lucasffrezende.sorteadorweb.repositories.TipoBrindeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TipoBrindeService {

    @Autowired
    private TipoBrindeRepository tipoBrindeRepository;

    public List<TipoBrinde> listar() {
        return tipoBrindeRepository.listar();
    }

    public List<TipoBrinde> buscar(String tipo) {
        return tipoBrindeRepository.buscar(tipo);
    }

    public void salvar(TipoBrinde tipoBrinde) {
        tipoBrindeRepository.salvar(tipoBrinde);
    }

    public void delete(TipoBrinde tipoBrinde) {
        tipoBrindeRepository.delete(tipoBrinde);
    }

}
