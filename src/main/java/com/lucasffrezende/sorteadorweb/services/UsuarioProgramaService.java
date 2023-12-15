package com.lucasffrezende.sorteadorweb.services;

import com.lucasffrezende.sorteadorweb.models.UsuarioPrograma;
import com.lucasffrezende.sorteadorweb.repositories.UsuarioProgramaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuarioProgramaService {

    @Autowired
    private UsuarioProgramaRepository usuarioProgramaRepository;

    public List<UsuarioPrograma> buscaDinamica(UsuarioPrograma usuarioPrograma) {
        return usuarioProgramaRepository.buscaDinamica(usuarioPrograma);
    }

    public void salvar(UsuarioPrograma usuarioPrograma) {
        usuarioPrograma.getPrograma().setAtivo(true);

        usuarioProgramaRepository.salvar(usuarioPrograma);
    }

    public void editar(UsuarioPrograma usuarioPrograma) {
        usuarioProgramaRepository.salvar(usuarioPrograma);
    }

    public void delete(UsuarioPrograma usuarioPrograma) {
        usuarioProgramaRepository.delete(usuarioPrograma);
    }

}
