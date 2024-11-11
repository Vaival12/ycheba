package Inc.TechSolutions.dao.Client;

import Inc.TechSolutions.Entity.Client;

import java.util.List;

public interface ClientService {
    void addClient(Client client);
    void deleteClient(long id);
    Client getClientById(long id);

    void update(long id);

    List<Client> readAllClients();
}
