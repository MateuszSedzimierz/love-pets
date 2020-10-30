package pl.sedzimierz.lovepets.service.dto;

import pl.sedzimierz.lovepets.model.Pet;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.Instant;

public class PetDTO {

    private Long id;

    private PetTypeDTO petTypeDTO;

    @NotBlank
    @Size(min = 1)
    private String name;

    private boolean adopted;

    private Instant adoptionDate;

    private String imageUrl;

    private PetDetailsDTO petDetailsDTO;

    private UserDTO owner;

    private AddressDTO address;

    public PetDTO() {
    }

    public PetDTO(Pet pet) {
        this.id = pet.getId();
        this.petTypeDTO = new PetTypeDTO(pet.getPetType());
        this.name = pet.getName();
        this.adopted = pet.isAdopted();
        this.adoptionDate = pet.getAdoptionDate();
        this.imageUrl = pet.getImageUrl();
        this.petDetailsDTO = new PetDetailsDTO(pet.getPetDetails());
        this.owner = new UserDTO(pet.getOwner());
        this.address = new AddressDTO(pet.getAddress());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PetTypeDTO getPetTypeDTO() {
        return petTypeDTO;
    }

    public void setPetTypeDTO(PetTypeDTO petTypeDTO) {
        this.petTypeDTO = petTypeDTO;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAdopted() {
        return adopted;
    }

    public void setAdopted(boolean adopted) {
        this.adopted = adopted;
    }

    public Instant getAdoptionDate() {
        return adoptionDate;
    }

    public void setAdoptionDate(Instant adoptionDate) {
        this.adoptionDate = adoptionDate;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public PetDetailsDTO getPetDetailsDTO() {
        return petDetailsDTO;
    }

    public void setPetDetailsDTO(PetDetailsDTO petDetailsDTO) {
        this.petDetailsDTO = petDetailsDTO;
    }

    public UserDTO getOwner() {
        return owner;
    }

    public void setOwner(UserDTO owner) {
        this.owner = owner;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }
}
