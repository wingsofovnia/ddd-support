package eu.socialedge.ddd.infrastructure.persistence.jpa;

import eu.socialedge.ddd.domain.AggregateRoot;
import eu.socialedge.ddd.domain.id.Identifier;
import eu.socialedge.ddd.domain.repository.MutableRepository;
import lombok.val;
import org.apache.commons.lang3.Validate;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import static eu.socialedge.ddd.infrastructure.persistence.jpa.SpringRepository.exec;

/**
 * A Spring Data JPA based {@link MutableRepository} implementation
 *
 * @param <ID> concrete {@link Identifier} type implementation
 * @param <T> {@link AggregateRoot} type implementation
 */
@NoRepositoryBean
@Transactional(readOnly = true)
public interface SpringMutableRepository<ID extends Identifier<?>, T extends AggregateRoot<ID>>
        extends MutableRepository<ID, T>, SpringExpandableRepository<ID, T> {

    @Override
    @Transactional
    default boolean remove(ID id) {
        Validate.notNull(id, "Identifier cannot be null");

        val entityOpt = get(id);

        if (!entityOpt.isPresent())
            return false;

        val entity = entityOpt.get();
        exec(() -> delete(entity));

        return true;
    }

    @Override
    @Transactional
    default void remove(Iterable<ID> entityIds) {
        Validate.notNull(entityIds, "Identifier collection cannot be null");

        entityIds.forEach(entityId -> {
            exec(() -> delete(entityId));
        });
    }

    @Override
    @Transactional
    default void clear() {
        exec(() -> deleteAll());
    }
}
