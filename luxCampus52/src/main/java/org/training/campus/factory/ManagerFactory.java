package org.training.campus.factory;

import org.training.campus.domain.Manager;
import org.training.campus.domain.Employee.Gender;

public class ManagerFactory extends EmployeeFactory {

	@Override
	public Manager create(String name, int age, Gender gender) {
		final Manager manager = new Manager(name, age, gender);
		manager.setSalary(generateSalary());
		return manager;
	}

}
