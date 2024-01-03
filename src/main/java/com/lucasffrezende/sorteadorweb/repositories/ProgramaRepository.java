package com.lucasffrezende.sorteadorweb.repositories;

import com.lucasffrezende.sorteadorweb.models.Programa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class ProgramaRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Programa> listar() {
        Query query = em.createQuery("SELECT p FROM Programa p ORDER BY p.nome");
        List<Programa> programaList = query.getResultList();
        return programaList;
    }

    @Transactional
    public void salvar(Programa programa) {
        em.merge(programa);
    }

    @Transactional
    public void delete(Programa programa) {
        em.remove(em.contains(programa) ? programa : em.merge(programa));
    }

}
