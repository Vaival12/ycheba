package com.university.controller;

import com.university.application.StaffServiceImpl;
import com.university.domain.Staff.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
@RestController
public class Controller {
    Scanner scanner = new Scanner(System.in);

    @Autowired
    private StaffServiceImpl staffService;

    @PostMapping
    public void addEmployee(Staff employee) {
        staffService.addEmployee(employee);
    }
    @GetMapping
    public Staff findEmployee(long id) {
        return staffService.findById(id);
    }
    @DeleteMapping
    public void deleteEmployee(long id) {
        staffService.delete(id);
    }

    public Staff createHeadStaff() {
        Staff head = new Staff();
        System.out.println("Введите ФИО начальника:");
        head.setFullName(scanner.next());
        scanner.nextLine();
        System.out.println("Введите адрес проживания начальника:");
        head.setAddress(scanner.nextLine());
        System.out.println("Введите отдел:");
        head.setRooms(scanner.nextLine());
        scanner.nextLine();
        System.out.println("Введите дату рождения сотрудника:");
        head.setBirthDate(scanner.nextLine());
        System.out.println("Введите должность сотрудника:");
        head.setPost(scanner.nextLine());
        return head;
    }
}
