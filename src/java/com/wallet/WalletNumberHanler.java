/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wallet;

import java.util.Random;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author centricgateway
 */
//@RequestScoped
public class WalletNumberHanler {
    
     String baseaccount="856";
    
    public String generateAccountNumber(){
       
         Random random = new Random();
         int number  = random.nextInt(9000000) + 1000000;
         String accountNumber=baseaccount.concat(Integer.toString(number));
         return accountNumber;
        
        // return set.toString();
    
    }
   
    
}
