package tarkvaratehnika.restaurantLike;

import org.springframework.stereotype.Service;
import tarkvaratehnika.restaurant.Restaurant;
import tarkvaratehnika.user.User;

import java.util.List;

@Service
public class RestaurantLikeService {

    private RestaurantLikeRepository restaurantLikeRepository;

    public RestaurantLikeService(RestaurantLikeRepository restaurantLikeRepository) {
        this.restaurantLikeRepository = restaurantLikeRepository;
    }

    public RestaurantLike addRestaurantLike(RestaurantLike restaurantLike, User user, Restaurant restaurant) {
        List<RestaurantLike> userLikeList = user.getRestaurantLikes();
        List<RestaurantLike> restaurantLikeList = restaurant.getRestaurantLikes();
        List<RestaurantLike> repoRestaurantLikes = restaurantLikeRepository.findAll();
        boolean flag = false;
        for (int i = 0; i < repoRestaurantLikes.size(); i++) {
            RestaurantLike repoRestaurantLike = repoRestaurantLikes.get(i);
            if (repoRestaurantLike.getUser() == user && repoRestaurantLike.getRestaurant() == restaurant) {
                restaurantLikeRepository.delete(repoRestaurantLike);
                if (restaurantLike.isRating() != repoRestaurantLike.isRating()) {
                    userLikeList.add(restaurantLike);
                    restaurantLikeList.add(restaurantLike);
                    restaurantLike.setUser(user);
                    restaurantLike.setRestaurant(restaurant);
                    return restaurantLikeRepository.save(restaurantLike);
                }
                flag = true;
                break;
            }
        }
        if (!flag) {
            userLikeList.add(restaurantLike);
            restaurantLikeList.add(restaurantLike);
            restaurantLike.setUser(user);
            restaurantLike.setRestaurant(restaurant);
            return restaurantLikeRepository.save(restaurantLike);
        }
        return null;
    }

    public List<RestaurantLike> getAllRestaurantLikes() {
        return restaurantLikeRepository.findAll();
    }

    public RestaurantLike getRestaurantLikeById(long restaurantLikeId) {
        return restaurantLikeRepository.findOne(restaurantLikeId);
    }
}
