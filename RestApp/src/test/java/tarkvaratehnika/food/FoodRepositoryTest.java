package tarkvaratehnika.food;

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
public class FoodRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private FoodRepository foodRepository;

    @Test
    public void saveToRepository() {
        Food food = new Food();
        food.setDescription("food name");
        entityManager.persist(food);
        entityManager.flush();

        Food found = foodRepository.findOne(food.getId());
        assertEquals(found.getDescription(), food.getDescription());
    }
}
