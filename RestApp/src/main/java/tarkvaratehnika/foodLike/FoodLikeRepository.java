package tarkvaratehnika.foodLike;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodLikeRepository extends CrudRepository<FoodLike, Long> {
    @Override
    public List<FoodLike> findAll();
}
