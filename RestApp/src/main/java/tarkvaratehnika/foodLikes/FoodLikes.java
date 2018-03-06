package tarkvaratehnika.foodLikes;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import tarkvaratehnika.user.User;
import tarkvaratehnika.food.Food;

@Entity
@Getter
@Setter

public class FoodLikes {
    @Id
    @GeneratedValue
    long id;
    int rating;
    String openTime;
    int phoneNumber;
    String location;
    @OneToOne()
    User user;
    @OneToOne()
    Food food;


}
