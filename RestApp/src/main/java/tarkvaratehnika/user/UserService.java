package tarkvaratehnika.user;

import java.util.List;

import org.springframework.stereotype.Service;

import tarkvaratehnika.car.Car;
import tarkvaratehnika.pen.Pen;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    User addUser(User user) {
        // here you can do some validations etc before saving the user
        //user.setCar(new Car());
        return userRepository.save(user);
    }

    User addUserPen(Pen pen, User user) {
        // here you can do some validations etc before saving the user
        List pens = user.getPens();
        pens.add(pen);
        pen.setUser(user);
        user.setPens(pens);
        return userRepository.save(user);
    }

    List<User> getAllUsers() {
        return userRepository.findAll();
    }

    User getUserById(long userId) {
        return userRepository.findOne(userId);
    }
}
