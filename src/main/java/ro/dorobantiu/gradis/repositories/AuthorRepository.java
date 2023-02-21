package ro.dorobantiu.gradis.repositories;

import org.springframework.data.repository.CrudRepository;
import ro.dorobantiu.gradis.entities.Author;

public interface AuthorRepository extends CrudRepository<Author, String> {
}
