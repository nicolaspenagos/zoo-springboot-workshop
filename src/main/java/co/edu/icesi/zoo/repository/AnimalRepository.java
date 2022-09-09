package co.edu.icesi.zoo.repository;

import co.edu.icesi.zoo.model.Animal;
import org.springframework.data.repository.CrudRepository;

public interface AnimalRepository extends CrudRepository<Animal, String> {


}
