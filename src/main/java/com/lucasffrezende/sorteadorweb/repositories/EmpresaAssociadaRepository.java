package com.lucasffrezende.sorteadorweb.repositories;

import com.lucasffrezende.sorteadorweb.models.EmpresaAssociada;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmpresaAssociadaRepository {

    @PersistenceContext
    private EntityManager em;

    public List<EmpresaAssociada> listar() {
        Query query = em.createQuery("SELECT ea FROM EmpresaAssociada ea ORDER BY ea.nome");
        List<EmpresaAssociada> empresaAssociadaList = query.getResultList();
        return empresaAssociadaList;
    }

    public void salvar(EmpresaAssociada empresaAssociada) {
        em.merge(empresaAssociada);
    }

    public void delete(EmpresaAssociada empresaAssociada) {
        em.remove(empresaAssociada);
    }
    
}
