package tarkvaratehnika.user;

import lombok.Getter;
import lombok.Setter;
import tarkvaratehnika.foodLike.FoodLike;
import tarkvaratehnika.restaurant.Restaurant;
import tarkvaratehnika.restaurantLike.RestaurantLike;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue
    private long id;
    private String userName;
    private String password;
    private String email;
    private String firstName;
    private String lastName;

    @OneToMany(mappedBy="user",cascade=CascadeType.ALL)
    private List<Restaurant> restaurants;

    @OneToMany(mappedBy="user",cascade=CascadeType.ALL)
    private List<FoodLike> foodLikes;

    @OneToMany(mappedBy="user",cascade=CascadeType.ALL)
    private List<RestaurantLike> restaurantLikes;
}
