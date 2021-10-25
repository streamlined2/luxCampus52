package org.training.campus.domain;

import java.math.BigDecimal;
import java.util.Objects;

public abstract class Employee implements Entity<Long> {
	public enum Gender {
		MALE, FEMALE
	}

	private static final IdGenerator idGenerator = new IdGenerator();

	public static final int MAX_AGE = 150;

	private final Long id = idGenerator.nextId();
	private String name;
	private int age;
	private Gender gender;
	private BigDecimal salary;

	protected Employee(String name, int age, Gender gender) {
		this.name = name;
		this.age = age;
		this.gender = gender;
	}

	protected Employee(String name, int age, Gender gender, BigDecimal salary) {
		this(name, age, gender);
		this.salary = salary;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		if (age < 0 || age > MAX_AGE)
			throw new IllegalArgumentException(String.format("age should be within [0..%d]", MAX_AGE));
		this.age = age;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		if (salary.compareTo(BigDecimal.ZERO) <= 0)
			throw new IllegalArgumentException("salary couldn't be negative or equal to zero");
		this.salary = salary;
	}

	public abstract BigDecimal getPay();

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Employee e) {
			return id.equals(e.id);
		}
		return false;
	}
}
