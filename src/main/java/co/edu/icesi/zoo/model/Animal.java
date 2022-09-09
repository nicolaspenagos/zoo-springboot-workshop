package co.edu.icesi.zoo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name="animal")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Animal {


    @Id
    private String id;
    private String name;
    private char sex;
    private int weight;
    private String age;
    private int height;
    private String arrivalDate;
    private String mother;
    private String father;

}
