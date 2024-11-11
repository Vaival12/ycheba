package Inc.TechSolutions.ApplicationServices;

import Inc.TechSolutions.dao.Department.DepartmentRepository;
import Inc.TechSolutions.dao.Department.DepartmentService;
import Inc.TechSolutions.Entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;
    @Override
    public void addDepartment(Department department) {
        departmentRepository.save(department);
    }

    @Override
    public void deleteDepartment(long id) {
        departmentRepository.deleteById(id);
    }

    @Override
    public Department getDepartmentById(long id) {
        return departmentRepository.getDepartmentById(id);
    }

    @Override
    public List<Department> readAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public void update(long id){
        Department department = departmentRepository.getDepartmentById(id);
        departmentRepository.save(department);
    }

}
