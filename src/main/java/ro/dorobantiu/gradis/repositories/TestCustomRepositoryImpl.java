package ro.dorobantiu.gradis.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;
import ro.dorobantiu.gradis.entities.TestEntity;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TestCustomRepositoryImpl implements TestCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<TestEntity> findTestEntitiesByListOfNames(List<String> names) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<TestEntity> query = cb.createQuery(TestEntity.class);
        Root<TestEntity> user = query.from(TestEntity.class);

        Path<String> namePath = user.get("name");

        List<Predicate> predicates = new ArrayList<>();
        for (String name : names) {
            predicates.add(cb.like(namePath, name));
        }
        query.select(user).where(cb.or(predicates.toArray(new Predicate[0])));

        return entityManager.createQuery(query).getResultList();
    }
}
