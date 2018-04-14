package tarkvaratehnika.food;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import tarkvaratehnika.foodCategory.FoodCategory;
import tarkvaratehnika.foodLike.FoodLike;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter

public class Food {
    @Id
    @GeneratedValue
    private long id;
    private String description;
    private String image;
    private double price;

    @JsonBackReference
    @ManyToOne()
    private FoodCategory foodCategory;

    @OneToMany(mappedBy="food",cascade=CascadeType.ALL)
    private List<FoodLike> foodLikes;
}
