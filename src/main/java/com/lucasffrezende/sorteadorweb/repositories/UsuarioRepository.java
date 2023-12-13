package com.lucasffrezende.sorteadorweb.repositories;

import com.lucasffrezende.sorteadorweb.models.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuarioRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Usuario> listar() {
        Query query = em.createQuery("SELECT u FROM Usuario u ORDER BY u.login");
        List<Usuario> usuarioList = query.getResultList();
        return usuarioList;
    }

    public void salvar(Usuario usuario) {
        em.merge(usuario);
    }

    public void delete(Usuario usuario) {
        em.remove(usuario);
    }
    
}