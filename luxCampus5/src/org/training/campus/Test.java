package org.training.campus;

import java.math.BigDecimal;

public class Test {

	public static void main(String[] args) {
		Container<Long,Employee> data=new Container<>(5);
		Employee e1=new Employee("Serhii Pylypenko",49,Employee.Gender.MALE);
		e1.setDefaultBugRate(BigDecimal.valueOf(100.00D));
		e1.setFixedBugs(7);
		e1.setSalary(BigDecimal.valueOf(20_000D));
		System.out.println(e1.getId());
		data.add(e1);

		Employee e2=new Employee("John Smith",30,Employee.Gender.MALE);
		e2.setDefaultBugRate(BigDecimal.valueOf(200.00D));
		e2.setFixedBugs(25);
		e2.setSalary(BigDecimal.valueOf(50_000D));
		System.out.println(e2.getId());
		data.add(e2);

		Employee e3=new Employee("Rebecca Blacksmith",30,Employee.Gender.FEMALE);
		e3.setDefaultBugRate(BigDecimal.valueOf(300.00D));
		e3.setFixedBugs(50);
		e3.setSalary(BigDecimal.valueOf(75_000D));
		System.out.println(e3.getId());
		data.add(e3);

		System.out.println(data);
	}

}
