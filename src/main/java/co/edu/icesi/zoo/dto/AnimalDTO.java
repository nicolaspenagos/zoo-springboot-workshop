package co.edu.icesi.zoo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalDTO {

    private String id;
    private String name;
    private String sex;
    private int weight;
    private String age;
    private int height;
    private String arrivalDate;
    private String mother;
    private String father;

}
