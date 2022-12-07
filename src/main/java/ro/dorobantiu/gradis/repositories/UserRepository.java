package ro.dorobantiu.gradis.repositories;


import org.springframework.data.repository.CrudRepository;
import ro.dorobantiu.gradis.entities.User;


public interface UserRepository extends CrudRepository<User, String> {
}