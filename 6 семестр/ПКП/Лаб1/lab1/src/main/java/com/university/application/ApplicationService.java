package com.university.application;

import com.university.domain.Department;
import com.university.domain.Employee;
import java.util.ArrayList;

public interface ApplicationService {
    void addDepartment(Department var1);

    void addDepartment(int var1, String var2, int var3, int var4);

    boolean addEmployee(Employee var1);

    boolean addEmployee(int var1, String var2, int var3, String var4, String var5);

    boolean removeDepartment(Department var1);

    boolean removeDepartment(int var1);

    boolean removeEmployee(Employee var1);

    boolean removeEmployee(int var1);

    boolean setDepartment(int var1, Department var2);

    boolean setEmployee(int var1, Employee var2);

    boolean editDepartment(int var1, int var2, String var3, int var4, int var5);

    boolean editDepartment(int var1, Department var2);

    boolean editEmployee(int var1, int var2, String var3, int var4, String var5, String var6);

    boolean editEmployee(int var1, Employee var2);

    ArrayList<Employee> getEmployees();

    ArrayList<Department> getDepartment();

    Employee getEmployee(int var1);

    Department getDepartment(int var1);

    ArrayList<Employee> getEmployeesWithDepartment(int var1);

}
