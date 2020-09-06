package com.ifi.data;

import com.ifi.entity.Employee;
import com.ifi.entity.Engineer;
import com.ifi.entity.Worker;
import com.ifi.util.constants.Gender;
import org.jboss.weld.junit5.auto.AddBeanClasses;
import org.jboss.weld.junit5.auto.EnableAutoWeld;
import org.junit.jupiter.api.*;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @Inject
    EntityManager entityManager;

    @BeforeAll
    static void mockData() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        try {
            Stream<Employee> stream = Stream.of(
                    new Engineer("Duc", Gender.MALE,
                            dateFormat.parse("17-09-1992"),
                            dateFormat.parse("11-08-2020"),
                            BigDecimal.valueOf(0.08),
                            BigDecimal.valueOf(0.003)),
                    new Employee("Dat", Gender.OTHER, dateFormat.parse("15-07-1997"), dateFormat.parse("11-08-2020")),
                    new Employee("Phuong", Gender.FEMALE, dateFormat.parse("20-04-1998"), dateFormat.parse("11-08-2020")),
                    new Worker("Duy", Gender.MALE,
                            dateFormat.parse("17-09-1998"),
                            dateFormat.parse("11-08-2020"),
                            BigDecimal.valueOf(0.003)),
                    new Employee("Chinh", Gender.MALE, dateFormat.parse("7-12-1998"), dateFormat.parse("11-08-2020"))
            );
            testEmployeeList = stream.collect(Collectors.toList());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @BeforeEach
    void refreshDB() {
        entityManager.getTransaction().begin();
        testEmployeeList.forEach(employee -> entityManager.persist(employee));
        entityManager.getTransaction().commit();
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
        });
    }

}
