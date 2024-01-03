package com.lucasffrezende.sorteadorweb.repositories;

import com.lucasffrezende.sorteadorweb.models.Brinde;
import com.lucasffrezende.sorteadorweb.models.EmpresaAssociada;
import com.lucasffrezende.sorteadorweb.models.TipoBrinde;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    public List<Brinde> buscaDinamica(Brinde brinde) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Brinde> criteriaQuery = criteriaBuilder.createQuery(Brinde.class);
        Root<Brinde> root = criteriaQuery.from(Brinde.class);

        List<Predicate> predicates = new ArrayList<>();

        if (brinde.getTipoBrinde() != null) {
            Join<Brinde, TipoBrinde> tipoBrindeJoin = root.join("tipoBrinde");

            if (brinde.getTipoBrinde().getTipo() != null && !brinde.getTipoBrinde().getTipo().isBlank()) {
                predicates.add(criteriaBuilder.like(tipoBrindeJoin.get("tipo"), "%" +
                        brinde.getTipoBrinde().getTipo() + "%"));
            }
        }

        if (brinde.getDescricao() != null && !brinde.getDescricao().isBlank()) {
            predicates.add(criteriaBuilder.like(root.get("descricao"), "%" + brinde.getDescricao() + "%"));
        }

        if (brinde.getEmpresaAssociada() != null) {
            Join<Brinde, EmpresaAssociada> empresaAssociadaJoin = root.join("empresaAssociada");

            if (brinde.getEmpresaAssociada().getNome() != null && !brinde.getEmpresaAssociada().getNome().isBlank()) {
                predicates.add(criteriaBuilder.like(empresaAssociadaJoin.get("nome"), "%" +
                        brinde.getEmpresaAssociada().getNome() + "%"));
            }
        }

        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        return em.createQuery(criteriaQuery).getResultList();
    }

    public List<Brinde> buscaPorDescricao(String descricao) {
        Query query = em.createQuery("SELECT b FROM Brinde b WHERE b.descricao LIKE : descricao");
        query.setParameter("descricao", "%" + descricao + "%");
        List<Brinde> brindeList = query.getResultList();
        return brindeList;
    }

    public Brinde buscaPorCodigo(Long codigo) {
        Brinde brinde = em.find(Brinde.class, codigo);
        return brinde;
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
