package pl.sedzimierz.lovepets.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import pl.sedzimierz.lovepets.model.Pet;
import pl.sedzimierz.lovepets.repository.PetRepository;
import pl.sedzimierz.lovepets.security.AuthoritiesConstants;
import pl.sedzimierz.lovepets.security.SecurityUtils;
import pl.sedzimierz.lovepets.service.dto.PetDTO;
import pl.sedzimierz.lovepets.service.exception.PetDeleteNotAllowedException;
import pl.sedzimierz.lovepets.service.mapper.PetMapper;

import java.time.Instant;
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

    @Transactional(readOnly = true)
    public List<PetDTO> getAllPets() {
        return petRepository
                .findAll()
                .stream()
                .map(PetDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PetDTO> getPetsByOwnerLogin(String login) {
        return petRepository
                .findAllByOwnerLogin(login)
                .stream()
                .map(PetDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deletePetById(Long id) {
        petRepository
                .findById(id)
                .ifPresent(pet -> {
                    if (doesUserCanDeletePet(pet.getOwner().getLogin())) {
                        petRepository.delete(pet);
                        log.info("Deleted pet: {}", pet);
                    } else {
                        log.debug("User do not have permission to delete pet with id: {}", pet.getId());
                        throw new PetDeleteNotAllowedException();
                    }
                });
    }

    private boolean doesUserCanDeletePet(String petOwnerLogin) {
        if (!SecurityUtils.hasCurrentUserAuthority(AuthoritiesConstants.ADMIN)) {
            Optional<String> currentUserLogin = SecurityUtils.getCurrentUserLogin();
            if (currentUserLogin.isPresent()) {
                return currentUserLogin.get().equals(petOwnerLogin);
            }
        }
        return true;
    }

    @Transactional
    public void setAdopted(Long petId, boolean adopted) {
        petRepository
                .findById(petId)
                .ifPresent(pet -> {
                    pet.setAdopted(adopted);
                    if (adopted) {
                        pet.setAdoptionDate(Instant.now());
                        log.info("Pet with id {} was adopted", pet.getId());
                    } else {
                        pet.setAdoptionDate(null);
                    }
                    petRepository.save(pet);
                });
    }
}
