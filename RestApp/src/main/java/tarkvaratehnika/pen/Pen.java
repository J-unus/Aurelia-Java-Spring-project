package tarkvaratehnika.pen;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import tarkvaratehnika.user.User;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Pen {
    @Id
    @GeneratedValue
    long id;
    String color;

    @JsonBackReference
    @ManyToOne
    User user;
}
