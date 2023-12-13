package com.lucasffrezende.sorteadorweb.repositories;

import com.lucasffrezende.sorteadorweb.models.Programa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ProgramaRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void salvar(Programa programa) {
        em.merge(programa);
    }

    @Transactional
    public void delete(Programa programa) {
        em.remove(em.contains(programa) ? programa : em.merge(programa));
    }

}
