package com.ifi.util.listener;

import com.ifi.constants.Gender;
import com.ifi.data.EmployeeDAO;
import com.ifi.data.exception.EmployeeSaveException;
import com.ifi.entity.Employee;
import com.ifi.entity.Engineer;
import com.ifi.entity.Worker;

import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@WebListener()
public class AppListener implements ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener {
    public static final LocalDate JOIN_DATE = LocalDate.of(2020, 8, 11);
    @Inject
    EmployeeDAO employeeDAO;

    // Public constructor is required by servlet spec
    public AppListener() {
    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    @Override
    public void contextInitialized(ServletContextEvent sce) {
      /* This method is called when the servlet context is
         initialized(when the Web application is deployed). 
         You can initialize servlet context related data here.
      */
        if (employeeDAO.getCurrentSize() == 0) {
            List<Employee> sampleData;
            Stream<Employee> stream = Stream.of(
                    new Engineer("Duc",
                            Gender.MALE,
                            Date.valueOf(LocalDate.of(1992, 9, 17)),
                            Date.valueOf(JOIN_DATE),
                            BigDecimal.valueOf(0.08),
                            BigDecimal.valueOf(0.003)),
                    new Employee("Dat",
                            Gender.OTHER,
                            Date.valueOf(LocalDate.of(1997, 7, 15)),
                            Date.valueOf(JOIN_DATE)),
                    new Employee("Phuong",
                            Gender.FEMALE,
                            Date.valueOf(LocalDate.of(1998, 4, 20)),
                            Date.valueOf(JOIN_DATE)),
                    new Worker("Duy", Gender.MALE,
                            Date.valueOf(LocalDate.of(1998, 9, 17)),
                            Date.valueOf(JOIN_DATE),
                            BigDecimal.valueOf(0.003)),
                    new Employee("Chinh",
                            Gender.MALE,
                            Date.valueOf(LocalDate.of(1999, 9, 17)),
                            Date.valueOf(JOIN_DATE))
            );
            sampleData = stream.collect(Collectors.toList());
            sampleData.forEach(employee -> {
                try {
                    employeeDAO.addNewEntity(employee);
                } catch (EmployeeSaveException e) {
                    e.printStackTrace();
                }
            });
        }

        System.out.println("Application Started!");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
      /* This method is invoked when the Servlet Context 
         (the Web application) is undeployed or 
         Application Server shuts down.
      */
    }

    // -------------------------------------------------------
    // HttpSessionListener implementation
    // -------------------------------------------------------
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        /* Session is created. */
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        /* Session is destroyed. */
    }

    // -------------------------------------------------------
    // HttpSessionAttributeListener implementation
    // -------------------------------------------------------

    @Override
    public void attributeAdded(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute 
         is added to a session.
      */
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute
         is removed from a session.
      */
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent sbe) {
      /* This method is invoked when an attribute
         is replaced in a session.
      */
    }
}
