package co.edu.icesi.zoo.controller;

import co.edu.icesi.zoo.api.AnimalZooAPI;
import co.edu.icesi.zoo.constant.AnimalErrorCode;
import co.edu.icesi.zoo.dto.AnimalDTO;
import co.edu.icesi.zoo.dto.AnimalNoParentsDTO;
import co.edu.icesi.zoo.error.exception.AnimalError;
import co.edu.icesi.zoo.error.exception.AnimalException;
import co.edu.icesi.zoo.mapper.AnimalMapper;
import co.edu.icesi.zoo.service.AnimalService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class AnimalController implements AnimalZooAPI {

    public final AnimalService animalService;
    public final AnimalMapper animalMapper;

    @Override
    public AnimalDTO createAnimal(AnimalDTO animalDTO) {
        validateAnimalName(animalDTO.getName());
        validateDate(animalDTO.getArrivalDate());
        return animalMapper.fromAnimal(animalService.createAnimal(animalMapper.fromDTO(animalDTO)));
    }

    @Override
    public List<AnimalNoParentsDTO> getAnimals(){
        return animalService.getAnimals().stream().map(animalMapper::fromAnimalNoParents).collect(Collectors.toList());
    }

    @Override
    public AnimalDTO getAnimal(String animalId) {
        return animalMapper.fromAnimal(animalService.getAnimal(animalId));
    }

    private void validateAnimalName(String name){
        if(name.length()>120||!name.matches("[a-zA-Z0-9 ]+")){
            throw new AnimalException(HttpStatus.BAD_REQUEST, new AnimalError(AnimalErrorCode.CODE_01, "WrongNameFormatException: name cannot exceed 120 digits length and can only contain letters and spaces"));
        }
    }

    private void validateDate(String date){
        try{
            LocalDate arrivalDate = LocalDate.parse(date);
            LocalDate now = LocalDate.now();
            if(arrivalDate.isAfter(now)){
                throw new AnimalException(HttpStatus.BAD_REQUEST, new AnimalError(AnimalErrorCode.CODE_02,"WrongDateException: the arrival date cannot be later than the current date"));
            }
        }catch ( DateTimeParseException e){
            throw new AnimalException(HttpStatus.BAD_REQUEST, new AnimalError(AnimalErrorCode.CODE_02,"DateTimeParseException: text cannot be parsed to a date, make sure you are using yyyy-mm-dd format"));
        }
    }

}
