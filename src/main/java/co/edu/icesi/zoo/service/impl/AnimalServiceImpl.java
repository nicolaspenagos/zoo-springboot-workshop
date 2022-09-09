package co.edu.icesi.zoo.service.impl;

import co.edu.icesi.zoo.model.Animal;
import co.edu.icesi.zoo.repository.AnimalRepository;
import co.edu.icesi.zoo.service.AnimalService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@Service
public class AnimalServiceImpl implements AnimalService {

    public final AnimalRepository animalRepository;


    @Override
    public Animal createAnimal(Animal animalDTO) {
        System.out.println(animalDTO.toString());
        return animalRepository.save(animalDTO);
    }

    @Override
    public List<Animal> getAnimals(){
        return   StreamSupport.stream(animalRepository.findAll().spliterator(),false).collect(Collectors.toList());
    }
}

