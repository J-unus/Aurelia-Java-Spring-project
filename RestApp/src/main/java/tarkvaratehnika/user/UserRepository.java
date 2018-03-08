package tarkvaratehnika.user;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
    User save(User saved);
    @Override
    public List<User> findAll();

}