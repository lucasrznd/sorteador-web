package com.lucasffrezende.sorteadorweb.repositories;

import com.lucasffrezende.sorteadorweb.models.Usuario;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class UsuarioRepository {

    @PersistenceContext
    private EntityManager em;

    public Usuario autenticar(String login, String senha) {
        try {
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<Usuario> criteriaQuery = criteriaBuilder.createQuery(Usuario.class);
            Root<Usuario> usuarioRoot = criteriaQuery.from(Usuario.class);

            Md5Hash md5Hash = new Md5Hash(senha);
            criteriaQuery.where(
                    criteriaBuilder.equal(usuarioRoot.get("login"), login),
                    criteriaBuilder.equal(usuarioRoot.get("senha"), md5Hash.toHex())
            );

            TypedQuery<Usuario> typedQuery = em.createQuery(criteriaQuery);
            return typedQuery.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Usuario> buscaPorNome(String nome) {
        Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.pessoa.nome LIKE :nome");
        query.setParameter("nome", "%" + nome + "%");

        List<Usuario> usuarioList = query.getResultList();
        return usuarioList;
    }

    @Transactional
    public void salvar(Usuario usuario) {
        Md5Hash md5Hash = new Md5Hash(usuario.getSenha());
        usuario.setSenha(md5Hash.toHex());
        usuario.setAtivo(true);

        em.merge(usuario);
    }

    @Transactional
    public void editar(Usuario usuario) {
        Md5Hash md5Hash = new Md5Hash(usuario.getSenha());
        usuario.setSenha(md5Hash.toHex());

        em.merge(usuario);
    }

    @Transactional
    public void delete(Usuario usuario) {
        em.remove(em.contains(usuario) ? usuario : em.merge(usuario));
    }

}
