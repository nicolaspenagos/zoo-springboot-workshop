package co.edu.icesi.zoo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnimalNoParentsDTO {

    private String id;
    private String name;
    private char sex;
    private int weight;
    private int age;
    private int height;
    private LocalDateTime arrivalDate;


}
