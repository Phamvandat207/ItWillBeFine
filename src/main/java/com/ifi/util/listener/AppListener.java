package com.ifi.util.listener;

import com.ifi.constants.Gender;
import com.ifi.data.EmployeeDAO;
import com.ifi.data.exception.EmployeeSaveException;
import com.ifi.entity.Employee;
import com.ifi.entity.Engineer;
import com.ifi.entity.Worker;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@WebListener()
public class AppListener implements ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener {
    public static final String JOIN_DATE = "11-08-2020";
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
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
            try {
                Stream<Employee> stream = Stream.of(
                        new Engineer("Duc",
                                Gender.MALE,
                                dateFormat.parse("17-09-1992"),
                                dateFormat.parse(JOIN_DATE),
                                BigDecimal.valueOf(0.08),
                                BigDecimal.valueOf(0.003)),
                        new Employee("Dat",
                                Gender.OTHER,
                                dateFormat.parse("15-07-1997"),
                                dateFormat.parse(JOIN_DATE)),
                        new Employee("Phuong",
                                Gender.FEMALE,
                                dateFormat.parse("20-04-1998"),
                                dateFormat.parse(JOIN_DATE)),
                        new Worker("Duy", Gender.MALE,
                                dateFormat.parse("17-09-1998"),
                                dateFormat.parse(JOIN_DATE),
                                BigDecimal.valueOf(0.003)),
                        new Employee("Chinh",
                                Gender.MALE,
                                dateFormat.parse("7-12-1998"),
                                dateFormat.parse(JOIN_DATE))
                );
                sampleData = stream.collect(Collectors.toList());
                sampleData.forEach(employee -> {
                    try {
                        employeeDAO.addNewEntity(employee);
                    } catch (EmployeeSaveException e) {
                        e.printStackTrace();
                    }
                });
            } catch (ParseException e) {
                e.printStackTrace();
            }
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
