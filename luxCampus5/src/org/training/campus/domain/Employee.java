package org.training.campus.domain;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.StringJoiner;

public abstract class Employee implements Entity<Long> {
	public enum Gender { MALE, FEMALE}

	private static final IdGenerator idGenerator=new IdGenerator();

	private final Long id=idGenerator.nextId();
	private String name;
	private int age;
	private Gender gender;
	private BigDecimal salary;
	
	protected Employee(String name,int age,Gender gender) {
		this.name=name;
		this.age=age;
		this.gender=gender;
	}
	
	@Override
	public String toString() {
		return new StringJoiner(",","[","]").
				add(name).
				add(Integer.toString(age)).
				add(gender.name()).
				add(salary.toString()).
				toString();
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
		if(age<0 || age>150) throw new IllegalArgumentException("age should be within [0..150]");
		this.age = age;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		if(salary.compareTo(BigDecimal.ZERO)<=0) throw new IllegalArgumentException("salary shouldn't be negative value or equal to zero");
		this.salary = salary;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	public abstract BigDecimal getPay();

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Employee e) {
			return id.equals(e.id);
		}
		return false; 
	}
}
