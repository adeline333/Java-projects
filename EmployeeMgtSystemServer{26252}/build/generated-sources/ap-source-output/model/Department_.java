package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Department.class)
public abstract class Department_ {

	public static volatile SingularAttribute<Department, String> deptName;
	public static volatile SingularAttribute<Department, Integer> establishedYear;
	public static volatile SingularAttribute<Department, Integer> totalEmployees;
	public static volatile ListAttribute<Department, Employee> employees;
	public static volatile SingularAttribute<Department, String> deptCode;
	public static volatile SingularAttribute<Department, String> headOfDept;

}

