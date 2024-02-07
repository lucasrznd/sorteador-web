package com.lucasffrezende.sorteadorweb.repositories;

import com.lucasffrezende.sorteadorweb.models.Ouvinte;
import com.lucasffrezende.sorteadorweb.models.Pessoa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class OuvinteRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Ouvinte> listar() {
        Query query = em.createQuery("SELECT o FROM Ouvinte o ORDER BY o.pessoa.nome");
        List<Ouvinte> ouvinteList = query.getResultList();
        return ouvinteList;
    }

    public Ouvinte buscaPorNomeETelefone(String nome, String telefone) {
        try {
            Query query = em.createQuery("SELECT o FROM Ouvinte o WHERE o.pessoa.nome = :nome AND o.pessoa.telefone = :telefone");
            query.setParameter("nome", nome);
            query.setParameter("telefone", telefone);
            Ouvinte ouvinte = (Ouvinte) query.getSingleResult();
            return ouvinte;
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Ouvinte> buscaDinamica(Ouvinte ouvinte) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Ouvinte> criteriaQuery = criteriaBuilder.createQuery(Ouvinte.class);
        Root<Ouvinte> root = criteriaQuery.from(Ouvinte.class);

        List<Predicate> predicates = new ArrayList<>();

        if (ouvinte.getPessoa() != null) {
            Join<Ouvinte, Pessoa> pessoaJoin = root.join("pessoa");

            if (ouvinte.getPessoa().getNome() != null && !ouvinte.getPessoa().getNome().isBlank()) {
                predicates.add(criteriaBuilder.like(pessoaJoin.get("nome"), "%" +
                        ouvinte.getPessoa().getNome() + "%"));
            }

            if (ouvinte.getPessoa().getTelefone() != null && !ouvinte.getPessoa().getTelefone().isBlank()) {
                predicates.add(criteriaBuilder.equal(pessoaJoin.get("telefone"), ouvinte.getPessoa().getTelefone()));
            }

            if (ouvinte.getPessoa().getEndereco().getBairro() != null &&
                    !ouvinte.getPessoa().getEndereco().getBairro().isBlank()) {
                predicates.add(criteriaBuilder.like(pessoaJoin.get("endereco").get("bairro"), "%"
                        + ouvinte.getPessoa().getEndereco().getBairro() + "%"));
            }

            if (ouvinte.getPessoa().getEndereco().getCidade() != null
                    && !ouvinte.getPessoa().getEndereco().getCidade().isBlank()) {
                predicates.add(criteriaBuilder.like(pessoaJoin.get("endereco").get("cidade"), "%" +
                        ouvinte.getPessoa().getEndereco().getCidade() + "%"));
            }
        }

        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        return em.createQuery(criteriaQuery).getResultList();
    }

    public Ouvinte buscaPorCodigo(Long codigo) {
        Ouvinte ouvinte = em.find(Ouvinte.class, codigo);
        return ouvinte;
    }

    @Transactional
    public void salvar(Ouvinte ouvinte) {
        em.merge(ouvinte);
    }

    @Transactional
    public void editar(Ouvinte ouvinte) {
        em.merge(ouvinte);
    }

    @Transactional
    public void delete(Ouvinte ouvinte) {
        em.remove(em.contains(ouvinte) ? ouvinte : em.merge(ouvinte));
    }

}
