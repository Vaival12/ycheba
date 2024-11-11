package Inc.TechSolutions.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer money;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public Long getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public Integer getMoney() {
        return money;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public void setClient(Client client) {
        this.client = client;
    }

}