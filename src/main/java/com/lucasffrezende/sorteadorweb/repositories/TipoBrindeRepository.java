package com.lucasffrezende.sorteadorweb.repositories;

import com.lucasffrezende.sorteadorweb.models.TipoBrinde;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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

    public List<TipoBrinde> buscar(String tipo) {
        Query query = em.createQuery("SELECT tp FROM TipoBrinde tp WHERE tp.tipo LIKE :tipo");
        query.setParameter("tipo", "%" + tipo + "%");

        List<TipoBrinde> tipoBrindeList = query.getResultList();
        return tipoBrindeList;
    }

    @Transactional
    public void salvar(TipoBrinde tipoBrinde) {
        em.merge(tipoBrinde);
    }

    @Transactional
    public void delete(TipoBrinde tipoBrinde) {
        em.remove(em.contains(tipoBrinde) ? tipoBrinde : em.merge(tipoBrinde));
    }

}
