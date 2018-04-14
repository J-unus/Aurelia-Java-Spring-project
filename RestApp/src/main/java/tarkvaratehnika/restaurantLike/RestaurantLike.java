package tarkvaratehnika.restaurantLike;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import tarkvaratehnika.restaurant.Restaurant;
import tarkvaratehnika.user.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class RestaurantLike {
    @Id
    @GeneratedValue
    long id;
    boolean rating;

    @JsonIgnore
    @ManyToOne()
    User user;

    @JsonIgnore
    @ManyToOne()
    Restaurant restaurant;
}