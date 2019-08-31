/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.v2.services;

import com.entities.Transaction;
import com.entities.WebhookEvent;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.util.Dao;
import com.util.DateTimeFormater;
import com.v2.entities.TransactionLog;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author centricgateway
 */
public class ThirdPartyServices {
    
    
    CloseableHttpClient client = HttpClients.createDefault();
    HttpPost post;
    HttpGet get;
    CloseableHttpResponse response;
    Gson gson = new Gson() ;
    JsonObject obj = new JsonObject();
    JsonObject obj2;
    
    ExecutorService es = Executors.newFixedThreadPool(1);
    
    public String getThirdPartyApi() throws UnsupportedEncodingException, IOException{
        post = new HttpPost(getAppProperties().getProperty("seerbit_auth_endpoint"));
        post.setHeader("Content-Type", "application/x-www-form-urlencoded");
        
        String clientId=getAppProperties().getProperty("client_Id");
        String clientSecret=getAppProperties().getProperty("client_Secret");  
        List<NameValuePair>list= new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("grant_type", "client_credentials"));
        list.add(new BasicNameValuePair("client_id", clientId));
        list.add(new BasicNameValuePair("client_secret",clientSecret));
        post.setEntity(new UrlEncodedFormEntity(list));
        response=client.execute(post);
        String msg = EntityUtils.toString(response.getEntity());
        return msg;
     }
    
    
    
      public JsonObject fectchMerchantInfo(String mid){
         try{
             get = new HttpGet(getAppProperties().getProperty("verify_merchant_info")+mid);
             response=client.execute(get);
             String msg = EntityUtils.toString(response.getEntity());
             System.out.println("======msg"+msg);
             obj = new JsonParser().parse(msg).getAsJsonObject();
             System.out.println("merchant info response...."+obj);
             JsonObject obj2 = new JsonObject();
               if((obj.get("responseCode").getAsString()).equals("00")){
                   obj2.addProperty("code", "00");
                   obj2.addProperty("currency", obj.get("payload").getAsJsonObject().get("default_currency").getAsString());
                   obj2.addProperty("max_amount", obj.get("payload").getAsJsonObject().get("max_amount").getAsString());
                   obj2.addProperty("min_amount", obj.get("payload").getAsJsonObject().get("min_amount").getAsString());
                   obj2.addProperty("test_publickey", obj.get("payload").getAsJsonObject().get("test_public_key").getAsString());
                   obj2.addProperty("live_publickey", obj.get("payload").getAsJsonObject().get("live_public_key").getAsString());
               }else{
                   System.out.println("somefin is wrong");
                   obj2.addProperty("code", "S18");
                   obj2.addProperty("message","Merchant does not exist");
               }  
               return obj2;
             
             
         }catch(Exception e){
           obj = new JsonObject();
           obj.addProperty("code", "S23");
           obj.addProperty("message", "operation failed");
           System.out.println("cause...."+e.getMessage());
           return obj;
         }
    }
      
      
          public void sendNotification(String reference){
    
          try{
              
              Dao dao = new Dao();
              
              // Construct Object to be sent  
             Transaction transaction =  dao.ngetTransactionDetails(reference);
              System.out.println("tran oooo"+gson.toJson(transaction));
              System.out.println("updating the webhookevent");
              WebhookEvent event = new WebhookEvent();
              event.setGeneratedAt(DateTimeFormater.getRealTimeFormat());
              event.setAmount(transaction.getUserinfo().getTransactionInfo().getAmount());
              event.setMobile(transaction.getUserinfo().getMobile());
              event.setName(transaction.getUserinfo().getFullname());
              //event.setPublickey(transaction.getUserinfo().getTransactionInfo().getPublic_key());
              event.setReference(transaction.getRef());
              event.setStatus(transaction.getStatus());
              
              
              System.out.println("trans  is still===="+gson.toJson(transaction));
               
              
              //fetch merchant webhook url from User-Management System
              // obj = fectchMerchantInfo(transaction.getUserinfo().getTransactionInfo().getPublic_key());
              obj = getMerchantInfo(transaction.getUserinfo().getTransactionInfo().getPublic_key());
              System.out.println("the returned obj==="+obj.toString());
              String endpoint =obj.get("payload").getAsJsonObject().get("webhook").getAsJsonObject().get("url").getAsString();
              System.out.println("====endpoint====="+endpoint);
            
              // fetch private key from
               String privkey =obj.get("payload").getAsJsonObject().get("test_private_key").getAsString();  
               
              String hashvalue=event.getName()+"|"+event.getAmount()+"|"+event.getMobile()+"|"+event.getReference()+"|"+event.getStatus()+"|"+event.getGeneratedAt()+"|"+privkey;
              event.setHashvalue(DigestUtils.sha512Hex(hashvalue));
              
              //save copy on the system
             String id = dao.add(event);
             Long entityId=Long.parseLong(id);
             // prepare to send to third party      
             post = new HttpPost(endpoint);
             post.setHeader("Content-Type", "application/json");
            // post.setHeader("Authorization", "Bearer "+);
             StringEntity ent = new StringEntity(gson.toJson(event));
             post.setEntity(ent);
             response=client.execute(post);
             // check and log the response from thirdpart 
             int data=response.getStatusLine().getStatusCode();
              System.out.println("data-------"+data);
             // updating the WebhookEvent
             dao.nupdateWebhookEvent(Integer.toString(data), entityId);
             System.out.println("==== response===="+data);

           
         }catch(Exception e){
           obj = new JsonObject();
           obj.addProperty("code", "S23");
           obj.addProperty("message", "operation failed");
           System.out.println("cause...."+e.getMessage());
           
         }
    
    }
    
         public JsonObject getMerchantInfo(String mid){
          try{
             get = new HttpGet(getAppProperties().getProperty("verify_merchant_info")+mid);
             response=client.execute(get);
             String msg = EntityUtils.toString(response.getEntity());
             obj = new JsonParser().parse(msg).getAsJsonObject();
             System.out.println("merchant info response...."+obj);
             JsonObject obj2 = new JsonObject();
               if((obj.get("responseCode").getAsString()).equals("00")){
                     return obj;
               }else{
                   System.out.println("somefin is wrong");
                   obj2.addProperty("code", "S18");
                   obj2.addProperty("message","Authentication Failed");
                   return obj2;
               }  
               
             
             
         }catch(Exception e){
           obj = new JsonObject();
           obj.addProperty("code", "S23");
           obj.addProperty("message", "operation failed");
           System.out.println("cause...."+e.getMessage());
           return obj;
         }
    } 
     
          
        
