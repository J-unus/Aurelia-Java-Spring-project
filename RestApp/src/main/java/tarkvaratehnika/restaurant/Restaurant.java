package tarkvaratehnika.restaurant;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import tarkvaratehnika.foodCategory.FoodCategory;
import tarkvaratehnika.user.User;

import java.util.List;

@Entity
@Getter
@Setter

public class Restaurant {
    @Id
    @GeneratedValue
    long id;
    String name;
    String openTime;
    int phoneNumber;
    String location;

    @OneToOne()
    User user;

    @OneToMany(mappedBy="restaurant",cascade=CascadeType.ALL)
    List <FoodCategory> foodCategory;


    /*
    @OneToMany(mappedBy="restaurant", cascade=CascadeType.ALL)
    List<Food> food;
    */
}
