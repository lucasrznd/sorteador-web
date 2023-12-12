package com.lucasffrezende.sorteadorweb.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_brinde")
public class Brinde extends GenericDomain {

    @ManyToOne
    private TipoBrinde tipoBrinde;
    private String descricao;

    @ManyToOne
    private EmpresaAssociada empresaAssociada;

}
