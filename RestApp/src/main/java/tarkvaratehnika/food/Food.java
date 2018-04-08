package tarkvaratehnika.food;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import tarkvaratehnika.foodCategory.FoodCategory;
import tarkvaratehnika.restaurant.Restaurant;

@Entity
@Getter
@Setter

public class Food {

    @Id
    @GeneratedValue
    long id;
    String description;
    String image;
    double price;

    @ManyToOne()
    FoodCategory foodCategory;
/*
    @OneToOne(mappedBy="food")
    FoodLikes foodLikes;
*/
}
