package tarkvaratehnika.restaurantLike;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantLikeRepository extends CrudRepository<RestaurantLike, Long> {
    @Override
    public List<RestaurantLike> findAll();
}
