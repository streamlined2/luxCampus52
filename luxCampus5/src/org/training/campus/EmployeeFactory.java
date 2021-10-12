package org.training.campus;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.Random;

import org.training.campus.Employee.Gender;

public class EmployeeFactory {
	
	private static final Random generator=new SecureRandom();
	
	public Container<Long,Employee> generateEmployees(int size){
		if(size<=0) throw new IllegalArgumentException("please pass positive number of employees to generate");
		Container<Long,Employee> employees = new Container<>(size);
		for(int k=0;k<size;k++) {
			Employee e=new Employee(generateName(),generateAge(),generateGender());
			e.setSalary(generateSalary());
			e.setDefaultBugRate(generateBugRate());
			e.setFixedBugs(generateFixedBugs());
			employees.add(e);
		}
		return employees;
	}

	private static int generateFixedBugs() {
		return generator.nextInt(100);
	}

	private static BigDecimal generateBugRate() {
		return BigDecimal.valueOf(generator.nextDouble()*100);
	}

	private static BigDecimal generateSalary() {
		return BigDecimal.valueOf(generator.nextDouble()*100_000);
	}

	private static Gender generateGender() {
		return generator.nextBoolean()?Employee.Gender.FEMALE:Employee.Gender.MALE;
	}

	private static int generateAge() {
		return generator.nextInt(100);
	}

	private static String generateName() {
		StringBuilder b=new StringBuilder();
		int minLength=generator.nextInt(20);
		int maxLength=generator.nextInt(50);
		for(int k=minLength;k<maxLength;k++) {
			b.append('A'+generator.nextInt('Z'-'A'+1));
		}
		return b.toString();
	}

}
