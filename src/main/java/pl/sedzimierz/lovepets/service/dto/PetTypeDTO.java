package pl.sedzimierz.lovepets.service.dto;

import pl.sedzimierz.lovepets.model.PetType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.Instant;

public class PetTypeDTO {

    private Long id;

    @NotBlank
    @Size(min = 1)
    private String name;

    private String createdBy;

    private Instant createdDate;

    private String lastModifiedBy;

    private Instant lastModifiedDate;

    public PetTypeDTO() {
    }

    public PetTypeDTO(PetType petType) {
        this.id = petType.getId();
        this.name = petType.getName();
        this.createdBy = petType.getCreatedBy();
        this.createdDate = petType.getCreatedDate();
        this.lastModifiedBy = petType.getLastModifiedBy();
        this.lastModifiedDate = petType.getLastModifiedDate();
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
        return "PetTypeDTO {" +
                "id: " + id +
                ", name: '" + name + '\'' +
                ", createdBy: '" + createdBy + '\'' +
                ", createdDate: " + createdDate +
                ", lastModifiedBy: '" + lastModifiedBy + '\'' +
                ", lastModifiedDate: " + lastModifiedDate +
                '}';
    }
}
