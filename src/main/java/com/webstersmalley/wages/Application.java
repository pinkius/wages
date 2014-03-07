package com.webstersmalley.wages;

import com.webstersmalley.wages.service.EmployeeService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by: Matthew Smalley
 * Date: 06/03/14
 */

@Configuration
public class Application {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("wages.xml");

        System.out.println(ac.getBean("employeeService", EmployeeService.class).getAllEmployees());
    }
}
