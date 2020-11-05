package pl.sedzimierz.lovepets.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.sedzimierz.lovepets.model.PetType;
import pl.sedzimierz.lovepets.repository.PetTypeRepository;
import pl.sedzimierz.lovepets.service.dto.PetTypeDTO;
import pl.sedzimierz.lovepets.service.exception.PetTypeNameAlreadyExistsException;
import pl.sedzimierz.lovepets.service.mapper.PetTypeMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PetTypeService {

    private final Logger log = LoggerFactory.getLogger(PetTypeService.class);

    private final PetTypeRepository petTypeRepository;
    private final PetTypeMapper petTypeMapper;

    public PetTypeService(PetTypeRepository petTypeRepository, PetTypeMapper petTypeMapper) {
        this.petTypeRepository = petTypeRepository;
        this.petTypeMapper = petTypeMapper;
    }

    public List<PetTypeDTO> getAllPetTypes() {
        return petTypeRepository.findAll()
                .stream()
                .map(PetTypeDTO::new)
                .collect(Collectors.toList());
    }

    public void createPetType(PetTypeDTO petTypeDTO) {
        String petTypeName = petTypeDTO.getName();
        if (petTypeRepository.findOneByName(petTypeName).isPresent()) {
            log.debug("Pet type name '{}' exists in database", petTypeName);
            throw new PetTypeNameAlreadyExistsException();
        } else {
            PetType petType = petTypeMapper.mapToEntity(petTypeDTO);
            petTypeRepository.save(petType);
            log.info("Created pet type: {}", petType);
        }
    }

    public Optional<PetTypeDTO> getPetTypeById(Long id) {
        return petTypeRepository
                .findById(id)
                .map(PetTypeDTO::new);
    }
}
