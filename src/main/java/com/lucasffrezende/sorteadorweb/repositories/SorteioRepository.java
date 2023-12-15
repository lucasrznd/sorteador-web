package com.lucasffrezende.sorteadorweb.repositories;

import com.lucasffrezende.sorteadorweb.models.Brinde;
import com.lucasffrezende.sorteadorweb.models.Programa;
import com.lucasffrezende.sorteadorweb.models.Sorteio;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
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
    public List<Sorteio> buscaDinamica(Sorteio sorteio) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Sorteio> criteriaQuery = criteriaBuilder.createQuery(Sorteio.class);
        Root<Sorteio> root = criteriaQuery.from(Sorteio.class);

        List<Predicate> predicates = new ArrayList<>();

        if (sorteio.getPrograma() != null) {
            Join<Sorteio, Programa> programaJoin = root.join("programa");

            if (sorteio.getPrograma().getNome() != null && !sorteio.getPrograma().getNome().isBlank()) {
                predicates.add(criteriaBuilder.equal(programaJoin.get("nome"), sorteio.getPrograma().getNome()));
            }
        }

        if (sorteio.getDataHora() != null) {
            Expression<LocalDate> dateFunction = criteriaBuilder.function("DATE", LocalDate.class, root.get("dataHora"));
            predicates.add(criteriaBuilder.equal(dateFunction, sorteio.getDataHora()));
        }

        if (sorteio.getBrinde() != null) {
            Join<Sorteio, Brinde> brindeJoin = root.join("brinde");

            if (sorteio.getBrinde().getDescricao() != null && !sorteio.getBrinde().getDescricao().isBlank()) {
                predicates.add(criteriaBuilder.equal(brindeJoin.get("descricao"), sorteio.getBrinde().getDescricao()));
            }
        }

        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        return em.createQuery(criteriaQuery).getResultList();
    }

    public Sorteio buscaPorCodigo(Long codigo) {
        Sorteio sorteio = em.find(Sorteio.class, codigo);
        return sorteio;
    }

    public Sorteio obterUltimoSorteio() {
        Query query = em.createQuery("SELECT s FROM Sorteio s ORDER BY s.codigo DESC LIMIT 1");
        Sorteio sorteio = (Sorteio) query.getSingleResult();
        return sorteio;
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
