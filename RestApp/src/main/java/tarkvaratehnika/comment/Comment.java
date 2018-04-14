package tarkvaratehnika.comment;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import tarkvaratehnika.food.Food;
import tarkvaratehnika.user.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class Comment {

    @Id
    @GeneratedValue
    private long id;
    private String content;

    @JsonIgnore
    @ManyToOne()
    private User user;

    @JsonIgnore
    @ManyToOne()
    private Food food;
}
