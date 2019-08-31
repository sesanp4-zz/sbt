/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.v2.entities;

import java.io.Serializable;
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
@Table(name = "TransactionLog")
public class TransactionLog implements Serializable {
       
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id; 
    
    @Column(name="publickey")        
    String publickey;
    
    @Column(name="reference",unique = true)                                                               
    String reference;
    
    @Column(name="linkingreference")        
    String linkingreference;
    
    @Column(name="mode")        
    String mode;
    
    @Column(name="status")        
    String status;
    
    @Column(name="customername")        
    String customername;
      
    @Column(name="email")        
    String email;
    
    @Column(name="source")        
    String source;
    
    @Column(name="sourcename")        
    String sourcename;
    
    @Column(name="mobile")        
    String mobile;
    
    @Column(name="country")        
    String country;
    
    @Column(name="currency")        
    String currency;
    
    @Column(name="businessname")        
    String businessname;
    
    @Column(name="description")        
    String description;
    
    @Column(name="amount")        
    String amount;
    
    @Column(name="fee")        
    String fee;
    
    @Column(name="creationdate")        
    String creationdate;
    
    @Column(name="callbackurl")        
    String callbackurl;
    
//    @Column(name="reference",columnDefinition = "")        
//    String redirecturl;
    
    @Column(name="number")        
    String number;
    
    @Column(name="sourceip")        
    String sourceip;
    
    @Column(name="devicetype")        
    String devicetype;
    
    @Column(name="gateway")        
    String gateway;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPublickey() {
        return publickey;
    }

    public void setPublickey(String publickey) {
        this.publickey = publickey;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getLinkingreference() {
        return linkingreference;
    }

    public void setLinkingreference(String linkingreference) {
        this.linkingreference = linkingreference;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSourcename() {
        return sourcename;
    }

    public void setSourcename(String sourcename) {
        this.sourcename = sourcename;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getBusinessname() {
        return businessname;
    }

    public void setBusinessname(String businessname) {
        this.businessname = businessname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getCreationdate() {
        return creationdate;
    }

    public void setCreationdate(String creationdate) {
        this.creationdate = creationdate;
    }

    public String getCallbackurl() {
        return callbackurl;
    }

    public void setCallbackurl(String callbackurl) {
        this.callbackurl = callbackurl;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSourceip() {
        return sourceip;
    }

    public void setSourceip(String sourceip) {
        this.sourceip = sourceip;
    }

    public String getDevicetype() {
        return devicetype;
    }

    public void setDevicetype(String devicetype) {
        this.devicetype = devicetype;
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }
    

    
    
}
