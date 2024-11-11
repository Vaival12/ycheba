package com.university.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {
    private ArrayList<Employee> employees;

    public EmployeeRepositoryImpl() {
        this.init();
    }

    public Employee getEmployee(int id) {
        Iterator var2 = this.employees.iterator();

        Employee em;
        do {
            if (!var2.hasNext()) {
                return null;
            }

            em = (Employee)var2.next();
        } while(em.getId() != id);

        return em;
    }

    public boolean editEmployee(Employee newEmplyee, int id) {
        return !((Employee)this.employees.set(this.employees.indexOf(this.getEmployee(id)), newEmplyee)).equals((Object)null);
    }

    public boolean removeEmployee(Employee employee) {
        return this.employees.remove(employee);
    }

    public boolean addEmployee(Employee newEmployee) {
        return this.employees.add(newEmployee);
    }

    public List<Employee> getEmployees() {
        return this.employees;
    }

    private void init() {
        this.employees = new ArrayList();
    }
}
