package org.training.campus;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;

public class EmployeeService {
	private static final int INITIAL_CAPACITY=100;
	private Container<Long,Employee> employees = new Container<>(INITIAL_CAPACITY);
	
	public void printEmployees() {
		printEmployees(System.out);
	}

	public void printEmployees(PrintStream out) {
		for(Employee e:employees) {
			out.append(e.toString()).append('\n');
		}
	}
	
	public BigDecimal calculateSalaryAndBonus() {
		BigDecimal total=BigDecimal.ZERO;
		for(Employee e:employees) {
			total=total.add(e.getSalary()).add(e.getDefaultBugRate().multiply(BigDecimal.valueOf(e.getFixedBugs())));
		}
		return total;
	}
	
	public Optional<Employee> getById(Long id){
		return employees.getById(id);
	}
	
	public Employee[] getByName(String name) {
		Objects.requireNonNull(name, "please pass valid non-null name");
		return employees.stream().filter(e->name.equals(e.getName())).toArray(Employee[]::new);
	}
	
	public Employee[] sortByName() {
		return employees.stream().sorted(Comparator.comparing(Employee::getName)).toArray(Employee[]::new);
	}
	
	public Employee[] sortByNameAndSalary() {
		return employees.stream().sorted(Comparator.comparing(Employee::getName).thenComparing(Employee::getSalary)).toArray(Employee[]::new);
	}
	
	public void add(Employee e) {
		employees.add(e);
	}
	
	public Employee edit(Employee e) {
		return employees.update(e);
	}
	
	public Employee remove(Long id) {
		return employees.remove(id);
	}
	
}
