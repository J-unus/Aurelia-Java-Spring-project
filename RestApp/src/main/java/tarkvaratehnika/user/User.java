package tarkvaratehnika.user;

import lombok.Getter;
import lombok.Setter;
import tarkvaratehnika.comment.Comment;
import tarkvaratehnika.foodLike.FoodLike;
import tarkvaratehnika.restaurant.Restaurant;
import tarkvaratehnika.restaurantLike.RestaurantLike;

import javax.persistence.*;
import java.sql.Timestamp;
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
    private boolean canAddRestaurant = false;
    private Timestamp createdTs = new Timestamp(System.currentTimeMillis());

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Restaurant> restaurants;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<FoodLike> foodLikes;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<RestaurantLike> restaurantLikes;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Comment> comments;
}
