package org.training.campus.factory;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.Random;

import org.training.campus.domain.Employee;
import org.training.campus.domain.Employee.Gender;
import org.training.campus.model.Container;

public abstract class EmployeeFactory {
	
	protected static final Random generator=new SecureRandom();
	
	public abstract Employee create(String name,int age,Gender gender);
	
	private static final EmployeeFactory[] factories=new EmployeeFactory[] {
			new DesignerFactory(), new ManagerFactory(), new DeveloperFactory()
	};
	
	public Container<Long,Employee> generateEmployees(int size){
		if(size<=0) throw new IllegalArgumentException("please pass positive number of employees to generate");
		Container<Long,Employee> employees = new Container<>(size);
		for(int k=0;k<size;k++) {
			EmployeeFactory factory=factories[k%factories.length];
			employees.add(factory.create(generateName(),generateAge(),generateGender()));
		}
		return employees;
	}

	protected static BigDecimal generateSalary() {
		return BigDecimal.valueOf(generator.nextDouble()*100_000);
	}

	protected static Gender generateGender() {
		return generator.nextBoolean()?Employee.Gender.FEMALE:Employee.Gender.MALE;
	}

	protected static int generateAge() {
		return generator.nextInt(100);
	}

	private static final int MIN_NAME_LENGTH=20;
	private static final int MAX_NAME_LENGTH=50;
	
	protected static String generateName() {
		StringBuilder b=new StringBuilder();
		int nameLength=generator.nextInt(MAX_NAME_LENGTH-MIN_NAME_LENGTH)+MIN_NAME_LENGTH;
		for(int k=0;k<nameLength;k++) {
			b.append('A'+generator.nextInt('Z'-'A'+1));
		}
		return b.toString();
	}

}
