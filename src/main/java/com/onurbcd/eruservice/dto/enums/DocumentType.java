package com.onurbcd.eruservice.dto.enums;

public enum DocumentType {

    /**
     * Boleto
     */
    BILLET,

    /**
     * Fatura
     */
    INVOICE,

    /**
     * Conta - para os casos da faxina, contador, vigilante ou conta da água
     */
    BILL,

    /**
     * Documento de Arrecadação de Receitas Federais
     */
    DARF,

    /**
     * Documento de Arrecadação do Simples Nacional
     */
    DAS,

    /**
     * Recibo - para o caso do vigilante
     */
    RECEIPT,

    /**
     * Papel - para o caso que não tem formato digital, mas sempre pode ser scaneado
     */
    PAPER
}
