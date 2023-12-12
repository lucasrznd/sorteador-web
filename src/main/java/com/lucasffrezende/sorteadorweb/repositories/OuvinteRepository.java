package com.lucasffrezende.sorteadorweb.repositories;

import com.lucasffrezende.sorteadorweb.models.Ouvinte;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Component;

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

    public void salvar(Ouvinte ouvinte) {
        em.merge(ouvinte);
    }

    public void delete(Ouvinte ouvinte) {
        em.remove(ouvinte);
    }

}
