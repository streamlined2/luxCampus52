package org.training.campus.factory;

import java.math.BigDecimal;

import org.training.campus.domain.Developer;
import org.training.campus.domain.Employee.Gender;

public class DeveloperFactory extends EmployeeFactory {

	@Override
	public Developer create(String name, int age, Gender gender) {
		final Developer developer = new Developer(name, age, gender, generateBugRate());
		developer.setSalary(generateSalary());
		developer.setFixedBugs(generateFixedBugs());
		return developer;
	}

	protected static int generateFixedBugs() {
		return generator.nextInt(100);
	}

	protected static BigDecimal generateBugRate() {
		return BigDecimal.valueOf(generator.nextDouble() * 100D);
	}

}
