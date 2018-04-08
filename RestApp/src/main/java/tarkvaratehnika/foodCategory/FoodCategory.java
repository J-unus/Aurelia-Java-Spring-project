package tarkvaratehnika.foodCategory;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import tarkvaratehnika.food.Food;
import tarkvaratehnika.restaurant.Restaurant;

import java.util.List;

@Entity
@Getter
@Setter

public class FoodCategory {

    @Id
    @GeneratedValue
    long id;
    String categoryName;

    @ManyToOne()
    Restaurant restaurant;

    @OneToMany(mappedBy="foodCategory",cascade=CascadeType.ALL)
    List<Food> food;

}
