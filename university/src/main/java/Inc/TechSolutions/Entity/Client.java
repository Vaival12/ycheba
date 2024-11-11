package Inc.TechSolutions.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Client")
public class Client {


    public enum Gender{
        Male, Female
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String dateOfBirth;

    private Client.Gender gender;

    public Long getId() {
        return id;
    }

    public String getIdString() {
        return String.valueOf(id);
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public Gender getGender(){return gender;}

    public void setId(Long id) {
        this.id = id;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(Gender gender){this.gender=gender;}
    
}
