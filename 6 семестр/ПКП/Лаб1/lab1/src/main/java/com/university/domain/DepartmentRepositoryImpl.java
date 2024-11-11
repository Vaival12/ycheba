package com.university.domain;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class DepartmentRepositoryImpl implements DepartmentRepository {
    private List<Department> departmentList;

    public Department getDepartment(int id) {
        for(int i = 0; i < this.departmentList.size(); ++i) {
            Department department = (Department)this.departmentList.get(i);
            if (department.getId() == id) {
                return department;
            }
        }

        return null;
    }

    public DepartmentRepositoryImpl() {
        this.init();
    }

    public boolean editDepartment(Department newDepartment, int id) {
        return !((Department)this.departmentList.set(this.departmentList.indexOf(this.getDepartment(id)), newDepartment)).equals((Object)null);
    }

    public boolean removeDepartment(Department department) {
        return this.departmentList.remove(department);
    }

    public boolean addDepartment(Department newDepartment) {
        if (this.getDepartment(newDepartment.getId()) != null) {
            return false;
        } else {
            return this.departmentList.add(newDepartment);
        }
    }

    public List<Department> getDepartments() {
        return this.departmentList;
    }

    public void init() {
        this.departmentList = new ArrayList();
    }
}
