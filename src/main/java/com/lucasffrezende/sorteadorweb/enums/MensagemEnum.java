package com.lucasffrezende.sorteadorweb.enums;

import lombok.Getter;

@Getter
public enum MensagemEnum {

    MSG_SALVO_SUCESSO("Salvo com sucesso."),
    MSG_IMPORT_SUCESSO("Importado com sucesso."),
    MSG_EXCLUIDO_SUCESSO("Excluido com sucesso."),
    MSG_OPERACAO_CUMCLUIDA_SUCESSO("Operação concluída com sucesso."),
    MSG_NENHUM_REGISTRO("Nenhum registro encontrado.");

    private String msg;

    private MensagemEnum(String msg) {
        this.msg = msg;
    }

}
