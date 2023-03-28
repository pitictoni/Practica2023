package ro.dorobantiu.gradis.repositories;

import org.springframework.data.repository.CrudRepository;
import ro.dorobantiu.gradis.entities.Author;

import java.util.Collection;

public interface AuthorRepository extends CrudRepository<Author, String> {
    Collection<Author> findByName(String name);

    Author findOneByName(String floreaAdrian);
}
