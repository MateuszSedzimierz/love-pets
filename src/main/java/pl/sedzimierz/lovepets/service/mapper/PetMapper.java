package pl.sedzimierz.lovepets.service.mapper;

import org.springframework.stereotype.Service;
import pl.sedzimierz.lovepets.model.Pet;
import pl.sedzimierz.lovepets.service.dto.PetDTO;

@Service
public class PetMapper implements BaseMapper<Pet, PetDTO> {

    private final PetTypeMapper petTypeMapper;
    private final PetDetailsMapper petDetailsMapper;
    private final UserMapper userMapper;
    private final AddressMapper addressMapper;

    public PetMapper(PetTypeMapper petTypeMapper, PetDetailsMapper petDetailsMapper, UserMapper userMapper, AddressMapper addressMapper) {
        this.petTypeMapper = petTypeMapper;
        this.petDetailsMapper = petDetailsMapper;
        this.userMapper = userMapper;
        this.addressMapper = addressMapper;
    }

    @Override
    public Pet mapToEntity(PetDTO petDTO) {
        if (petDTO == null) {
            return null;
        } else {
            Pet pet = new Pet();
            pet.setId(petDTO.getId());
            pet.setPetType(petTypeMapper.mapToEntity(petDTO.getPetTypeDTO()));
            pet.setName(petDTO.getName());
            pet.setAdopted(petDTO.isAdopted());
            pet.setAdoptionDate(petDTO.getAdoptionDate());
            pet.setImageUrl(petDTO.getImageUrl());
            pet.setPetDetails(petDetailsMapper.mapToEntity(petDTO.getPetDetailsDTO()));
            pet.setOwner(userMapper.mapToEntity(petDTO.getOwnerDTO()));
            pet.setAddress(addressMapper.mapToEntity(petDTO.getAddressDTO()));
            return pet;
        }
    }

    @Override
    public PetDTO mapToDTO(Pet pet) {
        return new PetDTO(pet);
    }
}
