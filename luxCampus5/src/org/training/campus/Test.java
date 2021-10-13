package org.training.campus;

import java.math.BigDecimal;
import java.util.Arrays;

public class Test {

	public static void main(String[] args) {
		EmployeeService service=new EmployeeService();
		Employee e1=new Employee("Serhii Pylypenko",49,Employee.Gender.MALE);
		e1.setDefaultBugRate(BigDecimal.valueOf(100.00D));
		e1.setFixedBugs(7);
		e1.setSalary(BigDecimal.valueOf(20_000D));
		service.add(e1);

		Employee e2=new Employee("John Smith",30,Employee.Gender.MALE);
		e2.setDefaultBugRate(BigDecimal.valueOf(200.00D));
		e2.setFixedBugs(25);
		e2.setSalary(BigDecimal.valueOf(50_000D));
		service.add(e2);

		Employee e3=new Employee("Rebecca Blacksmith",30,Employee.Gender.FEMALE);
		e3.setDefaultBugRate(BigDecimal.valueOf(300.00D));
		e3.setFixedBugs(50);
		e3.setSalary(BigDecimal.valueOf(75_000D));
		service.add(e3);
		
		service.printEmployees();
		System.out.printf("Salary and bonus total: %s%n",service.calculateSalaryAndBonus().toString());
		
		System.out.printf("Entity search by name: %s%n",Arrays.toString(service.getByName("Rebecca Blacksmith")));
		System.out.printf("Entity search by name: %s%n",Arrays.toString(service.getByName("John Smith")));
		
		System.out.printf("Sorted by name: %s%n",Arrays.toString(service.sortByName()));

		Employee e4=new Employee("Rebecca Blacksmith",45,Employee.Gender.FEMALE);
		e4.setDefaultBugRate(BigDecimal.valueOf(100.00D));
		e4.setFixedBugs(20);
		e4.setSalary(BigDecimal.valueOf(35_000D));
		service.add(e4);
		
		System.out.printf("Sorted by name and salary: %s%n",Arrays.toString(service.sortByNameAndSalary()));

	}

}
