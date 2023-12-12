package com.lucasffrezende.sorteadorweb.repositories;

import com.lucasffrezende.sorteadorweb.models.Endereco;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.util.List;

public class EnderecoRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Endereco> listar() {
        Query query = em.createQuery("SELECT e FROM Endereco e ORDER BY e.bairro");
        List<Endereco> enderecoList = query.getResultList();
        return enderecoList;
    }

    public void salvar(Endereco endereco) {
        em.merge(endereco);
    }

    public void delete(Endereco endereco) {
        em.remove(endereco);
    }
    
}
