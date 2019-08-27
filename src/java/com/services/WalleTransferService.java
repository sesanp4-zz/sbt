/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services;


import com.entities.WalletTransactionNotification;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.transfer.WalletTransferTransacionRequestProxy;
import com.wallet.WalletTransferHandler;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author centricgateway
 */
@Path("transfer/v1")
public class WalleTransferService {

    @Context
    private UriInfo context;
    
    @Inject
    WalletTransferHandler handler;
    
    Gson gson = new Gson();
    JsonObject obj;

    /**
     * Creates a new instance of TransferService
     */
    public WalleTransferService() {
    }

    /**
     * Retrieves representation of an instance of com.services.TransferService
     * @return an instance of java.lang.String
     */
    
    @Path("initiate/wallet/transfer")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String initiateWalletTransfer(WalletTransferTransacionRequestProxy payload){
     return handler.initiateTransfer(payload);   
    }
    
    
    @Path("wallet/notify")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String notifyWalletTransaction(WalletTransactionNotification payload,@QueryParam("auth")String auth){
        
       if(!auth.equals("3893849838943894893483")){
                obj = new JsonObject();
                obj.addProperty("code", "S3");
                obj.addProperty("message", "operation failed");
                return obj.toString();
        } 
        
     System.out.println("====== notification payload ===="+gson.toJson(payload));
     //return handler.logWalletEvent(payload);
     obj = new JsonObject();
     obj.addProperty("code", "00");
     obj.addProperty("message", "successful");
     return obj.toString();
    }
    
    @Path("wallet/name/enquiry/{reference}")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String NameEnquiry(@PathParam("reference")String reference, @QueryParam("auth")String auth){
        
        System.out.println("====== reference ===="+reference);
     return handler.WalletNameEnquiry(reference,auth);
    }
    
    
    
}
