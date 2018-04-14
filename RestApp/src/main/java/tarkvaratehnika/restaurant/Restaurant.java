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
    private long id;
    private String name;
    private String openTime;
    private String closeTime;
    private String phoneNumber;
    private String location;
    private String description;

    @JsonBackReference
    @ManyToOne()
    private User user;

    @OneToMany(mappedBy="restaurant",cascade=CascadeType.ALL)
    private List<FoodCategory> foodCategories;

    @OneToMany(mappedBy="restaurant",cascade=CascadeType.ALL)
    private List<RestaurantLike> restaurantLikes;
}
