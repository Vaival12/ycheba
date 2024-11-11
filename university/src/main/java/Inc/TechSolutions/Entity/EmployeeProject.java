package Inc.TechSolutions.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "EmployeeProject")
public class EmployeeProject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    public Project getProject() {
        return project;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Long getId() {
        return id;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String toString() {
        return String.format("%-3s|%-33s|%-3s", this.id, this.employee.getId(), this.project.getId());
    }
}