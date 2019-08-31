/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.v2.services;

import com.entities.Transaction;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.util.HibernateUtil;
import com.util.Utilities;
import com.v2.entities.TransactionLog;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author centricgateway
 */
public class TransactionLogService {
    
    
    SessionFactory sf;
    Session session;
    Query query ;
    
     JsonObject obj,obj2;
     Gson gson = new Gson();
     Utilities util = new Utilities();
     
    
     ExecutorService es = Executors.newFixedThreadPool(1);
    
       public TransactionLog addTransactionLog(TransactionLog object){
              TransactionLog transactionLog= null;
              
            try{
                 System.out.println("=========== Adding TranscationLog ========");
                 session= HibernateUtil.getSessionFactory().openSession();
                 session.beginTransaction();
                 Long id = (Long) session.save(object);
                 transactionLog = session.get(TransactionLog.class,id);
                 System.out.println("the object==="+gson.toJson(transactionLog)); 
                 session.getTransaction().commit(); 
                 return transactionLog;
           }catch(Exception e ){
                System.out.println("============error======");
                System.out.println("caused ===== "+e.getMessage());
//                obj2 = new JsonObject();
//                obj2.addProperty("code", "S7");
//                obj2.addProperty("message", "operation failed");
//                return obj2;
                  return transactionLog;
           } finally{
             if (session!=null) {
                   if(session.isOpen()) {
                       session.flush();
                       session.close();
                   }
                }
           }         
      } 
       
       
       
        public TransactionLog getTransactionLogByReference(String reference){
            
            TransactionLog transactionLog= null;
                        
            try{
                
                System.out.println("=========== Getting transaction Details started ========");
                session= HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                System.out.println("=========== About to fetch Transaction Details =========");
                query =  session.createQuery("from TransactionLog  transactionlog where transactionlog.reference=:reference");
                query.setParameter("reference", reference);
                transactionLog =(TransactionLog) query.uniqueResult();
                session.getTransaction().commit();
                System.out.println("=========== Done fetch Transaction Details =========");
                return transactionLog;

           }catch(Exception e ){
                System.out.println("============error======");
                System.out.println("caused ===== "+e.getMessage());
//                obj2 = new JsonObject();
//                obj2.addProperty("code", "S7");
//                obj2.addProperty("message", "operation failed");
 //               return obj2;
                  return transactionLog; 
           } finally{
             if (session!=null) {
                   if(session.isOpen()) {
                       session.flush();
                       session.close();
                   }
                }
           }         
      } 
        
        
        
//         public TransactionLog updateTransactionLog(TransactionLog transactionLog){
//             
//           TransactionLog transactionLog;
//             
//            try{
//                
//                session= HibernateUtil.getSessionFactory().openSession();
//                session.beginTransaction();
//
//           }catch(Exception e ){
//                System.out.println("============error======");
//                System.out.println("caused ===== "+e.getMessage());
//                obj2 = new JsonObject();
//                obj2.addProperty("code", "S7");
//                obj2.addProperty("message", "operation failed");
//                return obj2;
//           } finally{
//             if (session!=null) {
//                   if(session.isOpen()) {
//                       session.flush();
//                       session.close();
//                   }
//                }
//           }         
//      } 
//         
//         
//         
//          public TransactionLog deleteTransactionLog(TransactionLog transactionLog){
//            try{
//
//           }catch(Exception e ){
//                System.out.println("============error======");
//                System.out.println("caused ===== "+e.getMessage());
//                obj2 = new JsonObject();
//                obj2.addProperty("code", "S7");
//                obj2.addProperty("message", "operation failed");
//                return obj2;
//           } finally{
//             if (session!=null) {
//                   if(session.isOpen()) {
//                       session.flush();
//                       session.close();
//                   }
//                }
//           }         
//      } 
    
    public static void main(String[] args) {
        TransactionLog transactionLog = new TransactionLog();
        transactionLog.setAmount("2.00");
        transactionLog.setBusinessname("BC");
        transactionLog.setCountry("NGN");
        transactionLog.setCustomername("ssp");
        transactionLog.setReference("011");
        new TransactionLogService().addTransactionLog(transactionLog);
        System.out.println("done");

          System.out.println(new Gson().toJson(new TransactionLogService().getTransactionLogByReference("011")));
          System.out.println("done");

    }
}
