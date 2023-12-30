package com.lucasffrezende.sorteadorweb.services;

import com.lucasffrezende.sorteadorweb.models.EmpresaAssociada;
import com.lucasffrezende.sorteadorweb.repositories.EmpresaAssociadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmpresaAssociadaService {

    @Autowired
    private EmpresaAssociadaRepository empresaAssociadaRepository;

    public List<EmpresaAssociada> listar() {
        return empresaAssociadaRepository.listar();
    }

    public List<EmpresaAssociada> buscaDinamica(EmpresaAssociada empresaAssociada) {
        return empresaAssociadaRepository.buscaDinamica(empresaAssociada);
    }

    public List<EmpresaAssociada> buscaPorNome(String nome) {
        return empresaAssociadaRepository.buscaPorNome(nome);
    }

    public EmpresaAssociada buscaPorCodigo(Long codigo) {
        return empresaAssociadaRepository.buscaPorCodigo(codigo);
    }

    public boolean salvar(EmpresaAssociada empresaAssociada) {
        EmpresaAssociada empresaExistente = empresaAssociadaRepository.buscaPorNomeUnico(empresaAssociada.getNome());
        if (empresaExistente == null) {
            empresaAssociadaRepository.salvar(empresaAssociada);
            return true;
        }

        return false;
    }

    public void editar(EmpresaAssociada empresaAssociada) {
        empresaAssociadaRepository.editar(empresaAssociada);
    }

    public void delete(EmpresaAssociada empresaAssociada) {
        empresaAssociadaRepository.delete(empresaAssociada);
    }

}