//    public void sendToSettlement(TransactionLog transaction){
//        try{            
//            
//            String resmsg=null;
//            if(transaction.getUserinfo().getTransactionInfo().getTransactionEvent().getGatewayMessage().equals("Approved by Financial Institution")||transaction.getUserinfo().getTransactionInfo().getTransactionEvent().getGatewayMessage().equals("APPROVED")){
//                resmsg="APPROVED";
//            }
//           
//            
//           String datetime=transaction.getCreationdate().replaceAll(":", "").replaceAll(" ", "");
//           obj = new JsonObject();
//           obj.addProperty("public_key", transaction.getPublickey());
//           obj.addProperty("transactionRef", transaction.getReference());
//           obj.addProperty("linkingref", transaction.getLinkingreference());
//           obj.addProperty("customerName", transaction.getCustomername());
//           obj.addProperty("customerEmail", transaction.getEmail());
//           obj.addProperty("country", transaction.getCountry());
//           obj.addProperty("gatewayResponseMessage", resmsg);
//           obj.addProperty("gatewayResponseCode", transaction.getUserinfo().getTransactionInfo().getTransactionEvent().getGatewayCode());
//           obj.addProperty("amount", transaction.getUserinfo().getTransactionInfo().getAmount());
//           obj.addProperty("customerIP", transaction.getSourceip());
//           obj.addProperty("attempts", "to be determined");
//           obj.addProperty("deviceType", transaction.getDevicetype());
//           obj.addProperty("channel", "card");
//           obj.addProperty("channelType", transaction.getUserinfo().getTransactionInfo().getChannelType());
//           obj.addProperty("number", transaction.getNumber());
//           obj.addProperty("customerPhone", transaction.getMobile());
//           obj.addProperty("currency", transaction.getUserinfo().getCurrency());
//           obj.addProperty("transactionTime", datetime);
//           obj.addProperty("country", transaction.getCountry());
//           obj.addProperty("description", transaction.getDescription());
//           
//          
//           System.out.println("the object sending to settlement");
//           System.out.println(obj);
//           
//           String key="client:client";
//           String apikey=new String(Base64.getEncoder().encode(key.getBytes()));
//           
//           post = new HttpPost(getAppProperties().getProperty("settlement_endpoint"));
//           post.setHeader("Content-Type", "application/json");
//           post.setHeader("Authorization", "Basic "+apikey);
//           post.setHeader("Mode", transaction.getMode());
//           StringEntity ent = new StringEntity(obj.toString());
//           post.setEntity(ent);
//            System.out.println("about to execute===="+obj.toString());
//           response=client.execute(post);
//            System.out.println("done posting");
//           String msg = EntityUtils.toString(response.getEntity());
//            System.out.println("raw msg==="+msg);
//           obj = new JsonParser().parse(msg).getAsJsonObject();
//           System.out.println(" ========== response from settlement ========="+obj);
//           
//           //construct object to be sent for webhook notification
//          es.submit(()->{
//             // handler.sendNotification(transaction.getUserinfo().getTransactionInfo().getReference());
//               System.out.println("notifying client");
//               sendNotification(transaction.getReference());
//               System.out.println("done notifying client");
//           });
//
//           
//           
//           //constructing object to update transaction with settlement info in another thread
//           
//            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy:MM:dd hh:mm:ss");
//            String date_and_time_to_settlement=df.format(LocalDateTime.now());
//           
//         
//             es.submit(()->{
//                 System.out.println("updating transaction notificatiuon from settlement thread");
//                  new Dao().nupdateTransactionWithSettlementInfo(transaction.getReference(), obj.get("responseCode").getAsString(), obj.get("responseMessage").getAsString(), date_and_time_to_settlement);
//                  System.out.println("done updating tran");
//              });
//           
//           // get the response from settlement and log the time sent and settlement response messages, code and the transaction ref
//            
//        }catch(Exception e){
//             System.out.println(e.getMessage());
//        }
//    
//    } 
    
        
    public static void generatecode(){
       DateTimeFormatter df =  DateTimeFormatter.ofPattern("yymmddhhmmss");
       String bankcode="000014";
       int unique=Instant.now().getNano();
       String code=bankcode.concat(df.format(LocalDateTime.now())).concat(Integer.toString(unique));
        System.out.println(code);
        System.out.println(unique);
    }
    
    
           public String fetchStatusFromCore(String linkingref){
         try{ 

          //Call thirdparty authentication endpoint
          obj = new JsonParser().parse(getThirdPartyApi()).getAsJsonObject();
                       System.out.println("starting.......");
             System.out.println("token----"+obj.get("access_token").getAsString());
          //set parameters for thirdparty status check
          post = new HttpPost(getAppProperties().getProperty("thirdparty_status_check"));
          post.setHeader("Authorization", "Bearer "+obj.get("access_token").getAsString());
          post.setHeader("Content-Type", "application/json");
          
          //Consruct Payload to send
          obj2 = new JsonObject();
          obj2.addProperty("publickey", getAppProperties().getProperty("publickey"));
          System.out.println("==="+obj2.toString());
          JsonObject obj3 = new JsonObject();
          obj3.addProperty("linkingreference", linkingref);
          obj2.add("transaction", obj3);
          System.out.println("*****"+obj2.toString());
         JsonObject obj4 = new JsonObject();
         obj4.addProperty("operation", "pg_charge_status");
         obj2.add("source", obj4);
          System.out.println("===="+obj2.toString());
         StringEntity ent = new StringEntity(obj2.toString());
         System.out.println("...request sent to third party status endpoint...."+obj2);
         post.setEntity(ent);
         response=client.execute(post);
         String msg = EntityUtils.toString(response.getEntity());
         obj = new JsonParser().parse(msg).getAsJsonObject();
         System.out.println("response from third party status endpoint.."+obj);
             
              return obj.toString();
          
         }catch(Exception e){
            obj = new JsonObject();
            obj.addProperty("code","S7");
            obj.addProperty("message", "Operation failed");
             System.out.println("===="+e.getMessage());
           // logger.logp(Level.SEVERE, "CardApi.class", "calling checkstatusFromThirdparty", e.getMessage());
            return obj.toString();
         }
         


    }
    
    
    
    
     public Properties getAppProperties(){    
        Properties prop= null;
       try{
        prop  = new Properties();
        prop.load(this.getClass().getResourceAsStream("/test.properties"));
        return prop;
       }catch(Exception e){
           System.out.println("====== caused by ===== "+e.getMessage());
          return prop;
       }   
}
     
     public static void main(String[] args) {
        String key="client:client";
           String apikey=new String(Base64.getEncoder().encode(key.getBytes()));
           String mine ="Basic "+apikey;
           String against="Basic Y2xpZW50OmNsaWVudA==";
            if(mine.equals(against)){
                System.out.println("same");
            }else{
                System.out.println("not");
            }
           
           
    }
     

}
