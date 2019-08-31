/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.v2.entities;

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
@Table(name = "CoreLog")
public class CoreLog {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    
    @Column(name="message",columnDefinition = "")
    String message;
    
    @Column(name="code",columnDefinition = "")
    String code;
    
    @Column(name="linkingreference",columnDefinition = "")
    String linkingreference;
    
    @Column(name="reference",columnDefinition = "")
    String reference;
    
    @Column(name="datacreated",columnDefinition = "")
    String datacreated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLinkingreference() {
        return linkingreference;
    }

    public void setLinkingreference(String linkingreference) {
        this.linkingreference = linkingreference;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getDatacreated() {
        return datacreated;
    }

    public void setDatacreated(String datacreated) {
        this.datacreated = datacreated;
    }
    
    
    
}
