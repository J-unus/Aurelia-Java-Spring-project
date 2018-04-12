package tarkvaratehnika.foodCategory;


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
public class FoodCategoryRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private FoodCategoryRepository FoodCategoryRepository;

    @Test
    public void saveToRepository() {
        FoodCategory FoodCategory = new FoodCategory();
        FoodCategory.setCategoryName("Food category name");
        entityManager.persist(FoodCategory);
        entityManager.flush();

        FoodCategory found = FoodCategoryRepository.findOne(FoodCategory.getId());
        assertEquals(found.getCategoryName(), FoodCategory.getCategoryName());
    }
}

