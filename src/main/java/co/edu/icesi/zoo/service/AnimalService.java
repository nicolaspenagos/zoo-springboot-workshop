package co.edu.icesi.zoo.service;

import co.edu.icesi.zoo.model.Animal;
import org.springframework.web.bind.annotation.RequestBody;

public interface AnimalService {
    public Animal createAnimal(@RequestBody Animal animalDTO);
}
