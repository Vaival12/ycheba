package com.university.presentation;

import com.university.controller.Controller;
import com.university.domain.Staff.Staff;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component("presentation")
public class presentation {

    private Controller controller;
    private Scanner scanner = new Scanner(System.in);

    public presentation() {}

    @Autowired
    public presentation(Controller controller) {
        this.controller = controller;
    }

    public void askUser(){
        System.out.println("Interface:");
        System.out.println("1) Add Staff");

        switch (scanner.nextInt()){
            case 1: addStaff();
            break;

            default:
                System.out.println("Not a valid option");
        }
    }

    private void addStaff(){

        Staff staff = new Staff();
        System.out.println("Enter Full Name staff:");
        staff.setFullName(scanner.next());
        scanner.nextLine();
        System.out.println("Enter Address staff:");
        staff.setAddress(scanner.nextLine());

        System.out.println("Enter department staff:");
        staff.setDepartments(scanner.nextLine());

        System.out.println("Enter Birth Date staff:");
        staff.setBirthDate(scanner.nextLine());

        System.out.println("Enter position staff:");
        staff.setPost(scanner.nextLine());

        controller.addEmployee(staff);
        System.out.println("Staff added!");

    }
}
