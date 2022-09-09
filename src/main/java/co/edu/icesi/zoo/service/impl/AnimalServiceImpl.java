package co.edu.icesi.zoo.service.impl;

import co.edu.icesi.zoo.model.Animal;
import co.edu.icesi.zoo.repository.AnimalRepository;
import co.edu.icesi.zoo.service.AnimalService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AnimalServiceImpl implements AnimalService {

    public final AnimalRepository animalRepository;


    @Override
    public Animal createAnimal(Animal animalDTO) {
        return animalRepository.save(animalDTO);
    }
}

