package com.lucasffrezende.sorteadorweb.repositories;

import com.lucasffrezende.sorteadorweb.models.UsuarioPrograma;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UsuarioProgramaRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void salvar(UsuarioPrograma usuarioPrograma) {
        em.merge(usuarioPrograma);
    }

    @Transactional
    public void delete(UsuarioPrograma usuarioPrograma) {
        em.remove(em.contains(usuarioPrograma) ? usuarioPrograma : em.merge(usuarioPrograma));
    }

}
