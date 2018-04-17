package tarkvaratehnika.restaurantLike;

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
public class RestaurantLikeRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private RestaurantLikeRepository restaurantLikeRepository;

    @Test
    public void saveToRepository() {
        RestaurantLike restaurantLike = new RestaurantLike();
        restaurantLike.setRating(false);
        entityManager.persist(restaurantLike);
        entityManager.flush();

        RestaurantLike found = restaurantLikeRepository.findOne(restaurantLike.getId());
        assertEquals(found.isRating(), restaurantLike.isRating());
    }
}
