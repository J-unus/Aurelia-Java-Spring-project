package tarkvaratehnika.foodLike;

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
public class FoodLikeRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private FoodLikeRepository foodLikeRepository;

    @Test
    public void saveToRepository() {
        FoodLike foodLike = new FoodLike();
        foodLike.setRating(false);
        entityManager.persist(foodLike);
        entityManager.flush();

        FoodLike found = foodLikeRepository.findOne(foodLike.getId());
        assertEquals(found.isRating(), foodLike.isRating());
    }
}
