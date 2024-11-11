package com.university.domain.Staff;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends CrudRepository<Staff,Long> {
    Staff getStaffById(long id);
}
