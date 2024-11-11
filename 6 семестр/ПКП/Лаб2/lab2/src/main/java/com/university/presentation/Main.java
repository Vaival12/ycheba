package com.university.presentation;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Main {


    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        presentation pr = context.getBean("presentation",presentation.class);
        pr.askUser();

    }


}

