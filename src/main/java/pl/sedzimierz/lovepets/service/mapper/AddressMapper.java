package pl.sedzimierz.lovepets.service.mapper;

import org.springframework.stereotype.Service;
import pl.sedzimierz.lovepets.model.Address;
import pl.sedzimierz.lovepets.service.dto.AddressDTO;

@Service
public class AddressMapper implements BaseMapper<Address, AddressDTO> {

    @Override
    public Address mapToEntity(AddressDTO addressDTO) {
        if (addressDTO == null) {
            return null;
        } else {
            Address address = new Address();
            address.setId(addressDTO.getId());
            address.setCountry(addressDTO.getCountry());
            address.setCity(addressDTO.getCity());
            address.setState(addressDTO.getState());
            address.setZipCode(addressDTO.getZipCode());
            address.setStreetAddress(addressDTO.getStreetAddress());
            return address;
        }
    }

    @Override
    public AddressDTO mapToDTO(Address address) {
        return new AddressDTO(address);
    }
}
