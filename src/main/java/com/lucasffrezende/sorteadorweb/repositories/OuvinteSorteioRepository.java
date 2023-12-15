package com.lucasffrezende.sorteadorweb.repositories;

import com.lucasffrezende.sorteadorweb.models.OuvinteSorteio;
import com.lucasffrezende.sorteadorweb.models.Sorteio;
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
        Query query = em.createQuery("SELECT o FROM OuvinteSorteio o ORDER BY o.codigo DESC");
        List<OuvinteSorteio> ouvinteSorteioList = query.getResultList();
        return ouvinteSorteioList;
    }

    public List<OuvinteSorteio> buscaDinamica() {
        Query query = em.createQuery("SELECT os FROM OuvinteSorteio os ORDER BY os.codigo DESC");
        List<OuvinteSorteio> ouvinteSorteioList = query.getResultList();
        return ouvinteSorteioList;
    }

    public List<OuvinteSorteio> buscaPorSorteio(Sorteio sorteio) {
        Query query = em.createQuery("SELECT os FROM OuvinteSorteio os WHERE os.sorteio = :sorteio");
        query.setParameter("sorteio", sorteio);
        List<OuvinteSorteio> ouvinteSorteioList = query.getResultList();
        return ouvinteSorteioList;
    }

    public OuvinteSorteio buscaPorCodigo(Long codigo) {
        OuvinteSorteio ouvinteSorteio = em.find(OuvinteSorteio.class, codigo);
        return ouvinteSorteio;
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
