package org.training.campus.domain;

import java.math.BigDecimal;
import java.util.StringJoiner;

public class Manager extends Employee {

	public Manager(String name, int age, Gender gender) {
		super(name, age, gender);
	}

	@Override
	public String toString() {
		return new StringJoiner(",","[","]").
				add(getName()).
				add(Integer.toString(getAge())).
				add(getGender().name()).
				add(getSalary().toString()).
				toString();
	}
	
	@Override
	public BigDecimal getPay() {
		return getSalary();
	}

}
