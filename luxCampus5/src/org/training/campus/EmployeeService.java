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
		return (Employee[])employees.stream().filter(e->name.equals(e.getName())).toArray();
	}
	
	public Employee[] sortByName() {
		return (Employee[])employees.stream().sorted(Comparator.comparing(Employee::getName)).toArray();
	}
	
	public Employee[] sortByNameAndSalary() {
		return (Employee[])employees.stream().sorted(Comparator.comparing(Employee::getName).thenComparing(Employee::getSalary)).toArray();
	}
	
	public Employee edit(Employee e) {
		return employees.update(e);
	}
	
	public Employee remove(Long id) {
		return employees.remove(id);
	}
	
}
