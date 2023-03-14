package ro.dorobantiu.gradis.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import ro.dorobantiu.gradis.entities.User;


public interface UserRepository extends PagingAndSortingRepository<User, String>,
        CrudRepository<User, String> {
}