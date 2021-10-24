package test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.training.campus.domain.Designer;
import org.training.campus.domain.Developer;
import org.training.campus.domain.Employee;
import org.training.campus.domain.Manager;
import org.training.campus.model.Container;
import org.training.campus.domain.Employee.Gender;
import org.training.campus.service.EmployeeService;

class EmployeeServiceTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	void testPrintEmployeesPrintStream() {
		EmployeeService service = new EmployeeService();
		service.add(new Manager("Joe Doe",35,Gender.MALE,BigDecimal.valueOf(50_000D)));
		service.add(new Designer("Mary Wild",25,Gender.FEMALE,BigDecimal.valueOf(200D),BigDecimal.valueOf(100_000D)));
		service.add(new Developer("John Scott",30,Gender.MALE,BigDecimal.valueOf(50D),BigDecimal.valueOf(200_000D)));

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		service.printEmployees(new PrintStream(baos));
		assertEquals("[Joe Doe,35,MALE,50000.0]\n[Mary Wild,25,FEMALE,100000.0,0,200.0]\n[John Scott,30,MALE,200000.0,0,50.0]\n", baos.toString());
	}

	@Test
	void testPrintEmployees() {
		PrintStream out = System.out;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		System.setOut(new PrintStream(baos));
		
		EmployeeService service = new EmployeeService(new Container<>(3));
		service.add(new Manager("Joe Doe",35,Gender.MALE,BigDecimal.valueOf(50_000D)));
		service.add(new Designer("Mary Wild",25,Gender.FEMALE,BigDecimal.valueOf(200D),BigDecimal.valueOf(100_000D)));
		service.add(new Developer("John Scott",30,Gender.MALE,BigDecimal.valueOf(50D),BigDecimal.valueOf(200_000D)));
		service.printEmployees();
		assertEquals("[Joe Doe,35,MALE,50000.0]\n[Mary Wild,25,FEMALE,100000.0,0,200.0]\n[John Scott,30,MALE,200000.0,0,50.0]\n", baos.toString());

		System.setOut(out);
	}

	@Test
	void testCalculateSalaryAndBonus() {
		EmployeeService service = new EmployeeService();
		service.add(new Manager("Joe Doe",35,Gender.MALE,BigDecimal.valueOf(50_000D)));
		service.add(new Designer("Mary Wild",25,Gender.FEMALE,BigDecimal.valueOf(200D),BigDecimal.valueOf(100_000D)));
		service.add(new Developer("John Scott",30,Gender.MALE,BigDecimal.valueOf(50D),BigDecimal.valueOf(200_000D)));
		BigDecimal sum = service.calculateSalaryAndBonus();
		assertTrue(sum.equals(BigDecimal.valueOf(150000D)) || sum.equals(BigDecimal.valueOf(550000D)));
	}

	@Test
	void testGetById() {
		EmployeeService service = new EmployeeService();

		Employee e1 = new Manager("Joe Doe",35,Gender.MALE);
		service.add(e1);
		Optional<Employee> employee = service.getById(e1.getId());
		assertTrue(employee.isPresent());
		assertSame(e1, employee.get());
		assertEquals(e1, employee.get());
		
		Optional<Employee> dummy = service.getById(Long.MAX_VALUE);
		assertFalse(dummy.isPresent());
	}

	@Test
	void testGetByNameSuccess() {
		EmployeeService service = new EmployeeService();
		Employee e1 = new Manager("Joe Doe",35,Gender.MALE,BigDecimal.valueOf(120_000D));
		service.add(e1);
		Employee e2 = new Manager("Joe Duck",45,Gender.MALE,BigDecimal.valueOf(75_000D));
		service.add(e2);
		Employee[] employees = service.getByName("Joe Duck");
		assertArrayEquals(new Employee[] {e2}, employees);
	}

	@Test
	void testGetByNameFail() {
		EmployeeService service = new EmployeeService();
		Employee e1 = new Manager("Joe Doe",35,Gender.MALE);
		service.add(e1);
		Employee[] employees = service.getByName("Jack Doe");
		assertArrayEquals(new Employee[] {}, employees);
	}

	@Test
	void testGetByNameNullFail() {
		EmployeeService service = new EmployeeService();
		assertThrows(NullPointerException.class, ()->{ service.getByName(null);}, "null name passed");
	}

	@Test
	void testSortByName() {
		EmployeeService service = new EmployeeService();
		Employee e2 = new Designer("Mary Wild",25,Gender.FEMALE,BigDecimal.valueOf(200D),BigDecimal.valueOf(100_000D));
		service.add(e2);
		Employee e3 = new Developer("John Scott",30,Gender.MALE,BigDecimal.valueOf(50D),BigDecimal.valueOf(200_000D));
		service.add(e3);
		Employee e1 = new Manager("Joe Doe",35,Gender.MALE,BigDecimal.valueOf(50_000D));
		service.add(e1);
		Employee[] employees = service.sortByName();
		assertArrayEquals(new Employee[] {e1,e3,e2}, employees);
	}

	@Test
	void testSortByNameAndSalary() {
		EmployeeService service = new EmployeeService();
		Employee e2 = new Designer("Mary Wild",25,Gender.FEMALE,BigDecimal.valueOf(200D),BigDecimal.valueOf(100_000D));
		service.add(e2);
		Employee e21 = new Designer("Mary Wild",25,Gender.FEMALE,BigDecimal.valueOf(200D),BigDecimal.valueOf(50_000D));
		service.add(e21);
		Employee e3 = new Developer("John Scott",30,Gender.MALE,BigDecimal.valueOf(50D),BigDecimal.valueOf(200_000D));
		service.add(e3);
		Employee e31 = new Developer("John Scott",30,Gender.MALE,BigDecimal.valueOf(50D),BigDecimal.valueOf(50_000D));
		service.add(e31);
		Employee e1 = new Manager("Joe Doe",35,Gender.MALE,BigDecimal.valueOf(50_000D));
		service.add(e1);
		Employee e11 = new Manager("Joe Doe",35,Gender.MALE,BigDecimal.valueOf(20_000D));
		service.add(e11);
		Employee[] employees = service.sortByNameAndSalary();
		assertArrayEquals(new Employee[] {e11,e1,e31,e3,e21,e2}, employees);
	}

	@Test
	void testEdit() {
		EmployeeService service = new EmployeeService();
		Designer e = new Designer("Mary Wild",25,Gender.FEMALE,BigDecimal.valueOf(200D),BigDecimal.valueOf(100_000D));
		service.add(e);
		e.setSalary(BigDecimal.valueOf(200_000D));
		Designer changedEmployee = (Designer)service.edit(e);
		assertEquals("Mary Wild", changedEmployee.getName());
		assertEquals(25, changedEmployee.getAge());
		assertEquals(Gender.FEMALE, changedEmployee.getGender());
		assertEquals(BigDecimal.valueOf(200D), changedEmployee.getRate());
		assertEquals(BigDecimal.valueOf(200_000D), changedEmployee.getSalary());
	}

	@Test
	void testRemove() {
		EmployeeService service = new EmployeeService();
		Designer e = new Designer("Mary Wild",25,Gender.FEMALE,BigDecimal.valueOf(200D),BigDecimal.valueOf(100_000D));
		service.add(e);
		assertTrue(service.getById(e.getId()).isPresent());
		Designer removedEmployee = (Designer)service.remove(e.getId());
		assertFalse(service.getById(e.getId()).isPresent());
		assertSame(e, removedEmployee);
	}

	@Test
	void testAdd() {
		EmployeeService service = new EmployeeService();
		Designer e = new Designer("Mary Wild",25,Gender.FEMALE,BigDecimal.valueOf(200D),BigDecimal.valueOf(100_000D));
		service.add(e);
		assertTrue(service.getById(e.getId()).isPresent());
		assertSame(e, service.getById(e.getId()).get());
	}
}
