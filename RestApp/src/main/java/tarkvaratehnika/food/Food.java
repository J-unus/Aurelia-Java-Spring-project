package tarkvaratehnika.food;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import tarkvaratehnika.foodCategory.FoodCategory;
import tarkvaratehnika.foodLike.FoodLike;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter

public class Food {

    @Id
    @GeneratedValue
    long id;
    String description;
    String image;
    double price;
    Set<FoodLike> foodLikes;

    @JsonBackReference
    @ManyToOne()
    FoodCategory foodCategory;


    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<FoodLike> getfoodLikes() {
        return foodLikes;
    }

    public void setFoodLikes(Set<FoodLike> foodLikes) {
        this.foodLikes = foodLikes;
    }
}
