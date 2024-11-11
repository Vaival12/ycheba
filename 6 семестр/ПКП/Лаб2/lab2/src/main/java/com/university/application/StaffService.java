package com.university.application;

import com.university.domain.Staff.Staff;

import java.util.ArrayList;

public interface StaffService {
    void addEmployee(Staff employee);
    void delete(long id);
    Staff findById(long id);
    void update(Long id);
}
