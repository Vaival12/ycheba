package Inc.TechSolutions.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;
    private String city;


    public Long getId() {
        return id;
    }

    public String getIdString() {
        return String.valueOf(id);
    }

    public String getStreet() {
        return street ;
    }

    public String getCity() {
        return city ;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStreet(String street) {
        this.street  = street ;
    }

    public void setCity(String city) {
        this.city  = city ;
    }

    public String toString() {
        return String.format("%-3s|%-12s|%12s", this.id, this.street, this.city);
    }
}
