package tarkvaratehnika.restaurant;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import tarkvaratehnika.foodCategory.FoodCategory;
import tarkvaratehnika.restaurantLike.RestaurantLike;
import tarkvaratehnika.user.User;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Getter
@Setter
public class Restaurant {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String weekdays;
    private String weekend;
    private String phoneNumber;
    private String location;
    private String description;
    private Timestamp createdTs = new Timestamp(System.currentTimeMillis());
    private Timestamp modifiedTs = new Timestamp(System.currentTimeMillis());

    @JsonBackReference
    @ManyToOne()
    private User user;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<FoodCategory> foodCategories;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<RestaurantLike> restaurantLikes;
}
