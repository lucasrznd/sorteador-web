package com.lucasffrezende.sorteadorweb.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_resultado_sorteio")
public class ResultadoSorteio extends GenericDomain {

    private LocalDateTime dataHora;

    @OneToOne
    private Sorteio sorteio;

    @ManyToOne
    private Ouvinte ouvinte;

}
