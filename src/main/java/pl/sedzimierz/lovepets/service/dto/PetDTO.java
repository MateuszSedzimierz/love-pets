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

    private UserDTO ownerDTO;

    private AddressDTO addressDTO;

    private String createdBy;

    private Instant createdDate;

    private String lastModifiedBy;

    private Instant lastModifiedDate;

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
        this.ownerDTO = new UserDTO(pet.getOwner());
        this.addressDTO = new AddressDTO(pet.getAddress());
        this.createdBy = pet.getCreatedBy();
        this.createdDate = pet.getCreatedDate();
        this.lastModifiedBy = pet.getLastModifiedBy();
        this.lastModifiedDate = pet.getLastModifiedDate();
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

    public UserDTO getOwnerDTO() {
        return ownerDTO;
    }

    public void setOwnerDTO(UserDTO ownerDTO) {
        this.ownerDTO = ownerDTO;
    }

    public AddressDTO getAddressDTO() {
        return addressDTO;
    }

    public void setAddressDTO(AddressDTO addressDTO) {
        this.addressDTO = addressDTO;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @Override
    public String toString() {
        return "PetDTO {" +
                "id: " + id +
                ", petTypeDTO: " + petTypeDTO +
                ", name: '" + name + '\'' +
                ", adopted: " + adopted +
                ", adoptionDate: " + adoptionDate +
                ", imageUrl: '" + imageUrl + '\'' +
                ", petDetailsDTO: " + petDetailsDTO +
                ", ownerDTO: " + ownerDTO +
                ", addressDTO: " + addressDTO +
                ", createdBy: '" + createdBy + '\'' +
                ", createdDate: " + createdDate +
                ", lastModifiedBy: '" + lastModifiedBy + '\'' +
                ", lastModifiedDate: " + lastModifiedDate +
                '}';
    }
}
