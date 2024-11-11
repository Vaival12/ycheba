package Inc.TechSolutions.ApplicationServices;

import Inc.TechSolutions.dao.Client.ClientRepository;
import Inc.TechSolutions.dao.Client.ClientService;
import Inc.TechSolutions.Entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    ClientRepository clientRepository;
    @Override
    public void addClient(Client client) {
        clientRepository.save(client);
    }

    @Override
    public void deleteClient(long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public Client getClientById(long id) {
        return clientRepository.getClientById(id);
    }

    @Override
    public List<Client> readAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public void update(long id) {
        Client client = clientRepository.getClientById(id);
        clientRepository.save(client);
    }

}
