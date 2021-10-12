package org.training.campus;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.StringJoiner;

public class Employee implements Entity<Long> {
	public enum Gender { MALE, FEMALE}

	private static final IdGenerator idGenerator=new IdGenerator();

	private final Long id=idGenerator.nextId();
	private String name;
	private int age;
	private BigDecimal salary;
	private Gender gender;
	private int fixedBugs;
	private BigDecimal defaultBugRate;
	
	public Employee(String name,int age,Gender gender) {
		this.name=name;
		this.age=age;
		this.gender=gender;
	}
	
	@Override
	public String toString() {
		return new StringJoiner(",","[","]").add(name).add(Integer.toString(age)).add(gender.name()).add(salary.toString()).toString();
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

	public int getFixedBugs() {
		return fixedBugs;
	}

	public void setFixedBugs(int fixedBugs) {
		if(fixedBugs<0) throw new IllegalArgumentException("number of fixed bugs should be zero or positive value");
		this.fixedBugs = fixedBugs;
	}

	public BigDecimal getDefaultBugRate() {
		return defaultBugRate;
	}

	public void setDefaultBugRate(BigDecimal defaultBugRate) {
		if(defaultBugRate.compareTo(BigDecimal.ZERO)<=0) throw new IllegalArgumentException("bug rate shouldn't be negative value or equal to zero");
		this.defaultBugRate = defaultBugRate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Employee e) {
			return id==e.id;
		}
		return false; 
	}
}
