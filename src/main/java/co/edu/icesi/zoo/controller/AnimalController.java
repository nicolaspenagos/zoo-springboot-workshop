package co.edu.icesi.zoo.controller;

import co.edu.icesi.zoo.api.AnimalZooAPI;
import co.edu.icesi.zoo.dto.AnimalDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AnimalController implements AnimalZooAPI {



    @Override
    public AnimalDTO createAnimal(AnimalDTO animalDTO) {
        return null;
    }
}
