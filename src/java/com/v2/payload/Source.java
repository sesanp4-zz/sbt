/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.v2.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author centricgateway
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Source {
    
    Card card;
    Bank bank;
    Transfer transfer;

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public Transfer getTransfer() {
        return transfer;
    }

    public void setTransfer(Transfer transfer) {
        this.transfer = transfer;
    }
    
    
    
    
    
}
