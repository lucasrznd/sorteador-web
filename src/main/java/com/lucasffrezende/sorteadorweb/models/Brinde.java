package com.lucasffrezende.sorteadorweb.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_brinde")
public class Brinde extends GenericDomain {

    @ManyToOne
    @JoinColumn(name = "tipo_brinde_codigo")
    private TipoBrinde tipoBrinde;

    @Column(name = "descricao")
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "empresa_associada_codigo")
    private EmpresaAssociada empresaAssociada;

}
