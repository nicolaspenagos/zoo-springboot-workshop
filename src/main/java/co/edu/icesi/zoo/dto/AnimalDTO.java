package co.edu.icesi.zoo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnimalDTO {

    private String id;
    private String name;
    private char sex;
    private double weight;
    private int age;
    private double height;
    private String arrivalDate;
    private String motherId;
    private String fatherId;


}
