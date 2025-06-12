package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Employee.class)
public abstract class Employee_ {

	public static volatile SingularAttribute<Employee, String> empId;
	public static volatile ListAttribute<Employee, Salary> salaries;
	public static volatile SingularAttribute<Employee, String> gender;
	public static volatile SingularAttribute<Employee, String> phone;
	public static volatile SingularAttribute<Employee, String> fullName;
	public static volatile SingularAttribute<Employee, Department> department;
	public static volatile SingularAttribute<Employee, User> user;
	public static volatile SingularAttribute<Employee, String> email;

}

