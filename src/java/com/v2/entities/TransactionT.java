/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.v2.entities;

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
@Table(name = "TransactionV2")
public class TransactionT {
       
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id; 
    
    String publickey;
    
    
    String reference;
    
    String status;
    
    String customername;
    
    
    String email;
    
    
    String source;
    
    
    String mobile;
    
    String country;
    
    String currency;
    
    String businessname;
    
    String description;
    
    String amount;
    
    String fee;
    
    String creationdate;
    
    
    
    
}
