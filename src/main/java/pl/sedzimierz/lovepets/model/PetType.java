package pl.sedzimierz.lovepets.model;

import javax.persistence.*;

@Entity
@Table(name = "pet_types")
public class PetType extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    public PetType() {
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
        return "PetType {" +
                "id: " + id +
                ", name: '" + name + '\'' +
                '}';
    }
}
