package com.lucasffrezende.sorteadorweb.repositories;

import com.lucasffrezende.sorteadorweb.models.Sorteio;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class SorteioRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Sorteio> listar() {
        Query query = em.createQuery("SELECT s FROM Sorteio s ORDER BY s.id DESC");
        List<Sorteio> sorteioList = query.getResultList();
        return sorteioList;
    }

    @Transactional
    public void salvar(Sorteio sorteio) {
        em.merge(sorteio);
    }

    @Transactional
    public void delete(Sorteio sorteio) {
        em.remove(em.contains(sorteio) ? sorteio : em.merge(sorteio));
    }

}
