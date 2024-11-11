package com.university.application;

import com.university.domain.Employee;
import com.university.domain.EmployeeRepositoryImpl;
import com.university.domain.Department;
import com.university.domain.DepartmentRepositoryImpl;
import java.util.ArrayList;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ApplicationServiceImpl implements ApplicationService {
    @Autowired
    private EmployeeRepositoryImpl employeeRepository;
    @Autowired
    private DepartmentRepositoryImpl departmentRepository;

    public ApplicationServiceImpl() {
    }

    @Transactional
    public void addDepartment(Department newDepartment) {
        this.departmentRepository.addDepartment(newDepartment);
    }

    @Transactional
    public void addDepartment(int id, String name, int countEms, int countRms) {
        this.departmentRepository.addDepartment(new Department(id, name, countEms, countRms));
    }

    @Transactional
    public boolean addEmployee(Employee newEmployee) {
        return this.employeeRepository.addEmployee(newEmployee);
    }

    @Transactional
    public boolean addEmployee(int id, String name, int idDepartment, String age, String post) {
        this.employeeRepository.addEmployee(new Employee(id, name, idDepartment, age, post));
        return true;
    }

    @Transactional
    public boolean removeDepartment(Department department) {
        return this.removeDepartment(department);
    }

    @Transactional
    public boolean removeDepartment(int id) {
        return this.departmentRepository.removeDepartment(this.departmentRepository.getDepartment(id));
    }

    @Transactional
    public boolean removeEmployee(Employee employee) {
        return this.removeEmployee(employee);
    }

    @Transactional
    public boolean removeEmployee(int id) {
        return this.employeeRepository.removeEmployee(this.employeeRepository.getEmployee(id));
    }

    @Transactional
    public boolean setDepartment(int id, Department department) {
        return this.departmentRepository.editDepartment(department, id);
    }

    @Transactional
    public boolean setEmployee(int id, Employee employee) {
        return this.employeeRepository.editEmployee(employee, id);
    }

    @Transactional
    public boolean editDepartment(int id, int newId, String newName, int newCountEmployee, int newCountRooms) {
        Department tempDepartment = new Department();
        if (newId != -1) {
            tempDepartment.setId(newId);
        }

        if (newName != "") {
            tempDepartment.setName(newName);
        }

        if (newCountEmployee != -1) {
            tempDepartment.setCountEmployees(newCountRooms);
        }

        if (newCountRooms != -1) {
            tempDepartment.setCountRooms(newCountRooms);
        }

        return this.departmentRepository.editDepartment(tempDepartment, id);
    }

    @Transactional
    public boolean editDepartment(int id, Department newDepartment) {
        return this.departmentRepository.editDepartment(newDepartment, id);
    }

    @Transactional
    public boolean editEmployee(int id, int newId, String newName, int newIdDepartment, String newAge, String newPost) {
        Employee tempEmployee = new Employee();
        if (newId != -1) {
            tempEmployee.setId(newId);
        }

        if (newName != "") {
            tempEmployee.setName(newName);
        }

        if (newIdDepartment != -1) {
            tempEmployee.setIdDepartment(newIdDepartment);
        }

        if (newAge != "") {
            tempEmployee.setAge(newAge);
        }

        if (newPost != "") {
            tempEmployee.setPost(newPost);
        }

        return this.employeeRepository.editEmployee(tempEmployee, id);
    }

    @Transactional
    public boolean editEmployee(int id, Employee newEmployee) {
        return this.employeeRepository.editEmployee(newEmployee, id);
    }

    @Transactional
    public ArrayList<Employee> getEmployees() {
        ArrayList<Employee> copy = new ArrayList(this.employeeRepository.getEmployees());
        return copy;
    }

    @Transactional
    public ArrayList<Department> getDepartment() {
        ArrayList<Department> copy = new ArrayList(this.departmentRepository.getDepartments());
        return copy;
    }

    @Transactional
    public Employee getEmployee(int id) {
        return this.employeeRepository.getEmployee(id);
    }

    @Transactional
    public Department getDepartment(int id) {
        return this.departmentRepository.getDepartment(id);
    }

    @Transactional
    public ArrayList<Employee> getEmployeesWithDepartment(int idDepartment) {
        return (ArrayList)this.employeeRepository.getEmployees().stream().filter((id) -> {
            return id.getIdDepartment() == idDepartment;
        }).collect(Collectors.toList());
    }

}
