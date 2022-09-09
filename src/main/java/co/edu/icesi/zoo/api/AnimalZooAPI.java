package co.edu.icesi.zoo.api;

import co.edu.icesi.zoo.dto.AnimalDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/animals")
public interface AnimalZooAPI {

    @PostMapping
    public AnimalDTO createAnimal(@RequestBody AnimalDTO animalDTO);

}
