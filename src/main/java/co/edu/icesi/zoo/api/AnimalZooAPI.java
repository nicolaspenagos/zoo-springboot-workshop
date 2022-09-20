package co.edu.icesi.zoo.api;

import co.edu.icesi.zoo.dto.AnimalDTO;
import co.edu.icesi.zoo.dto.AnimalNoParentsDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/animals")
public interface AnimalZooAPI {

    @PostMapping
    public AnimalDTO createAnimal(@RequestBody AnimalDTO animalDTO);

    @GetMapping
    public List<AnimalNoParentsDTO> getAnimals();

    @GetMapping("/{animalId}")
    public AnimalDTO getAnimal(@PathVariable String animalId);

}
