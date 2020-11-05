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
        return petTypeRepository.findAllByOrderById()
                .stream()
                .map(PetTypeDTO::new)
                .collect(Collectors.toList());
    }

    public void createPetType(PetTypeDTO petTypeDTO) {
        String petTypeName = petTypeDTO.getName();
        if (isPetTypeNamePresent(petTypeName)) {
            throw new PetTypeNameAlreadyExistsException();
        } else {
            PetType petType = petTypeMapper.mapToEntity(petTypeDTO);
            petTypeRepository.save(petType);
            log.info("Created pet type: {}", petType);
        }
    }

    private boolean isPetTypeNamePresent(String petTypeName) {
        if (petTypeRepository.findOneByName(petTypeName).isPresent()) {
            log.debug("Pet type name '{}' exists in database", petTypeName);
            return true;
        }
        return false;
    }

    public Optional<PetTypeDTO> getPetTypeById(Long id) {
        return petTypeRepository
                .findById(id)
                .map(PetTypeDTO::new);
    }

    public Optional<PetTypeDTO> updatePetType(PetTypeDTO petTypeDTO) {
        return Optional.of(petTypeRepository
                .findById(petTypeDTO.getId())
                .map(petType -> {
                    if (isPetTypeNamePresent(petTypeDTO.getName())) {
                        throw new PetTypeNameAlreadyExistsException();
                    } else {
                        petType = petTypeMapper.mapToEntity(petTypeDTO);
                        petTypeRepository.save(petType);
                        log.info("Updated pet type: {}", petType);
                    }
                    return petTypeDTO;
                }))
                .orElse(Optional.empty());
    }

    public void deletePetType(Long id) {
        petTypeRepository
                .findById(id)
                .ifPresent(petType -> {
                    petTypeRepository.delete(petType);
                    log.info("Deleted pet type: {}", petType);
                });
    }
}
