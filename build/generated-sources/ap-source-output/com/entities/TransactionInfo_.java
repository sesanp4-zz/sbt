package com.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TransactionInfo.class)
public abstract class TransactionInfo_ {

	public static volatile SingularAttribute<TransactionInfo, String> public_key;
	public static volatile SingularAttribute<TransactionInfo, String> deviceType;
	public static volatile SingularAttribute<TransactionInfo, String> amount;
	public static volatile SingularAttribute<TransactionInfo, String> redirecturl;
	public static volatile SingularAttribute<TransactionInfo, String> fee;
	public static volatile SingularAttribute<TransactionInfo, String> businessName;
	public static volatile SingularAttribute<TransactionInfo, String> channel;
	public static volatile SingularAttribute<TransactionInfo, String> description;
	public static volatile SingularAttribute<TransactionInfo, String> channelType;
	public static volatile SingularAttribute<TransactionInfo, String> reference;
	public static volatile SingularAttribute<TransactionInfo, String> mode;
	public static volatile SingularAttribute<TransactionInfo, String> clientAppCode;
	public static volatile SingularAttribute<TransactionInfo, String> number;
	public static volatile SingularAttribute<TransactionInfo, String> datetime;
	public static volatile SingularAttribute<TransactionInfo, String> sourceIP;
	public static volatile SingularAttribute<TransactionInfo, TransactionEvent> transactionEvent;
	public static volatile SingularAttribute<TransactionInfo, Long> id;
	public static volatile SingularAttribute<TransactionInfo, String> callbackurl;

}

