package com.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TransferLog.class)
public abstract class TransferLog_ {

	public static volatile SingularAttribute<TransferLog, String> expiryDate;
	public static volatile SingularAttribute<TransferLog, String> reference;
	public static volatile SingularAttribute<TransferLog, String> dateCreated;
	public static volatile SingularAttribute<TransferLog, String> businessName;
	public static volatile SingularAttribute<TransferLog, Long> id;
	public static volatile SingularAttribute<TransferLog, String> accountNumber;

}

