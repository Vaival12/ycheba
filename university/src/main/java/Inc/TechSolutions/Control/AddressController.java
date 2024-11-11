package Inc.TechSolutions.Control;

import Inc.TechSolutions.ApplicationServices.*;
import Inc.TechSolutions.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adr")
public class AddressController {

    @Autowired
    private AddressServiceImpl addressService;

    // Address
    @PostMapping("/Address")
    public void addAddress(Address address) {
        addressService.addAddress(address);
    }

    @GetMapping("/Address/{id}")
    public Address getAddressById(long id) {
        return addressService.getAddressById(id);
    }

    @DeleteMapping("/Address/{id}")
    public void deleteAddress(long id) {
        addressService.deleteAddress(id);
    }

    @GetMapping("/Address")
    public List<Address> readAllAddress(){
        return addressService.readAllAddress();
    }

    @PutMapping("/Address/{id}")
    public void updateAddress(Long id) {
        addressService.update(id);
    }

}