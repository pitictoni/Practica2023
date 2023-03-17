package ro.dorobantiu.gradis.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ro.dorobantiu.gradis.entities.Author;
import ro.dorobantiu.gradis.entities.TestEntity;

import java.util.Collection;
import java.util.List;

public interface AuthorRepository extends CrudRepository<Author, String> {

    Author findOneByName(String name);

    Collection<Author> findByName(String name);
}

