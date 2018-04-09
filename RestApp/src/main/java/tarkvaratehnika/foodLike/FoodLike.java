package tarkvaratehnika.foodLike;


import lombok.Getter;
import lombok.Setter;
import tarkvaratehnika.food.Food;
import tarkvaratehnika.user.User;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
@Getter
@Setter
public class FoodLike {
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
    @Id
    @ManyToOne
    @JoinColumn(name = "food_id")
    Food food;
    int rating;

    public void setRating(int rating) {
        this.rating = rating;
    }
}
