package pl.sedzimierz.lovepets.model;

import javax.persistence.*;

@Entity
@Table(name = "pet_details")
public class PetDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private GenderType gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "size")
    private PetSize size;

    @Enumerated(EnumType.STRING)
    @Column(name = "age")
    private PetAge age;

    @Column(name = "sterilized")
    private boolean sterilized;

    public PetDetails() {
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
        return "PetDetails {" +
                "id: " + id +
                ", description: '" + description + '\'' +
                ", gender: " + gender +
                ", size: " + size +
                ", age: " + age +
                ", sterilized: " + sterilized +
                '}';
    }
}
