package Inc.TechSolutions.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "count_Employee")
    private int countEmployee;


    public void setCountEmployee(int countEmployee) {
        this.countEmployee = countEmployee;
    }

    public int getCountEmployee() {
        return countEmployee;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }
    public String getIdString() {
        return String.valueOf(id);
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public String toString() {
        return String.format("%-3s|%-12s|%3d", this.id, this.name, this.countEmployee);
    }
}