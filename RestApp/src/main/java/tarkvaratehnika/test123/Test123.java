package tarkvaratehnika.test123;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Test123 {
    @Id
    @GeneratedValue
    long id;
    String firstName;
    String lastName;
    int numOfPets;
}
