package org.example.bookmyshowaugmorning.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.bookmyshowaugmorning.models.enums.TheatreStatus;

import java.util.List;

@Getter
@Setter
@Entity
public class Theatre extends BaseModel{
    private String name;

    @OneToMany
    private List<Screen> screens;

    @Enumerated(value = EnumType.STRING) // takes care of making an Enum table automatically
    private TheatreStatus theatreStatus;

    @ManyToOne
    private Region region;
}
