package Inc.TechSolutions.ApplicationServices;

import Inc.TechSolutions.dao.Address.AddressService;
import Inc.TechSolutions.dao.Address.AddressRepository;
import Inc.TechSolutions.Entity.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class AddressServiceImpl implements AddressService {
    Scanner scanner = new Scanner(System.in);
    @Autowired
    AddressRepository addressRepository;
    @Override
    public void addAddress(Address address) {
        addressRepository.save(address);
    }

    @Override
    public void deleteAddress(long id) {
        addressRepository.deleteById(id);
    }

    @Override
    public Address getAddressById(long id) {
        return addressRepository.getAddressById(id);
    }

    @Override
    public List<Address> readAllAddress() {
        return addressRepository.findAll();
    }

    @Override
    public void update(long id) {
        Address address = addressRepository.getAddressById(id);
        addressRepository.save(address);
    }

}
