package com.lucasffrezende.sorteadorweb.models;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Endereco {

    private String rua;
    private String bairro;
    private Integer numero;
    private String cidade;

}
