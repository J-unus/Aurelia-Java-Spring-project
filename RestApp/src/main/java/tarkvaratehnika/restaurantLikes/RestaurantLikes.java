package tarkvaratehnika.restaurantLikes;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import tarkvaratehnika.user.User;
import tarkvaratehnika.restaurants.Restaurants;

@Entity
@Getter
@Setter

public class RestaurantLikes {
    @Id
    @GeneratedValue
    long id;
    int rating;
    @OneToOne()
    User user;
    @OneToOne()
    Restaurants restaurants;


}