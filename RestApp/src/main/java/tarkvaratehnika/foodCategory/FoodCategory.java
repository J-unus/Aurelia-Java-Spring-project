package tarkvaratehnika.foodCategory;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import tarkvaratehnika.food.Food;
import tarkvaratehnika.restaurant.Restaurant;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Getter
@Setter
public class FoodCategory {
    @Id
    @GeneratedValue
    private long id;
    private String categoryName;
    private Timestamp createdTs = new Timestamp(System.currentTimeMillis());
    private Timestamp modifiedTs = new Timestamp(System.currentTimeMillis());

    @JsonBackReference
    @ManyToOne()
    private Restaurant restaurant;

    @OneToMany(mappedBy = "foodCategory", cascade = CascadeType.ALL)
    private List<Food> foods;
}
