package com.lucasffrezende.sorteadorweb.services;

import com.lucasffrezende.sorteadorweb.models.Programa;
import com.lucasffrezende.sorteadorweb.repositories.ProgramaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProgramaService {

    @Autowired
    private ProgramaRepository programaRepository;

    public void salvar(Programa programa) {
        programaRepository.salvar(programa);
    }

    public void delete(Programa programa) {
        programaRepository.salvar(programa);
    }

}
