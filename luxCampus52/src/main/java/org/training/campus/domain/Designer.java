package org.training.campus.domain;

import java.math.BigDecimal;
import java.util.StringJoiner;

public class Designer extends Employee {
	private BigDecimal rate;
	private int workedDays;

	public Designer(String name, int age, Gender gender, BigDecimal rate) {
		super(name, age, gender);
		this.rate = rate;
	}

	public Designer(String name, int age, Gender gender, BigDecimal rate, BigDecimal salary) {
		super(name, age, gender, salary);
		this.rate = rate;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		if (rate.compareTo(BigDecimal.ZERO) <= 0)
			throw new IllegalArgumentException("designer's rate should be positive value");
		this.rate = rate;
	}

	public int getWorkedDays() {
		return workedDays;
	}

	public void setWorkedDays(int workedDays) {
		if (workedDays < 0)
			throw new IllegalArgumentException("number of worked days shouldn't be negative");
		this.workedDays = workedDays;
	}

	@Override
	public String toString() {
		return new StringJoiner(",", "[", "]").add(getName()).add(Integer.toString(getAge())).add(getGender().name())
				.add(getSalary().toString()).add(Integer.toString(workedDays)).add(rate.toString()).toString();
	}

	@Override
	public BigDecimal getPay() {
		return getSalary().add(rate.multiply(BigDecimal.valueOf(workedDays)));
	}

}
