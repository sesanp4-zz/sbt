package com.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TransactionEvent.class)
public abstract class TransactionEvent_ {

	public static volatile SingularAttribute<TransactionEvent, String> gatewayCode;
	public static volatile SingularAttribute<TransactionEvent, String> gatewayref;
	public static volatile SingularAttribute<TransactionEvent, String> transactionRef;
	public static volatile SingularAttribute<TransactionEvent, Long> id;
	public static volatile SingularAttribute<TransactionEvent, String> gatewayMessage;
	public static volatile SingularAttribute<TransactionEvent, String> gateway;

}

