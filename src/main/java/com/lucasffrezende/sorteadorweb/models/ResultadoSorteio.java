package com.lucasffrezende.sorteadorweb.models;

import jakarta.persistence.*;
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

    @Column(name = "data_hora")
    private LocalDateTime dataHora;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "sorteio_codigo")
    private Sorteio sorteio;

    @ManyToOne
    @JoinColumn(name = "ouvinte_codigo")
    private Ouvinte ouvinte;

}
