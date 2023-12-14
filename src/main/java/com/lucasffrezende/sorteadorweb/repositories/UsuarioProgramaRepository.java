package com.lucasffrezende.sorteadorweb.repositories;

import com.lucasffrezende.sorteadorweb.models.Pessoa;
import com.lucasffrezende.sorteadorweb.models.Programa;
import com.lucasffrezende.sorteadorweb.models.Usuario;
import com.lucasffrezende.sorteadorweb.models.UsuarioPrograma;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class UsuarioProgramaRepository {

    @PersistenceContext
    private EntityManager em;

    public List<UsuarioPrograma> buscaDinamica(UsuarioPrograma usuarioPrograma) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<UsuarioPrograma> criteriaQuery = criteriaBuilder.createQuery(UsuarioPrograma.class);
        Root<UsuarioPrograma> root = criteriaQuery.from(UsuarioPrograma.class);

        List<Predicate> predicates = new ArrayList<>();

        if (usuarioPrograma.getPrograma() != null) {
            Join<UsuarioPrograma, Programa> programaJoin = root.join("programa");

            if (usuarioPrograma.getPrograma().getNome() != null &&
                    !usuarioPrograma.getPrograma().getNome().isBlank()) {
                predicates.add(criteriaBuilder.like(programaJoin.get("nome"),
                        "%" + usuarioPrograma.getPrograma().getNome() + "%"));
            }
        }

        if (usuarioPrograma.getUsuario() != null && usuarioPrograma.getUsuario().getPessoa() != null) {
            Join<UsuarioPrograma, Usuario> usuarioJoin = root.join("usuario");
            Join<Usuario, Pessoa> pessoaJoin = usuarioJoin.join("pessoa");

            if (usuarioPrograma.getUsuario().getPessoa().getNome() != null &&
                    !usuarioPrograma.getUsuario().getPessoa().getNome().isBlank()) {
                predicates.add(criteriaBuilder.like(pessoaJoin.get("nome"),
                        "%" + usuarioPrograma.getUsuario().getPessoa().getNome() + "%"));
            }
        }
        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        return em.createQuery(criteriaQuery).getResultList();
    }

    @Transactional
    public void salvar(UsuarioPrograma usuarioPrograma) {
        em.merge(usuarioPrograma);
    }

    @Transactional
    public void delete(UsuarioPrograma usuarioPrograma) {
        em.remove(em.contains(usuarioPrograma) ? usuarioPrograma : em.merge(usuarioPrograma));
    }

}
