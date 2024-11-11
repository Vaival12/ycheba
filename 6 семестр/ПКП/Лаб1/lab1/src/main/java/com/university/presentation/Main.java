package com.university.presentation;

import com.university.application.ApplicationServiceImpl;
import com.university.domain.Employee;
import com.university.domain.Department;
import java.util.Iterator;
import java.util.Scanner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    private static Scanner scanner;

    public Main() {
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(new Class[]{AppConfig.class});
        ApplicationServiceImpl reMonitor = context.getBean(ApplicationServiceImpl.class);

        while(askUser(reMonitor)) {
        }

        System.out.println("Program exit TY for using");
        scanner.nextLine();
        scanner.close();
        context.close();
    }

    public static boolean askUser(ApplicationServiceImpl reMonitor) {
        System.out.println("Choice: ");
        System.out.println("1) Show all employees");
        System.out.println("2) Show all departments");
        System.out.println("3) Show all");
        System.out.println("4) Add department");
        System.out.println("5) Add employee");
        System.out.println("6) Get all employees with department id");
        System.out.println("7) Delete Employee");
        System.out.println("8) Delete Department");
        switch (scanner.nextLine()) {
            case "1":
                showEmployees(reMonitor);
                break;
            case "2":
                showDepartments(reMonitor);
                break;
            case "3":
                showAll(reMonitor);
                break;
            case "4":
                addDepartment(reMonitor);
                break;
            case "5":
                addEmployee(reMonitor);
                break;
            case "6":
                showEmployeesIdDepartment(reMonitor);
                break;
            case "7":
                deleteEmployee(reMonitor);
                break;
            case "8":
                deleteDepartment(reMonitor);
                break;
            default:
                return false;
        }

        return true;
    }

    private static void showEmployees(ApplicationServiceImpl reMonitor) {
        System.out.printf("id |%-10s|%-8s|%-10s\n", "name", "age", "post");
        Iterator var1 = reMonitor.getEmployees().iterator();

        while(var1.hasNext()) {
            Employee employee = (Employee)var1.next();
            System.out.println(employee.toString());
        }

        scanner.nextLine();
    }

    private static boolean addEmployee(ApplicationServiceImpl reMonitor) {
        System.out.println("Enter space-separated: id name idDepartment age post");
        String[] data = scanner.nextLine().split(" ");
        if (data.length < 5) {
            System.out.println("Less than 5 elements were provided");
            scanner.nextLine();
            return false;
        } else {
            try {
                reMonitor.addEmployee(Integer.parseInt(data[0]), data[1], Integer.parseInt(data[2]), data[3], data[4]);
            } catch (NumberFormatException var3) {
                System.out.println("ERROR: write normal data!");
                scanner.nextLine();
            }

            return true;
        }
    }

    private static void showDepartments(ApplicationServiceImpl reMonitor) {
        System.out.printf("Id |%-10s|Ems|Rms\n", "name");
        Iterator var1 = reMonitor.getDepartment().iterator();

        while(var1.hasNext()) {
            Department department = (Department) var1.next();
            System.out.println(department.toString());
        }

        scanner.nextLine();
    }

    private static boolean addDepartment(ApplicationServiceImpl reMonitor) {
        System.out.println("Enter space-separated: id name countOfEmployees CountOfRooms");
        String[] data = scanner.nextLine().split(" ");
        if (data.length < 4) {
            System.out.println("Less than 4 elements were provided");
            scanner.nextLine();
            return false;
        } else {
            try {
                reMonitor.addDepartment(Integer.parseInt(data[0]), data[1], Integer.parseInt(data[2]), Integer.parseInt(data[3]));
            } catch (NumberFormatException var3) {
                System.out.println("ERROR: Not a number or unavailable data");
                scanner.nextLine();
            }

            return true;
        }
    }

    private static void showAll(ApplicationServiceImpl reMonitor) {
        showDepartments(reMonitor);
        showEmployees(reMonitor);
    }

    private static void showEmployeesIdDepartment(ApplicationServiceImpl reMonitor) {
        System.out.println("6) Get all employees with department id:");
        String id = scanner.nextLine();

        try {
            Iterator var2 = reMonitor.getEmployeesWithDepartment(Integer.parseInt(id)).iterator();

            while(var2.hasNext()) {
                Employee employee = (Employee)var2.next();
                System.out.println(employee.toString());
            }

            scanner.nextLine();
        } catch (NumberFormatException var4) {
            System.out.println("ERROR: not a number.");
            scanner.nextLine();
        }

    }

    private static void deleteEmployee(ApplicationServiceImpl reMonitor) {
        System.out.println("Enter employee id");
        String employeeId = scanner.nextLine();

        try {
            reMonitor.removeEmployee(Integer.parseInt(employeeId));
        } catch (NumberFormatException var3) {
            System.out.println("ERROR: not a number.");
            scanner.nextLine();
        }

    }

    private static void deleteDepartment(ApplicationServiceImpl reMonitor) {
        System.out.println("Enter department id");
        String employeeId = scanner.nextLine();

        try {
            if (reMonitor.removeDepartment(Integer.parseInt(employeeId))) {
                System.out.println("Department was removed");
            } else {
                System.out.println("Warning: not removed");
            }
        } catch (NumberFormatException var3) {
            System.out.println("ERROR: not a number.");
            scanner.nextLine();
        }

    }

    static {
        scanner = new Scanner(System.in);
    }
}
