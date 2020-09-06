package com.ifi.data;

import com.ifi.entity.Employee;
import com.ifi.util.constants.Gender;
import org.jboss.weld.junit5.auto.AddBeanClasses;
import org.jboss.weld.junit5.auto.EnableAutoWeld;
import org.junit.jupiter.api.*;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

    @BeforeAll
    static void init() {
        testEmployeeList = new LinkedList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        try {
            testEmployeeList.add(new Employee("Duc", Gender.MALE, dateFormat.parse("17-09-1992"), dateFormat.parse("11-08-2020")));
            testEmployeeList.add(new Employee("Dat", Gender.OTHER, dateFormat.parse("15-07-1997"), dateFormat.parse("11-08-2020")));
            testEmployeeList.add(new Employee("Phuong", Gender.FEMALE, dateFormat.parse("20-04-1998"), dateFormat.parse("11-08-2020")));
            testEmployeeList.add(new Employee("Duy", Gender.MALE, dateFormat.parse("17-09-1998"), dateFormat.parse("11-08-2020")));
            testEmployeeList.add(new Employee("Chinh", Gender.MALE, dateFormat.parse("7-12-1998"), dateFormat.parse("11-08-2020")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Inject
    EmployeeDAO employeeDAO;

    @Inject
    EntityManager entityManager;

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
    @DisplayName("Test FindAll Method")
    void should_return_same_dataSize() {
        int expectedDataSize = testEmployeeList.size();
        List<Employee> employees = employeeDAO.findAllEmployee();
        assertEquals(expectedDataSize, employees.size());
    }

}
