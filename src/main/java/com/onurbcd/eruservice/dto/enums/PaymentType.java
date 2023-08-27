package com.onurbcd.eruservice.dto.enums;

public enum PaymentType {

    /**
     * Modo de transferência monetária instantâneo e de pagamento eletrônico instantâneo em real brasileiro
     */
    PIX,

    /**
     * Código de Barras - no caso de boletos
     */
    BAR_CODE,

    /**
     * Transferência - no caso de pagamentos de faturas de cartão de crédito, em que o dinheiro sai direto da conta escolhida
     */
    TRANSFER
}
