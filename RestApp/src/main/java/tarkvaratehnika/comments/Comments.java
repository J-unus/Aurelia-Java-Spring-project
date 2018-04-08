package tarkvaratehnika.comments;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import tarkvaratehnika.food.Food;
import tarkvaratehnika.user.User;
/*
@Entity
@Getter
@Setter
*/
public class Comments {
  /*
  @Id
    @GeneratedValue
    long id;
    String content;
    @OneToOne
    User user;
    @OneToOne
    Food food;
    */
}
