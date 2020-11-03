package pl.sedzimierz.lovepets.service.mapper;

import org.springframework.stereotype.Service;
import pl.sedzimierz.lovepets.model.PetType;
import pl.sedzimierz.lovepets.service.dto.PetTypeDTO;

@Service
public class PetTypeMapper implements BaseMapper<PetType, PetTypeDTO> {

    @Override
    public PetType mapToEntity(PetTypeDTO petTypeDTO) {
        if (petTypeDTO == null) {
            return null;
        } else {
            PetType petType = new PetType();
            petType.setId(petTypeDTO.getId());
            petType.setName(petTypeDTO.getName());
            return petType;
        }
    }

    @Override
    public PetTypeDTO mapToDTO(PetType petType) {
        return new PetTypeDTO(petType);
    }
}
