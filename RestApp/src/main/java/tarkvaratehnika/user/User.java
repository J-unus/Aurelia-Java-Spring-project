package tarkvaratehnika.user;

import lombok.Getter;
import lombok.Setter;
import tarkvaratehnika.restaurant.Restaurant;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

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

    @OneToOne(mappedBy="user",cascade=CascadeType.ALL)
    Restaurant restaurant;
    /*
    @OneToMany(mappedBy="user")
    RestaurantLikes restaurantLikes;

    @OneToMany(mappedBy="user")
    FoodLikes foodLikes;
    */
}
