package tarkvaratehnika.food;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import tarkvaratehnika.comment.Comment;
import tarkvaratehnika.foodCategory.FoodCategory;
import tarkvaratehnika.foodLike.FoodLike;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Getter
@Setter

public class Food {
    @Id
    @GeneratedValue
    private long id;
    private String title;
    private String description;
    private String image;
    private double price;
    private Timestamp created_ts = new Timestamp(System.currentTimeMillis());
    private Timestamp modified_ts = new Timestamp(System.currentTimeMillis());

    @JsonBackReference
    @ManyToOne()
    private FoodCategory foodCategory;

    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL)
    private List<FoodLike> foodLikes;

    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL)
    private List<Comment> comments;
}
