package co.edu.icesi.zoo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Table(name="animal")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Animal {

    @Id
    @Type(type="org.hibernate.type.UUIDCharType")
    private UUID id;
    private String name;
    private char sex;
    private int weight;
    private int age;
    private double height;
    private LocalDateTime arrivalDate;
    private UUID motherId;
    private UUID fatherId;

    @PrePersist
    public void generateId(){
        this.id = UUID.randomUUID();
    }

    @Override
    public String toString() {
        return "{\n id:"+id+"\n name:"+name+"\n sex:"+sex+"\n weight:"+weight+" \n age:"+age+"\n height:"+height+"\n arrivalDate:"+arrivalDate+"\n motherId:"+motherId+"\n father:"+fatherId+" \n}";
    }

}
