package co.edu.icesi.zoo.mapper;

import co.edu.icesi.zoo.dto.AnimalDTO;
import co.edu.icesi.zoo.dto.AnimalNoParentsDTO;
import co.edu.icesi.zoo.model.Animal;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AnimalMapper {

    Animal fromDTO(AnimalDTO animalDTO);
    AnimalDTO fromAnimal(Animal animal);

    AnimalNoParentsDTO fromAnimalNoParents(Animal animal);

}
