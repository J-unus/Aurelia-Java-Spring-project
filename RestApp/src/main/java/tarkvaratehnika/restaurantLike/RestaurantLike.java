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
import java.sql.Timestamp;

@Entity
@Getter
@Setter
public class RestaurantLike {
    @Id
    @GeneratedValue
    private long id;
    private boolean rating;
    private Timestamp created_ts = new Timestamp(System.currentTimeMillis());

    @JsonIgnore
    @ManyToOne()
    private User user;

    @JsonIgnore
    @ManyToOne()
    private Restaurant restaurant;
}
