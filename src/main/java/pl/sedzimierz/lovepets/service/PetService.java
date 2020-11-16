package pl.sedzimierz.lovepets.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import pl.sedzimierz.lovepets.model.Pet;
import pl.sedzimierz.lovepets.repository.PetRepository;
import pl.sedzimierz.lovepets.service.dto.PetDTO;
import pl.sedzimierz.lovepets.service.mapper.PetMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PetService {

    private final Logger log = LoggerFactory.getLogger(PetService.class);

    private final PetRepository petRepository;
    private final PetMapper petMapper;
    private final PetImageService petImageService;

    public PetService(PetRepository petRepository, PetMapper petMapper, PetImageService petImageService) {
        this.petRepository = petRepository;
        this.petMapper = petMapper;
        this.petImageService = petImageService;
    }

    @Transactional(readOnly = true)
    public List<PetDTO> getNotAdoptedPets() {
        return petRepository
                .findAllByAdoptedFalse()
                .stream()
                .map(PetDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<PetDTO> getPetById(Long id) {
        return petRepository
                .findById(id)
                .map(PetDTO::new);
    }

    @Transactional
    public Optional<PetDTO> savePet(PetDTO petDTO, MultipartFile petImage) {
        Pet pet = petMapper.mapToEntity(petDTO);
        petRepository.save(pet);

        if (!petImage.isEmpty()) {
            String imageUrl = petImageService.saveImageAndGetUrl(petImage, pet.getId());
            pet.setImageUrl(imageUrl);
            petRepository.save(pet);
        }

        if (petDTO.getId() == null) {
            log.info("Created pet: {}", pet);
        } else {
            log.info("Updated pet: {}", pet);
        }

        return Optional.of(new PetDTO(pet));
    }
}
