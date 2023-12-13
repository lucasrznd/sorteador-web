package com.lucasffrezende.sorteadorweb.repositories;

import com.lucasffrezende.sorteadorweb.models.ResultadoSorteio;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResultadoSorteioRepository {

    @PersistenceContext
    private EntityManager em;

    public List<ResultadoSorteio> listar() {
        Query query = em.createQuery("SELECT rs FROM ResultadoSorteio rs");
        List<ResultadoSorteio> resultadoSorteioList = query.getResultList();
        return resultadoSorteioList;
    }

    public void salvar(ResultadoSorteio resultadoSorteio) {
        em.merge(resultadoSorteio);
    }

    public void delete(ResultadoSorteio resultadoSorteio) {
        em.remove(resultadoSorteio);
    }
    
}