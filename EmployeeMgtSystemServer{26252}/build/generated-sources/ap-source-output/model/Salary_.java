package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Salary.class)
public abstract class Salary_ {

	public static volatile SingularAttribute<Salary, String> payMonth;
	public static volatile SingularAttribute<Salary, Double> basicSalary;
	public static volatile SingularAttribute<Salary, Double> bonus;
	public static volatile SingularAttribute<Salary, Double> deductions;
	public static volatile SingularAttribute<Salary, Employee> employee;
	public static volatile SingularAttribute<Salary, String> salaryCode;

}

