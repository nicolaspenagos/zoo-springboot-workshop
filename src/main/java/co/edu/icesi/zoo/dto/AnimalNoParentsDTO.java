package co.edu.icesi.zoo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalNoParentsDTO {

    private String id;
    private String name;
    private char sex;
    private int weight;
    private int age;
    private int height;
    private String arrivalDate;


}
