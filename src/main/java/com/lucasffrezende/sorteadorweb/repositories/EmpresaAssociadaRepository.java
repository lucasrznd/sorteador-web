package com.lucasffrezende.sorteadorweb.repositories;

import com.lucasffrezende.sorteadorweb.models.EmpresaAssociada;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    public List<EmpresaAssociada> buscaDinamica(EmpresaAssociada empresaAssociada) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<EmpresaAssociada> criteriaQuery = criteriaBuilder.createQuery(EmpresaAssociada.class);
        Root<EmpresaAssociada> root = criteriaQuery.from(EmpresaAssociada.class);

        List<Predicate> predicates = new ArrayList<>();

        if (empresaAssociada.getNome() != null && !empresaAssociada.getNome().isBlank()) {
            predicates.add(criteriaBuilder.like(root.get("nome"), "%" + empresaAssociada.getNome()
                    + "%"));
        }

        if (empresaAssociada.getTelefone() != null && !empresaAssociada.getTelefone().isBlank()) {
            predicates.add(criteriaBuilder.equal(root.get("telefone"), empresaAssociada.getTelefone()));
        }

        if (empresaAssociada.getEndereco().getCidade() != null && !empresaAssociada.getEndereco().getCidade().isBlank()) {
            predicates.add(criteriaBuilder.like(root.get("endereco").get("cidade"),
                    "%" + empresaAssociada.getEndereco().getCidade() + "%"));
        }

        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        return em.createQuery(criteriaQuery).getResultList();
    }

    public List<EmpresaAssociada> buscaPorNome(String nome) {
        Query query = em.createQuery("SELECT e FROM EmpresaAssociada e WHERE e.nome LIKE :nome");
        query.setParameter("nome", "%" + nome + "%");

        List<EmpresaAssociada> empresaAssociadaList = query.getResultList();
        return empresaAssociadaList;
    }

    public EmpresaAssociada buscaPorCodigo(Long codigo) {
        EmpresaAssociada empresaAssociada = em.find(EmpresaAssociada.class, codigo);
        return empresaAssociada;
    }

    @Transactional
    public void salvar(EmpresaAssociada empresaAssociada) {
        em.merge(empresaAssociada);
    }

    @Transactional
    public void delete(EmpresaAssociada empresaAssociada) {
        em.remove(em.contains(empresaAssociada) ? empresaAssociada : em.merge(empresaAssociada));
    }

}
