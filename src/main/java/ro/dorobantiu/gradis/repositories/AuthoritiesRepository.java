package ro.dorobantiu.gradis.repositories;


import org.springframework.data.repository.CrudRepository;
import ro.dorobantiu.gradis.entities.Authority;
import ro.dorobantiu.gradis.entities.AuthorityId;

public interface AuthoritiesRepository extends CrudRepository<Authority, AuthorityId> {
}