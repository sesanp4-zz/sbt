/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author centricgateway
 */
@Entity
@Table(name = "WalletTransactionNotification")
public class WalletTransactionNotification {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    
    String originatoraccountnumber, amount, originatorname, narration, craccountname,
    paymentreference, sessionid, bankname, craccount, bankcode;

    public String getOriginatoraccountnumber() {
        return originatoraccountnumber;
    }

    public void setOriginatoraccountnumber(String originatoraccountnumber) {
        this.originatoraccountnumber = originatoraccountnumber;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getOriginatorname() {
        return originatorname;
    }

    public void setOriginatorname(String originatorname) {
        this.originatorname = originatorname;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public String getCraccountname() {
        return craccountname;
    }

    public void setCraccountname(String craccountname) {
        this.craccountname = craccountname;
    }

    public String getPaymentreference() {
        return paymentreference;
    }

    public void setPaymentreference(String paymentreference) {
        this.paymentreference = paymentreference;
    }

    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public String getCraccount() {
        return craccount;
    }

    public void setCraccount(String craccount) {
        this.craccount = craccount;
    }

    public String getBankcode() {
        return bankcode;
    }

    public void setBankcode(String bankcode) {
        this.bankcode = bankcode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
}
