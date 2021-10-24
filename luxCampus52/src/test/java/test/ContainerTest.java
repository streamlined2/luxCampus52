package test;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.training.campus.domain.Designer;
import org.training.campus.domain.Developer;
import org.training.campus.domain.Employee;
import org.training.campus.domain.Employee.Gender;
import org.training.campus.domain.Manager;
import org.training.campus.model.Container;

class ContainerTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	void testContainer() {
		Container<Long, Manager> c = new Container<>();
		assertEquals(0, c.getSize());
		assertEquals(Container.INITIAL_CAPACITY, c.getCapacity());
	}

	@Test
	void testContainerIntSuccess() {
		final int testCapacity=1000;
		Container<Long, Manager> c = new Container<>(testCapacity);
		assertEquals(0, c.getSize());
		assertEquals(testCapacity, c.getCapacity());
	}

	@Test
	void testContainerIntFail() {
		final int testCapacity=-10;
		assertThrows(IllegalArgumentException.class, ()->{new Container<>(testCapacity);},"negative capacity isn't accepted");
	}

	@Test
	void testIndexOfE() {
		Container<Long, Employee> c = new Container<>();

		Employee e1 = new Manager("Joe Doe",35,Gender.MALE);
		c.add(e1);
		assertEquals(0, c.indexOf(e1));

		Employee e2 = new Designer("Mary Wild",25,Gender.FEMALE,BigDecimal.valueOf(200D));
		c.add(e2);
		assertEquals(1, c.indexOf(e2));

		Employee e3 = new Developer("John Scott",30,Gender.MALE,BigDecimal.valueOf(50D));
		c.add(e3);
		assertEquals(2, c.indexOf(e3));

		assertEquals(0, c.indexOf(e1));
	}

	@Test
	void testGetById() {
		Container<Long, Employee> c = new Container<>();

		Employee e1 = new Manager("Joe Doe",35,Gender.MALE);
		c.add(e1);
		Optional<Employee> employee = c.getById(e1.getId());
		assertTrue(employee.isPresent());
		assertSame(e1, employee.get());
		assertEquals(e1, employee.get());
		
		Optional<Employee> dummy = c.getById(Long.MAX_VALUE);
		assertFalse(dummy.isPresent());		
	}

	@Test
	void testAddSuccess() {
		Container<Long, Employee> c = new Container<>();

		Employee e1 = new Manager("Joe Doe",35,Gender.MALE);
		c.add(e1);
		assertEquals(1, c.getSize());

		Employee e2 = new Designer("Mary Wild",25,Gender.FEMALE,BigDecimal.valueOf(200D));
		c.add(e2);
		assertEquals(2, c.getSize());

		Employee e3 = new Developer("John Scott",30,Gender.MALE,BigDecimal.valueOf(50D));
		c.add(e3);
		assertEquals(3, c.getSize());

	}

	@Test
	void testAddFail() {
		Container<Long, Employee> c = new Container<>();

		Employee e1 = new Manager("Joe Doe",35,Gender.MALE);
		c.add(e1);
		
		assertThrows(IllegalStateException.class, ()->{ c.add(e1);},"the element already has been added to container");
	}

	@Test
	void testRemoveSuccess() {
		Container<Long, Employee> c = new Container<>();

		Employee e = new Manager("Joe Doe",35,Gender.MALE);
		c.add(e);
		
		Employee anotherEmployee = c.remove(e);
		
		assertSame(e, anotherEmployee);
		assertEquals(e, anotherEmployee);
	}

	@Test
	void testRemoveFail() {
		Container<Long, Employee> c = new Container<>();

		Employee employeeToRemove = new Developer("John Scott",30,Gender.MALE,BigDecimal.valueOf(50D));
		
		assertThrows(NoSuchElementException.class, ()->{ c.remove(employeeToRemove);}, "can't find employee to remove");

		Employee e = new Manager("Joe Doe",35,Gender.MALE);
		c.add(e);
		
		assertThrows(NoSuchElementException.class, ()->{ c.remove(employeeToRemove);}, "can't find employee to remove");
	}

	@Test
	void testUpdateSuccess() {
		Container<Long, Employee> c = new Container<>();

		Employee e = new Manager("Joe Doe",35,Gender.MALE);
		c.add(e);
		
		e.setAge(40);
		e.setGender(Gender.FEMALE);
		e.setName("Jayne Doe");
		c.update(e);
		
		assertSame(e, c.getById(e.getId()).get());
		assertEquals(e, c.getById(e.getId()).get());
	}

	@Test
	void testUpdateFail() {
		Container<Long, Employee> c = new Container<>();

		Employee e = new Manager("Joe Doe",35,Gender.MALE);
		assertThrows(NoSuchElementException.class,()->{ c.update(e);},"no element to update");
	}

	@Test
	void testToString() {
		Container<Long, Employee> c = new Container<>();

		Employee e1 = new Manager("Joe Doe",35,Gender.MALE);
		e1.setSalary(BigDecimal.valueOf(100_000D));
		c.add(e1);

		Employee e2 = new Designer("Mary Wild",25,Gender.FEMALE,BigDecimal.valueOf(200D));
		e2.setSalary(BigDecimal.valueOf(200_000D));
		c.add(e2);

		Employee e3 = new Developer("John Scott",30,Gender.MALE,BigDecimal.valueOf(50D));
		e3.setSalary(BigDecimal.valueOf(300_000D));
		c.add(e3);
		
		assertEquals("[Joe Doe,35,MALE,100000.0]\n[Mary Wild,25,FEMALE,200000.0,0,200.0]\n[John Scott,30,MALE,300000.0,0,50.0]\n", c.toString());
	}

	@Test
	void testIteratorFail() {
		Container<Long, Employee> c = new Container<>();
		Iterator<Employee> i = c.iterator();
		assertThrows(NoSuchElementException.class,()->{ i.next();},"empty coniner doesn't yield any element");
	}

	@Test
	void testStream() {
		Container<Long, Employee> c = new Container<>();

		Employee e1 = new Manager("Joe Doe",35,Gender.MALE);
		c.add(e1);

		Employee e2 = new Designer("Mary Wild",25,Gender.FEMALE,BigDecimal.valueOf(200D));
		c.add(e2);

		Employee e3 = new Developer("John Scott",30,Gender.MALE,BigDecimal.valueOf(50D));
		c.add(e3);

		assertEquals(3, c.stream().count());
		assertArrayEquals(new Employee[] { e1, e2, e3 }, c.stream().toArray());	
	}
	
	@Test
	void testEnsureCapacity() {
		Container<Long, Employee> c = new Container<>(1);

		Employee e1 = new Manager("Joe Doe",35,Gender.MALE);
		c.add(e1);

		assertEquals(1, c.getSize());
		assertEquals(1, c.getCapacity());

		Employee e2 = new Designer("Mary Wild",25,Gender.FEMALE,BigDecimal.valueOf(200D));
		c.add(e2);

		assertEquals(2, c.getSize());
		assertEquals(4, c.getCapacity());

		Employee e3 = new Developer("John Scott",30,Gender.MALE,BigDecimal.valueOf(50D));
		c.add(e3);

		assertEquals(3, c.getSize());
		assertEquals(4, c.getCapacity());

	}

	@Test
	void testShrinkCapacity() {
		Container<Long, Employee> c = new Container<>(1);

		Employee e1 = new Manager("Joe Doe",35,Gender.MALE);
		c.add(e1);

		Employee e2 = new Designer("Mary Wild",25,Gender.FEMALE,BigDecimal.valueOf(200D));
		c.add(e2);

		Employee e3 = new Developer("John Scott",30,Gender.MALE,BigDecimal.valueOf(50D));
		c.add(e3);

		assertEquals(3, c.getSize());
		assertEquals(4, c.getCapacity());
		
		c.remove(e3);
		
		assertEquals(2, c.getSize());
		assertEquals(4, c.getCapacity());
		
		c.remove(e2);
		
		assertEquals(1, c.getSize());
		assertEquals(1, c.getCapacity());
		
		c.remove(e1);
		
		assertEquals(0, c.getSize());
		assertEquals(0, c.getCapacity());
	}

}
