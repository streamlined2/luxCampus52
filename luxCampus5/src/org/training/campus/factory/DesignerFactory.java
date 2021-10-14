package org.training.campus.factory;

import java.math.BigDecimal;

import org.training.campus.domain.Designer;
import org.training.campus.domain.Employee.Gender;

public class DesignerFactory extends EmployeeFactory {

	@Override
	public Designer create(String name, int age, Gender gender) {
		Designer designer=new Designer(name,age,gender,generateRate());
		designer.setSalary(generateSalary());
		designer.setWorkedDays(generateWorkedDays());
		return designer;
	}

	protected static int generateWorkedDays() {
		return generator.nextInt(23);
	}

	protected static BigDecimal generateRate() {
		return BigDecimal.valueOf(generator.nextDouble()*200D);
	}

}
