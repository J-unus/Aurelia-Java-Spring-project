package tarkvaratehnika.foodCategory;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodCategoryRepository extends CrudRepository<FoodCategory, Long> {
    @Override
    List<FoodCategory> findAll();
    List<FoodCategory> findAllByOrderByCategoryNameAsc();
}
