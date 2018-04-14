package tarkvaratehnika.restaurant;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import tarkvaratehnika.foodCategory.FoodCategory;
import tarkvaratehnika.restaurantLike.RestaurantLike;
import tarkvaratehnika.user.User;

import javax.persistence.*;
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
    String closeTime;
    String phoneNumber;
    String location;
    String description;

    @JsonBackReference
    @ManyToOne()
    User user;

    @OneToMany(mappedBy="restaurant",cascade=CascadeType.ALL)
    List<FoodCategory> foodCategories;

    @OneToMany(mappedBy="restaurant",cascade=CascadeType.ALL)
    List<RestaurantLike> restaurantLikes;
}
