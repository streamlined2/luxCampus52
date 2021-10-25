package org.training.campus.domain;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.Random;
import java.util.StringJoiner;

public class Developer extends Employee {
	private static final Random generator = new SecureRandom();

	private int fixedBugs;
	private BigDecimal defaultBugRate;

	public Developer(String name, int age, Gender gender, BigDecimal defaultBugRate) {
		super(name, age, gender);
		this.defaultBugRate = defaultBugRate;
	}

	public Developer(String name, int age, Gender gender, BigDecimal defaultBugRate, BigDecimal salary) {
		super(name, age, gender, salary);
		this.defaultBugRate = defaultBugRate;
	}

	@Override
	public String toString() {
		return new StringJoiner(",", "[", "]").add(getName()).add(Integer.toString(getAge())).add(getGender().name())
				.add(getSalary().toString()).add(Integer.toString(fixedBugs)).add(defaultBugRate.toString()).toString();
	}

	public int getFixedBugs() {
		return fixedBugs;
	}

	public void setFixedBugs(int fixedBugs) {
		if (fixedBugs < 0)
			throw new IllegalArgumentException("number of fixed bugs should be zero or positive value");
		this.fixedBugs = fixedBugs;
	}

	public BigDecimal getDefaultBugRate() {
		return defaultBugRate;
	}

	public void setDefaultBugRate(BigDecimal defaultBugRate) {
		if (defaultBugRate.compareTo(BigDecimal.ZERO) <= 0)
			throw new IllegalArgumentException("bug rate shouldn't be negative value or equal to zero");
		this.defaultBugRate = defaultBugRate;
	}

	@Override
	public BigDecimal getPay() {
		return getSalary().add(defaultBugRate.multiply(BigDecimal.valueOf(fixedBugs)))
				.multiply(generator.nextBoolean() ? BigDecimal.valueOf(2) : BigDecimal.ZERO);
	}

}
