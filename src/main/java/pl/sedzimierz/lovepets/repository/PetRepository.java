package pl.sedzimierz.lovepets.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sedzimierz.lovepets.model.Pet;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

}
