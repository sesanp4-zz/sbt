package com.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UserInfo.class)
public abstract class UserInfo_ {

	public static volatile SingularAttribute<UserInfo, String> country;
	public static volatile SingularAttribute<UserInfo, String> mobile;
	public static volatile SingularAttribute<UserInfo, String> currency;
	public static volatile SingularAttribute<UserInfo, Long> id;
	public static volatile SingularAttribute<UserInfo, String> fullname;
	public static volatile SingularAttribute<UserInfo, String> email;
	public static volatile SingularAttribute<UserInfo, TransactionInfo> transactionInfo;

}

