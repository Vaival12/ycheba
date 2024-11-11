package com.university.domain;

import java.util.List;

public interface DepartmentRepository {
    Department getDepartment(int var1);

    boolean editDepartment(Department var1, int var2);

    boolean removeDepartment(Department var1);

    boolean addDepartment(Department var1);

    List<Department> getDepartments();
}
