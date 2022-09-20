package co.edu.icesi.zoo.controller;

import co.edu.icesi.zoo.api.AnimalZooAPI;
import co.edu.icesi.zoo.constant.AnimalErrorCode;
import co.edu.icesi.zoo.constant.AnimalErrorMsgs;
import co.edu.icesi.zoo.constant.BurmesePython;
import co.edu.icesi.zoo.dto.AnimalDTO;
import co.edu.icesi.zoo.dto.AnimalNoParentsDTO;
import co.edu.icesi.zoo.mapper.AnimalMapper;
import co.edu.icesi.zoo.service.AnimalService;
import co.edu.icesi.zoo.utils.AnimalExceptionUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
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
        validatePhytonCharacteristics(animalDTO.getAge(), animalDTO.getHeight(), animalDTO.getWeight());
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

    /*
     * CONTROLLER VALIDATIONS
     */
    private void validateAnimalName(String name){
        if(name.length()>120||!name.matches("[a-zA-Z0-9 ]+"))
            AnimalExceptionUtils.throwAnimalException(HttpStatus.BAD_REQUEST, AnimalErrorCode.CODE_01, AnimalErrorMsgs.WRONG_NAME_FORMAT_MSG);
    }

    private void validateDate(String date){
        try{
            LocalDateTime arrivalDate = LocalDateTime.parse(date);
            LocalDateTime now = LocalDateTime.now();
            if(arrivalDate.isAfter(now))
                AnimalExceptionUtils.throwAnimalException(HttpStatus.BAD_REQUEST, AnimalErrorCode.CODE_02, AnimalErrorMsgs.IMPOSSIBLE_DATE_MSG);
        }catch ( DateTimeParseException e){
            AnimalExceptionUtils.throwAnimalException(HttpStatus.BAD_REQUEST, AnimalErrorCode.CODE_02, AnimalErrorMsgs.WRONG_DATE_FORMAT_MSG);
        }
    }

    private void validatePhytonCharacteristics(int age, int height, int weight){
        if(!(age>0&&age<= BurmesePython.MAX_AGE&&height>0&&height<=BurmesePython.MAX_HEIGHT&&weight>0&&weight<=BurmesePython.MAX_HEIGHT))
            AnimalExceptionUtils.throwAnimalException(HttpStatus.BAD_REQUEST,AnimalErrorCode.CODE_03, AnimalErrorMsgs.WRONG_PYTHON_CHARACTERISTICS_MSG);
    }


}
