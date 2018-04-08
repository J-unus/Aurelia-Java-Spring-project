package tarkvaratehnika.user;

import lombok.Getter;
import lombok.Setter;
import tarkvaratehnika.restaurant.Restaurant;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue
    long id;
    String userName;
    String password;
    String email;
    String firstName;
    String lastName;

    @OneToMany(mappedBy="user",cascade=CascadeType.ALL)
    List<Restaurant> restaurants;

    /*
    @OneToMany(mappedBy="user")
    RestaurantLikes restaurantLikes;

    @OneToMany(mappedBy="user")
    FoodLikes foodLikes;
    */
}
