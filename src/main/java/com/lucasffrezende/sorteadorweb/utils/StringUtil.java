package com.lucasffrezende.sorteadorweb.utils;

import com.lucasffrezende.sorteadorweb.models.ResultadoSorteio;
import com.lucasffrezende.sorteadorweb.models.Sorteio;
import com.lucasffrezende.sorteadorweb.models.UsuarioPrograma;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StringUtil {

    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public static String mensagemGanhador(ResultadoSorteio resultadoSorteio, UsuarioPrograma usuarioPrograma) {
        String nomeGanhador = resultadoSorteio.getOuvinte().getPessoa().getNome();
        String descricaoBrinde = resultadoSorteio.getSorteio().getBrinde().getDescricao();
        String nomeEmpresa = resultadoSorteio.getSorteio().getBrinde().getEmpresaAssociada().getNome();
        String nomePrograma = resultadoSorteio.getSorteio().getPrograma().getNome();
        String nomeLocutor = usuarioPrograma.getUsuario().getPessoa().getNome();
        LocalDateTime dataHoraSorteio = resultadoSorteio.getDataHora();

        return String.format("Parabéns %s, você foi o(a) ganhador(a) do Brinde %s da Empresa %s" +
                        " no programa %s do Locutor %s, na data %s.",
                nomeGanhador, descricaoBrinde, nomeEmpresa, nomePrograma, nomeLocutor, dtf.format(dataHoraSorteio));
    }
}
