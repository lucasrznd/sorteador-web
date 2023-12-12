package com.lucasffrezende.sorteadorweb.repositories;

import com.lucasffrezende.sorteadorweb.models.TipoBrinde;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TipoBrindeRepository {

    @PersistenceContext
    private EntityManager em;

    public List<TipoBrinde> listar() {
        Query query = em.createQuery("SELECT tp FROM TipoBrinde tp ORDER BY tp.tipo");
        List<TipoBrinde> tipoBrindeList = query.getResultList();
        return tipoBrindeList;
    }

    public void salvar(TipoBrinde tipoBrinde) {
        em.merge(tipoBrinde);
    }

    public void delete(TipoBrinde tipoBrinde) {
        em.remove(tipoBrinde);
    }
    
}
