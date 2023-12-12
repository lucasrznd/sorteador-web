package com.lucasffrezende.sorteadorweb.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@MappedSuperclass
public class GenericDomain implements Serializable {

    private static final long serialVersionUID = 1L;

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @Override
    public String toString() {
        return String.format("%s[codigo%d]", getClass().getSimpleName(), getCodigo());
    }

}
