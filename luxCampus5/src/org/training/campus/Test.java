package org.training.campus;

import java.math.BigDecimal;
import java.util.Optional;

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
		
		System.out.println(data.indexOf(e1));
		System.out.println(data.indexOf(e2));
		System.out.println(data.indexOf(e3));
		
		Optional<Employee> a=data.getById(Long.MIN_VALUE+1);
		a.ifPresent(x->System.out.println(x));
		System.out.println();

		System.out.println(data);
		System.out.println();
		
		data.remove(e1);
		System.out.println(data);
		System.out.println();
		
		data.remove(e2);
		System.out.println(data);
		System.out.println();
		
		e3.setAge(31);
		e3.setDefaultBugRate(BigDecimal.valueOf(450D));
		e3.setFixedBugs(100);
		e3.setSalary(BigDecimal.valueOf(100_000D));
		data.update(e3);
		System.out.println(data);
		System.out.println();
		
		Optional<Employee> e=data.getById(Long.MIN_VALUE+2);
		if(e.isPresent()) {	
			e.get().setAge(32);
			e.get().setDefaultBugRate(BigDecimal.valueOf(600D));
			e.get().setFixedBugs(200);
			e.get().setSalary(BigDecimal.valueOf(200_000D));			
			data.update(e.get());
			
			System.out.println(data);
		}else {
			System.out.println("not found");
		}
		
	}

}
