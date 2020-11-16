package pl.sedzimierz.lovepets.service.mapper;

import org.springframework.stereotype.Service;
import pl.sedzimierz.lovepets.model.PetDetails;
import pl.sedzimierz.lovepets.service.dto.PetDetailsDTO;

@Service
public class PetDetailsMapper implements BaseMapper<PetDetails, PetDetailsDTO> {

    @Override
    public PetDetails mapToEntity(PetDetailsDTO petDetailsDTO) {
        if (petDetailsDTO == null) {
            return null;
        } else {
            PetDetails petDetails = new PetDetails();
            petDetails.setId(petDetailsDTO.getId());
            petDetails.setDescription(petDetailsDTO.getDescription());
            petDetails.setGender(petDetailsDTO.getGender());
            petDetails.setSize(petDetailsDTO.getSize());
            petDetails.setAge(petDetailsDTO.getAge());
            petDetails.setSterilized(petDetailsDTO.isSterilized());
            return petDetails;
        }
    }

    @Override
    public PetDetailsDTO mapToDTO(PetDetails petDetails) {
        return new PetDetailsDTO(petDetails);
    }
}
