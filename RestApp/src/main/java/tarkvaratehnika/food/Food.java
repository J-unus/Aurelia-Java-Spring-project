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
    private Timestamp createdTs = new Timestamp(System.currentTimeMillis());
    private Timestamp modifiedTs = new Timestamp(System.currentTimeMillis());

    @JsonBackReference
    @ManyToOne()
    private FoodCategory foodCategory;

    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL)
    private List<FoodLike> foodLikes;

    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL)
    private List<Comment> comments;
}
