package com.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(WalletTransactionNotification.class)
public abstract class WalletTransactionNotification_ {

	public static volatile SingularAttribute<WalletTransactionNotification, String> originatoraccountnumber;
	public static volatile SingularAttribute<WalletTransactionNotification, String> amount;
	public static volatile SingularAttribute<WalletTransactionNotification, String> originatorname;
	public static volatile SingularAttribute<WalletTransactionNotification, String> narration;
	public static volatile SingularAttribute<WalletTransactionNotification, String> craccountname;
	public static volatile SingularAttribute<WalletTransactionNotification, String> paymentreference;
	public static volatile SingularAttribute<WalletTransactionNotification, Long> id;
	public static volatile SingularAttribute<WalletTransactionNotification, String> sessionid;
	public static volatile SingularAttribute<WalletTransactionNotification, String> bankname;
	public static volatile SingularAttribute<WalletTransactionNotification, String> craccount;
	public static volatile SingularAttribute<WalletTransactionNotification, String> bankcode;

}

