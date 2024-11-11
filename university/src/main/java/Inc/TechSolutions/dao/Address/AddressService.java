package Inc.TechSolutions.dao.Address;

import Inc.TechSolutions.Entity.Address;

import java.util.List;

public interface AddressService {
    void addAddress(Address address);
    void deleteAddress(long id);
    Address getAddressById(long id);

    void update(long id);

    List<Address> readAllAddress();
}
