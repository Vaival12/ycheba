package Inc.TechSolutions.Control;

import Inc.TechSolutions.ApplicationServices.*;
import Inc.TechSolutions.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clt")
public class ClientController {

    @Autowired
    private ClientServiceImpl clientService;

    // Department
    @PostMapping("/Client")
    public void addClient(Client client) {
        clientService.addClient(client);
    }

    @GetMapping("/Client/{id}")
    public Client getClientById(long id) {
        return clientService.getClientById(id);
    }

    @DeleteMapping("/Client/{id}")
    public void deleteClient(long id) {
        clientService.deleteClient(id);
    }

    @GetMapping("/Client")
    public List<Client> readAllClients(){
        return clientService.readAllClients();
    }

    @PutMapping("/Client/{id}")
    public void updateClient(Long id) {
        clientService.update(id);
    }

}