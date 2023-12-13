package com.lucasffrezende.sorteadorweb.repositories;

import com.lucasffrezende.sorteadorweb.models.Ouvinte;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class OuvinteRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Ouvinte> listar() {
        Query query = em.createQuery("SELECT o FROM Ouvinte o ORDER BY o.pessoa.nome");
        List<Ouvinte> ouvinteList = query.getResultList();
        return ouvinteList;
    }

    @Transactional
    public void salvar(Ouvinte ouvinte) {
        em.merge(ouvinte);
    }

    @Transactional
    public void delete(Ouvinte ouvinte) {
        em.remove(em.contains(ouvinte) ? ouvinte : em.merge(ouvinte));
    }

}
