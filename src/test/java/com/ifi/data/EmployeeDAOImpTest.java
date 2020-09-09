package com.ifi.data;

import com.ifi.entity.Employee;
import com.ifi.entity.Engineer;
import com.ifi.entity.Worker;
import com.ifi.constants.Gender;
import com.ifi.data.exception.EmployeeDataException;
import com.ifi.data.exception.EmployeeSaveException;
import org.jboss.weld.junit5.auto.AddBeanClasses;
import org.jboss.weld.junit5.auto.EnableAutoWeld;
import org.junit.jupiter.api.*;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;
import java.sql.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.ifi.util.listener.AppListener.JOIN_DATE;
import static org.junit.jupiter.api.Assertions.*;

@EnableAutoWeld
@AddBeanClasses(EmployeeDAOImp.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EmployeeDAOImpTest {
    private static List<Employee> testEmployeeList;

    @ApplicationScoped
    @Produces
    EntityManager produceEntityManager() {
        return Persistence.createEntityManagerFactory("test").createEntityManager();
    }

    @Inject
    EmployeeDAO employeeDAO;

    @BeforeAll
    static void mockData() {

        Stream<Employee> stream = Stream.of(
                new Engineer("Duc",
                        Gender.MALE,
                        java.sql.Date.valueOf(LocalDate.of(1992, 9, 17)),
                        java.sql.Date.valueOf(JOIN_DATE),
                        BigDecimal.valueOf(0.08),
                        BigDecimal.valueOf(0.003)),
                new Employee("Dat",
                        Gender.OTHER,
                        java.sql.Date.valueOf(LocalDate.of(1997, 7, 15)),
                        java.sql.Date.valueOf(JOIN_DATE)),
                new Employee("Phuong",
                        Gender.FEMALE,
                        java.sql.Date.valueOf(LocalDate.of(1998, 4, 20)),
                        java.sql.Date.valueOf(JOIN_DATE)),
                new Worker("Duy", Gender.MALE,
                        java.sql.Date.valueOf(LocalDate.of(1998, 9, 17)),
                        java.sql.Date.valueOf(JOIN_DATE),
                        BigDecimal.valueOf(0.003)),
                new Employee("Chinh",
                        Gender.MALE,
                        java.sql.Date.valueOf(LocalDate.of(1999, 9, 17)),
                        java.sql.Date.valueOf(JOIN_DATE))
        );
        testEmployeeList = stream.collect(Collectors.toList());

    }

    @BeforeEach
    void refreshDB() {
        testEmployeeList.forEach(employee -> {
            try {
                employeeDAO.addNewEntity(employee);
            } catch (EmployeeSaveException e) {
                e.printStackTrace();
            }
        });
        System.out.println("Database Refreshed!");
    }

    @Test
    @DisplayName("Test CDI")
    void showFrameworkStatus() {
        assertNotNull(employeeDAO);
        System.out.println("Test initiated! CDI successful!");
    }

    @Test
    @DisplayName("Test Find all Employee")
    void should_return_same_dataSize() {
        int expectedDataSize = testEmployeeList.size();
        List<Employee> employees = employeeDAO.findAllEntity();
        assertEquals(expectedDataSize, employees.size());
    }

    @Test
    @DisplayName("Test Find Employee By Id")
    void should_return_one_employee() {
        Employee expected = testEmployeeList.get(0);
        Employee employee = employeeDAO.findEntityByID(expected.getId());
        assertNotNull(employee);
        assertEquals(expected, employee);
    }

    @Test
    @DisplayName("Test Find Worker By Id")
    void should_return_one_worker() {
        Worker expected = (Worker) testEmployeeList.get(3);
        Worker worker = (Worker) employeeDAO.findEntityByID(expected.getId());
        assertNotNull(worker);
        assertEquals(expected, worker);
    }

    @Test
    @DisplayName("Test Find Engineer By Id")
    void should_return_one_engineer() {
        Engineer expected = (Engineer) testEmployeeList.get(0);
        Engineer engineer = (Engineer) employeeDAO.findEntityByID(expected.getId());
        assertNotNull(engineer);
        assertEquals(expected, engineer);
    }

    @Test
    @DisplayName("Test Not Find Worker By Id")
    void should_not_return_worker() {
        Engineer expected = (Engineer) testEmployeeList.get(0);
        assertThrows(ClassCastException.class, () -> {
            Worker worker = (Worker) employeeDAO.findEntityByID(expected.getId());
            assertNull(worker);
        });
    }

    @Test
    @DisplayName("Test Find Entity Base on Class")
    void should_return_entity_by_Type() {
        Employee expected = testEmployeeList.get(0);
        Engineer engineer = employeeDAO.findEntityByID(expected.getId(), Engineer.class);
        assertNotNull(engineer);
        assertEquals(expected, engineer);
    }

    @Test
    @DisplayName("Test Not Found Entity By Wrong class")
    void should_return_null_by_wrong_Type() {
        Employee expected = testEmployeeList.get(1);
        Worker worker = employeeDAO.findEntityByID(expected.getId(), Worker.class);
        assertNull(worker);
    }

    @Test
    @DisplayName("Test not Found Entity")
    void should_return_null() {
        Employee employee = employeeDAO.findEntityByID(UUID.randomUUID());
        assertNull(employee);
    }

    @Test
    @DisplayName("Test add new Employee")
    void should_add_new_employee() throws EmployeeSaveException {
        int expectedSize = testEmployeeList.size();
        Employee employee = new Employee("dummy",
                Gender.MALE,
                Date.valueOf(LocalDate.of(1992, 9, 17)),
                Date.valueOf(LocalDate.now()));
        Employee employeeSaved = employeeDAO.addNewEntity(employee);
        assertNotNull(employeeSaved);
        assertEquals(employee, employeeSaved);
        assertNotEquals(expectedSize, employeeDAO.getCurrentSize());
    }

    @Test
    @DisplayName("Test add new Engineer")
    void should_add_new_engineer() throws EmployeeSaveException {
        int expectedSize = testEmployeeList.size();
        Engineer engineer = new Engineer("dummy",
                Gender.MALE,
                Date.valueOf(LocalDate.of(1992, 9, 17)),
                Date.valueOf(LocalDate.now()),
                BigDecimal.valueOf(0.01),
                BigDecimal.valueOf(0.001));
        Engineer employeeSaved = employeeDAO.addNewEntity(engineer);
        assertNotNull(employeeSaved);
        assertEquals(engineer, employeeSaved);
        assertNotEquals(expectedSize, employeeDAO.getCurrentSize());
    }

    @Test
    @DisplayName("Test add new Worker")
    void should_add_new_worker() throws EmployeeSaveException {
        int expectedSize = testEmployeeList.size();
        Worker worker = new Worker("dummy",
                Gender.MALE,
                Date.valueOf(LocalDate.of(1992, 9, 17)),
                Date.valueOf(LocalDate.now()),
                BigDecimal.valueOf(0.001));
        Worker employeeSaved = employeeDAO.addNewEntity(worker);
        assertNotNull(employeeSaved);
        assertEquals(worker, employeeSaved);
        assertNotEquals(expectedSize, employeeDAO.getCurrentSize());
    }

    @Test
    @DisplayName("Test add Employee with Not null Id")
    void should_not_add_employee() {
        Employee employee = new Employee("dummy",
                Gender.MALE,
                Date.valueOf(LocalDate.of(1992, 9, 17)),
                Date.valueOf(LocalDate.now()));
        employee.setId(UUID.randomUUID());
        assertThrows(EmployeeSaveException.class, () -> {
            Employee employeeSaved = employeeDAO.addNewEntity(employee);
        });
    }

    @Test
    @DisplayName("Test update Employee")
    void should_update_employee() throws EmployeeSaveException, EmployeeDataException {
        String expectedName = "Nam";
        Employee toUpdate = testEmployeeList.get(0);
        toUpdate.setName(expectedName);
        Employee updated = employeeDAO.updateEntity(toUpdate);
        assertNotNull(updated);
        assertEquals(expectedName, updated.getName());
    }

    @Test
    @DisplayName("Test update Engineer")
    void should_update_engineer() throws EmployeeSaveException, EmployeeDataException {
        Gender expectedGender = Gender.FEMALE;
        Engineer toUpdate = (Engineer) testEmployeeList.get(0);
        toUpdate.setGender(expectedGender);
        Engineer updated = employeeDAO.updateEntity(toUpdate);
        assertNotNull(updated);
        assertEquals(expectedGender, updated.getGender());
    }

    @Test
    @DisplayName("Test update Worker")
    void should_update_worker() throws EmployeeSaveException, EmployeeDataException {
        String expectedName = "Nam";
        Worker toUpdate = (Worker) testEmployeeList.get(3);
        toUpdate.setName(expectedName);
        Worker updated = employeeDAO.updateEntity(toUpdate);
        assertNotNull(updated);
        assertEquals(expectedName, updated.getName());
    }

    @Test
    @DisplayName("Test update Employee with null id")
    void should_not_update_employee_null_id() {
        Employee employee = testEmployeeList.get(0);
        employee.setId(null);
        assertThrows(EmployeeDataException.class, () -> {
            Employee updated = employeeDAO.updateEntity(employee);
        });
    }

    @Test
    @DisplayName("Test update Employee with non exist id")
    void should_not_update_employee_non_exist_id() {
        Employee employee = new Employee();
        employee.setId(UUID.randomUUID());
        assertThrows(EmployeeSaveException.class, () -> {
            Employee updated = employeeDAO.updateEntity(employee);
        });
    }

    @Test
    @DisplayName("Test Delete Employee")
    void should_delete_employee() {
        Employee employee = testEmployeeList.get(4);
        Employee employeeDeleted = employeeDAO.deleteEntity(employee);
        assertNotNull(employeeDeleted);
        assertEquals(employee, employeeDeleted);
    }

    @Test
    @DisplayName("Test Delete Engineer")
    void should_delete_engineer() throws EmployeeSaveException {
        Engineer engineer = new Engineer("dummy",
                Gender.MALE,
                Date.valueOf(LocalDate.of(1992, 9, 17)),
                Date.valueOf(LocalDate.now()),
                BigDecimal.valueOf(0.01),
                BigDecimal.valueOf(0.001));
        employeeDAO.addNewEntity(engineer);
        Engineer employeeDeleted = employeeDAO.deleteEntity(engineer);
        assertNotNull(employeeDeleted);
        assertEquals(engineer, employeeDeleted);
    }

    @Test
    @DisplayName("Test Delete Worker")
    void should_delete_worker() throws EmployeeSaveException {
        Worker worker = new Worker("dummy",
                Gender.MALE,
                Date.valueOf(LocalDate.of(1992, 9, 17)),
                Date.valueOf(LocalDate.now()),
                BigDecimal.valueOf(0.001));
        employeeDAO.addNewEntity(worker);
        Worker employeeDeleted = employeeDAO.deleteEntity(worker);
        assertNotNull(employeeDeleted);
        assertEquals(worker, employeeDeleted);
    }

    @Test
    @DisplayName("Test Delete Non Exist Employee")
    void should_throw_not_found() {
        assertThrows(EntityNotFoundException.class, () -> {
            Employee employee = new Employee();
            employee.setId(UUID.randomUUID());
            employeeDAO.deleteEntity(employee);
        });
    }

    @Test
    @DisplayName("Test Delete Exist Id")
    void should_delete_exist_id() {
        Employee employee = testEmployeeList.get(2);
        Employee employeeDeleted = employeeDAO.deleteEntity(employee.getId());
        assertNotNull(employeeDeleted);
        assertEquals(employee, employeeDeleted);
    }

    @Test
    @DisplayName("Test Delete Non Exist Id")
    void should_delete_non_exist_id() {
        assertThrows(EntityNotFoundException.class, () -> {
            Employee employeeDeleted = employeeDAO.deleteEntity(UUID.randomUUID());
        });
    }

    @Test
    @DisplayName("Test get Employee return Type")
    void should_return_correct_Employee_Type() {
        Engineer engineer = (Engineer) testEmployeeList.get(0);
        Engineer employeeFound = employeeDAO.findEntityByID(engineer.getId());
        assertNotNull(employeeFound);
        assertEquals(engineer, employeeFound);
    }
}
