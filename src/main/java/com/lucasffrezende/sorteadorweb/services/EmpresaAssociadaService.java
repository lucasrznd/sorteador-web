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

    public void salvar(EmpresaAssociada empresaAssociada) {
        empresaAssociadaRepository.salvar(empresaAssociada);
    }

    public void delete(EmpresaAssociada empresaAssociada) {
        empresaAssociadaRepository.delete(empresaAssociada);
    }

}
