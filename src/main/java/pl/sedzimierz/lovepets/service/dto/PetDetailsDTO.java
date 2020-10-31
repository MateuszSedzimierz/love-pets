package pl.sedzimierz.lovepets.service.dto;

import pl.sedzimierz.lovepets.model.GenderType;
import pl.sedzimierz.lovepets.model.PetAge;
import pl.sedzimierz.lovepets.model.PetDetails;
import pl.sedzimierz.lovepets.model.PetSize;

public class PetDetailsDTO {

    private Long id;

    private String description;

    private GenderType gender;

    private PetSize size;

    private PetAge age;

    private boolean sterilized;

    public PetDetailsDTO() {
    }

    public PetDetailsDTO(PetDetails petDetails) {
        this.id = petDetails.getId();
        this.description = petDetails.getDescription();
        this.gender = petDetails.getGender();
        this.size = petDetails.getSize();
        this.age = petDetails.getAge();
        sterilized = petDetails.isSterilized();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public GenderType getGender() {
        return gender;
    }

    public void setGender(GenderType gender) {
        this.gender = gender;
    }

    public PetSize getSize() {
        return size;
    }

    public void setSize(PetSize size) {
        this.size = size;
    }

    public PetAge getAge() {
        return age;
    }

    public void setAge(PetAge age) {
        this.age = age;
    }

    public boolean isSterilized() {
        return sterilized;
    }

    public void setSterilized(boolean sterilized) {
        this.sterilized = sterilized;
    }

    @Override
    public String toString() {
        return "PetDetailsDTO {" +
                "id: " + id +
                ", description: '" + description + '\'' +
                ", gender: " + gender +
                ", size: " + size +
                ", age: " + age +
                ", sterilized: " + sterilized +
                '}';
    }
}
