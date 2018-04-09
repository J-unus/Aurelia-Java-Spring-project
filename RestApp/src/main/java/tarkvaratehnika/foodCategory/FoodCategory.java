package tarkvaratehnika.foodCategory;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import tarkvaratehnika.food.Food;
import tarkvaratehnika.restaurant.Restaurant;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter

public class FoodCategory {

    @Id
    @GeneratedValue
    long id;
    String categoryName;

    @JsonBackReference
    @ManyToOne()
    Restaurant restaurant;

    @OneToMany(mappedBy="foodCategory",cascade=CascadeType.ALL)
    List<Food> foods;

}
