package com.webstersmalley.wages;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by: Matthew Smalley
 * Date: 06/03/14
 */

public class Application {
    private Application() {

    }
    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("wages.xml");
    }
}
