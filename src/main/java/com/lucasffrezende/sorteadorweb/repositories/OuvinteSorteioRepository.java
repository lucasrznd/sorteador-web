package com.lucasffrezende.sorteadorweb.repositories;

import com.lucasffrezende.sorteadorweb.models.OuvinteSorteio;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class OuvinteSorteioRepository {

    @PersistenceContext
    private EntityManager em;

    public List<OuvinteSorteio> listar() {
        Query query = em.createQuery("SELECT o FROM OuvinteSorteio o");
        List<OuvinteSorteio> ouvinteSorteioList = query.getResultList();
        return ouvinteSorteioList;
    }

    @Transactional
    public void salvar(OuvinteSorteio ouvinteSorteio) {
        em.merge(ouvinteSorteio);
    }

    @Transactional
    public void delete(OuvinteSorteio ouvinteSorteio) {
        em.remove(ouvinteSorteio);
    }
    
}
