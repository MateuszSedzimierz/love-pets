package pl.sedzimierz.lovepets.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sedzimierz.lovepets.repository.AddressRepository;
import pl.sedzimierz.lovepets.service.dto.AddressDTO;

import java.util.Optional;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Transactional(readOnly = true)
    public Optional<AddressDTO> getAddressById(Long id) {
        return addressRepository
                .findById(id)
                .map(AddressDTO::new);
    }
}