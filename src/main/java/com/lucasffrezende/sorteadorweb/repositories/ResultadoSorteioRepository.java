package com.lucasffrezende.sorteadorweb.repositories;

import com.lucasffrezende.sorteadorweb.models.Ouvinte;
import com.lucasffrezende.sorteadorweb.models.ResultadoSorteio;
import com.lucasffrezende.sorteadorweb.models.Sorteio;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class ResultadoSorteioRepository {

    @PersistenceContext
    private EntityManager em;

    public List<ResultadoSorteio> listar() {
        Query query = em.createQuery("SELECT rs FROM ResultadoSorteio rs WHERE rs.ouvinte IS NOT NULL ORDER BY rs.codigo DESC");
        query.setMaxResults(15);
        List<ResultadoSorteio> resultadoSorteioList = query.getResultList();
        return resultadoSorteioList;
    }

    public List<ResultadoSorteio> buscaDinamica(ResultadoSorteio resultadoSorteio) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<ResultadoSorteio> criteriaQuery = criteriaBuilder.createQuery(ResultadoSorteio.class);
        Root<ResultadoSorteio> root = criteriaQuery.from(ResultadoSorteio.class);

        List<Predicate> predicates = new ArrayList<>();

        if (resultadoSorteio.getSorteio() != null) {
            Join<ResultadoSorteio, Sorteio> sorteioJoin = root.join("sorteio");

            if (resultadoSorteio.getSorteio().getPrograma() != null) {
                if (resultadoSorteio.getSorteio().getPrograma().getNome() != null &&
                        !resultadoSorteio.getSorteio().getPrograma().getNome().isBlank()) {
                    predicates.add(criteriaBuilder.equal(sorteioJoin.get("programa").get("nome"),
                            resultadoSorteio.getSorteio().getPrograma().getNome()));
                }
            }

            if (resultadoSorteio.getSorteio().getBrinde() != null) {
                if (resultadoSorteio.getSorteio().getBrinde().getDescricao() != null
                        && !resultadoSorteio.getSorteio().getBrinde().getDescricao().isBlank()) {
                    predicates.add(criteriaBuilder.equal(sorteioJoin.get("brinde").get("descricao"),
                            resultadoSorteio.getSorteio().getBrinde().getDescricao()));
                }
            }
        }

        if (resultadoSorteio.getDataHora() != null) {
            predicates.add(criteriaBuilder.equal(root.get("dataHora"), resultadoSorteio.getDataHora()));
        }

        if (resultadoSorteio.getOuvinte() != null) {
            Join<ResultadoSorteio, Ouvinte> ouvinteJoin = root.join("ouvinte");

            if (resultadoSorteio.getOuvinte().getPessoa().getNome() != null &&
                    !resultadoSorteio.getOuvinte().getPessoa().getNome().isBlank()) {
                predicates.add(criteriaBuilder.like(ouvinteJoin.get("pessoa").get("nome"),
                        "%" + resultadoSorteio.getOuvinte().getPessoa().getNome() + "%"));
            }
        }

        criteriaQuery.where(predicates.toArray(new Predicate[0]));
        criteriaQuery.orderBy(criteriaBuilder.desc(root.get("codigo")));

        // Definindo o limite de resultados para os últimos 25
        TypedQuery<ResultadoSorteio> query = query = em.createQuery(criteriaQuery).setMaxResults(25);

        return query.getResultList();
    }

    public ResultadoSorteio buscaPorSorteio(Sorteio sorteio) {
        try {
            Query query = em.createQuery("SELECT rs FROM ResultadoSorteio rs WHERE rs.sorteio = :sorteio");
            query.setParameter("sorteio", sorteio);
            ResultadoSorteio resultadoSorteio = (ResultadoSorteio) query.getSingleResult();
            return resultadoSorteio;
        } catch (Exception e) {
            //e.printStackTrace();
            return null;
        }
    }

    @Transactional
    public void salvar(ResultadoSorteio resultadoSorteio) {
        em.merge(resultadoSorteio);
    }

    @Transactional
    public void delete(ResultadoSorteio resultadoSorteio) {
        em.remove(em.contains(resultadoSorteio) ? resultadoSorteio : em.merge(resultadoSorteio));
    }

}
