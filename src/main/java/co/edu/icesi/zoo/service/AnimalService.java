package co.edu.icesi.zoo.service;

import co.edu.icesi.zoo.model.Animal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface AnimalService {
    public Animal createAnimal(@RequestBody Animal animalDTO);
    public List<Animal> getAnimals();


    public Animal getAnimal(@PathVariable String animalId);

}
