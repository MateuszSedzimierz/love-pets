package pl.sedzimierz.lovepets.service.dto;

import pl.sedzimierz.lovepets.model.PetType;

public class PetTypeDTO {

    private Long id;

    private String name;

    public PetTypeDTO() {
    }

    public PetTypeDTO(PetType petType) {
        this.id = petType.getId();
        this.name = petType.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PetTypeDTO {" +
                "id: " + id +
                ", name: '" + name + '\'' +
                '}';
    }
}
