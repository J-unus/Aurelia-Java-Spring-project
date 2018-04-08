package tarkvaratehnika.user;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import tarkvaratehnika.car.Car;
import tarkvaratehnika.pen.Pen;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue
    long id;
    String firstName;
    String lastName;
    int numOfPets;


    @OneToOne(mappedBy="user",  // välja nimi Car klassis
            cascade=CascadeType.ALL)
    Car car;


    @OneToMany(mappedBy="user", cascade=CascadeType.ALL)
    List<Pen> pens;
}
