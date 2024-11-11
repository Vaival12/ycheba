package com.university.domain;

import java.util.List;

public interface EmployeeRepository {
    Employee getEmployee(int var1);

    boolean editEmployee(Employee var1, int var2);

    boolean removeEmployee(Employee var1);

    boolean addEmployee(Employee var1);

    List<Employee> getEmployees();
}
