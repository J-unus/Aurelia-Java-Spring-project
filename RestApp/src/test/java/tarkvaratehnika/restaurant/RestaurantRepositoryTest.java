package tarkvaratehnika.restaurant;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RestaurantRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Test
    public void saveToRepository() {
        Restaurant restaurant = new Restaurant();
        restaurant.setName("Restaurant demo");
        entityManager.persist(restaurant);
        entityManager.flush();

        Restaurant found = restaurantRepository.findOne(restaurant.getId());
        assertEquals(found.getName(), restaurant.getName());
    }
}

