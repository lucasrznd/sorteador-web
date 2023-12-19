package com.lucasffrezende.sorteadorweb.utils;

import com.lucasffrezende.sorteadorweb.models.Sorteio;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StringUtil {

    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public static String mensagemGanhador(Sorteio sorteio) {
        String nomeGanhador = sorteio.getResultado().getOuvinte().getPessoa().getNome();
        String descricaoBrinde = sorteio.getBrinde().getDescricao();
        String nomeEmpresa = sorteio.getBrinde().getEmpresaAssociada().getNome();
        String nomePrograma = sorteio.getPrograma().getNome();
        LocalDateTime dataHoraSorteio = sorteio.getResultado().getDataHora();

        return String.format("Parabéns %s, você foi o(a) ganhador(a) do Brinde %s da Empresa %s" +
                        " no programa %s, na data %s.",
                nomeGanhador, descricaoBrinde, nomeEmpresa, nomePrograma, dtf.format(dataHoraSorteio));
    }
}
