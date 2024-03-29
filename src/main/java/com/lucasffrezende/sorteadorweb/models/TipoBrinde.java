package com.lucasffrezende.sorteadorweb.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_tipo_brinde")
public class TipoBrinde extends GenericDomain {

    @Column(name = "tipo")
    private String tipo;

}
