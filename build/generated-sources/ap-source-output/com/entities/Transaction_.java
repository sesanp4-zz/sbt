package com.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Transaction.class)
public abstract class Transaction_ {

	public static volatile SingularAttribute<Transaction, String> time_to_settlement;
	public static volatile SingularAttribute<Transaction, String> ref;
	public static volatile SingularAttribute<Transaction, Long> id;
	public static volatile SingularAttribute<Transaction, String> settlementCode;
	public static volatile SingularAttribute<Transaction, UserInfo> userinfo;
	public static volatile SingularAttribute<Transaction, String> settlementMessage;
	public static volatile SingularAttribute<Transaction, String> status;

}

