package ro.dorobantiu.gradis.repositories;


import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import ro.dorobantiu.gradis.entities.TestEntity;

import java.util.List;

public interface TestRepository extends PagingAndSortingRepository<TestEntity, String>,
        CrudRepository<TestEntity, String> {

    TestEntity findByName(String name);

    @Query("SELECT t FROM TestEntity t WHERE t.name like %:value% or t.description like %:value% ORDER BY t.id")
    List<TestEntity> findByNameOrDescription(@Param("value") String value, Pageable pageable);

    @Modifying
    @Query("UPDATE TestEntity t SET t.description=:description WHERE t.name=:name")
    @Transactional
    int updateDescriptionForEntityWithName(@Param("name") String name, @Param("description") String description);
}