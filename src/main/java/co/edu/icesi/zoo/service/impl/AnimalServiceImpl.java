package co.edu.icesi.zoo.service.impl;

import co.edu.icesi.zoo.constant.AnimalErrorCode;
import co.edu.icesi.zoo.constant.AnimalErrorMsgs;
import co.edu.icesi.zoo.model.Animal;
import co.edu.icesi.zoo.repository.AnimalRepository;
import co.edu.icesi.zoo.service.AnimalService;
import co.edu.icesi.zoo.utils.AnimalExceptionUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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
        validateUniqueName(animalDTO.getName());
        return animalRepository.save(animalDTO);
    }

    @Override
    public List<Animal> getAnimals(){
        return   StreamSupport.stream(animalRepository.findAll().spliterator(),false).collect(Collectors.toList());
    }

    @Override
    public Animal getAnimal(String animalId) {
        return animalRepository.findById(animalId).orElse(null);
    }

    /*
     * SERVICE VALIDATIONS
     */
    private void validateUniqueName(String name){
        for(Animal currentAnimal:getAnimals()){
            if(currentAnimal.getName().equals(name))
                AnimalExceptionUtils.throwAnimalException(HttpStatus.BAD_REQUEST, AnimalErrorCode.CODE_04, AnimalErrorMsgs.NOT_UNIQUE_NAME_MSG);
        }
    }
}

