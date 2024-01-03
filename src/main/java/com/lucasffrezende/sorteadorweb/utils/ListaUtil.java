package com.lucasffrezende.sorteadorweb.utils;

import org.omnifaces.util.Messages;

import java.util.List;

import static com.lucasffrezende.sorteadorweb.enums.MensagemEnum.MSG_NENHUM_REGISTRO;

public class ListaUtil<T> {

    public static <T> void verificaTamanhoLista(List<T> lista) {
        if (lista.isEmpty()) {
            Messages.addFlashGlobalWarn(MSG_NENHUM_REGISTRO.getMsg());
        }
    }

    public static <T> String listaString(List<T> list) {
        if (list == null || list.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (T item : list) {
            sb.append(item).append(", ");
        }
        return sb.substring(0, sb.length() - 2);
    }

}
