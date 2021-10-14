package org.training.campus;

import java.math.BigDecimal;
import java.util.Arrays;

import org.training.campus.domain.Designer;
import org.training.campus.domain.Developer;
import org.training.campus.domain.Employee;
import org.training.campus.domain.Manager;
import org.training.campus.factory.EmployeeFactory;
import org.training.campus.model.EmployeeService;

public class Test {

	public static void main(String[] args) {
		EmployeeService service=new EmployeeService();
		
		Developer e1=new Developer("Serhii Pylypenko",49,Employee.Gender.MALE,BigDecimal.valueOf(100D));
		e1.setSalary(BigDecimal.valueOf(20_000D));
		e1.setFixedBugs(7);
		service.add(e1);
		
		System.out.printf("Employee added with id %d: %s%n",e1.getId(),service.getById(e1.getId()));

		Designer e2=new Designer("John Smith",30,Employee.Gender.MALE,BigDecimal.valueOf(500D));
		e2.setSalary(BigDecimal.valueOf(50_000D));
		e2.setWorkedDays(20);
		service.add(e2);

		System.out.printf("Employee added with id %d: %s%n",e2.getId(),service.getById(e2.getId()));

		Manager e3=new Manager("Rebecca Blacksmith",30,Employee.Gender.FEMALE);
		e3.setSalary(BigDecimal.valueOf(75_000D));
		service.add(e3);
		
		System.out.printf("Employee added with id %d: %s%n",e3.getId(),service.getById(e3.getId()));

		System.out.printf("Can't find any employee with id %d: %s%n",Long.MAX_VALUE,service.getById(Long.MAX_VALUE));

		System.out.println("\nCurrent list of employees:");
		service.printEmployees();
		System.out.printf("Salary and bonus total: %s%n",service.calculateSalaryAndBonus().toString());
		
		System.out.printf("\nEntity search by name: %s%n",Arrays.toString(service.getByName("Rebecca Blacksmith")));
		System.out.printf("\nEntity search by name: %s%n",Arrays.toString(service.getByName("John Smith")));
		
		System.out.printf("\nSorted by name: %s%n",Arrays.toString(service.sortByName()));

		Developer e4=new Developer("Rebecca Blacksmith",45,Employee.Gender.FEMALE,BigDecimal.valueOf(200D));
		e4.setSalary(BigDecimal.valueOf(35_000D));
		e4.setDefaultBugRate(BigDecimal.valueOf(100.00D));
		e4.setFixedBugs(20);
		service.add(e4);
		
		System.out.printf("\nEntity search by name: %s%n",Arrays.toString(service.getByName("Rebecca Blacksmith")));

		System.out.printf("\nSorted by name and salary: %s%n",Arrays.toString(service.sortByNameAndSalary()));
		
		System.out.println("\nRemoving employees step by step:");
		System.out.println(">");
		e4.setName("Zooey Boggart");
		service.edit(e4);
		service.printEmployees(System.out);
		System.out.println(">");
		
		service.remove(e1.getId());
		service.printEmployees();
		System.out.println(">");

		service.remove(e2.getId());
		service.printEmployees();
		System.out.println(">");

		service.remove(e3.getId());
		service.printEmployees();
		System.out.println(">");

		service.remove(e4.getId());
		service.printEmployees();
		System.out.println(">");
		
		EmployeeService randomizedService = new EmployeeService(EmployeeFactory.generateEmployees(100));
		System.out.println("Randomly generated list of employees:");
		randomizedService.printEmployees();
		System.out.println();
		
		System.out.printf("Salary and bonus total: %s%n%n",randomizedService.calculateSalaryAndBonus().toString());

		System.out.printf("Sorted by name: %s%n%n",Arrays.toString(randomizedService.sortByName()));
		System.out.printf("Sorted by name and salary: %s%n%n",Arrays.toString(randomizedService.sortByNameAndSalary()));
		
	}

}
