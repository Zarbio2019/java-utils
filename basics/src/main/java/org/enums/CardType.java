package org.enums;

public enum CardType {

    CREDIT_CARD,
    TG;

    /*************************************************/

    public static void main(String[] args) {

        System.out.println("CardType");
        String str = CardType.CREDIT_CARD.name(); // CREDIT_CARD
        System.out.println("str: "  + str);
    }
}
/* ref
PPGSR041IMPL.java/package com.bbva.ppgs.dto.proposals.enums;
*/