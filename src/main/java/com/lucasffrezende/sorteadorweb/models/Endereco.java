package com.lucasffrezende.sorteadorweb.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Endereco {

    @Column(name = "rua")
    private String rua;

    @Column(name = "bairro")
    private String bairro;

    @Column(name = "numero")
    private Integer numero;

    @Column(name = "cidade")
    private String cidade;

}
