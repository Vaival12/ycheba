package Inc.TechSolutions.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String deadLine;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIdString() {
        return String.valueOf(id);
    }

    public String getDeadLine() {
        return deadLine;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDeadLine(String deadLine) {
        this.deadLine = deadLine;
    }

    public String toString() {
        return String.format("%-3s|%-12s", this.id, this.name);
    }
}
