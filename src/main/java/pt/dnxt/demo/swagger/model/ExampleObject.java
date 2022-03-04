package pt.dnxt.demo.swagger.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@NoArgsConstructor
@Entity
@Getter
@Table(name = "example_object")
public class ExampleObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private UUID uuid;

    private String description;

    public ExampleObject(String description){
        this.description = description;
        this.uuid = UUID.randomUUID();
    }

    public void updateDescription(String description){
        this.description = description;
        this.uuid = UUID.randomUUID();
    }

    public void updateUuid(){
        this.uuid = UUID.randomUUID();
    }
}
