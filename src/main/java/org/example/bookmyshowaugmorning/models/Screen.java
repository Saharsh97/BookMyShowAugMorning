package org.example.bookmyshowaugmorning.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.bookmyshowaugmorning.models.enums.Feature;

import java.util.List;

@Getter
@Setter
@Entity
public class Screen extends BaseModel{
    private String name;

    @OneToMany
    private List<Seat> seats;

    @Enumerated
    @ElementCollection
    private List<Feature> features;
    // automatically mapping table is made for you.

    // feature Table
    // id   |   enum
    // 1    |   DOLBY
    // 2    | THREE_D
    // 3    | FOUR_K

    // screenId featureId
    // 1        1
    // 1        2
    // 10       1
    // 10       2
    // 10       3
    // 11       3
}

// Class to class -> normal cardinalities
// Class to enum -> always ManyToOne -> enumerated
// Class to List of enums -> always ManyToMany -> enumerated and collection
