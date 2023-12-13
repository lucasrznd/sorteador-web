package com.lucasffrezende.sorteadorweb.repositories;

import com.lucasffrezende.sorteadorweb.models.Brinde;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class BrindeRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Brinde> listar() {
        Query query = em.createQuery("SELECT b FROM Brinde b ORDER BY b.descricao");
        List<Brinde> brindeList = query.getResultList();
        return brindeList;
    }

    @Transactional
    public void salvar(Brinde brinde) {
        em.merge(brinde);
    }

    @Transactional
    public void delete(Brinde brinde) {
        em.remove(em.contains(brinde) ? brinde : em.merge(brinde));
    }

}
