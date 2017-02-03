package eu.socialedge.ddd.infrastructure.persistence.jpa;

import eu.socialedge.ddd.domain.AggregateRoot;
import eu.socialedge.ddd.domain.Identifier;
import eu.socialedge.ddd.domain.repository.Repository;
import eu.socialedge.ddd.domain.repository.RepositoryException;
import lombok.val;
import org.apache.commons.lang3.Validate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Spring Data Jpa implementation of {@link Repository} for
 * given {@link AggregateRoot}.
 *
 * <p>
 * To enable Spring Data Jpa for you custom domain-specific interface
 * of {@link Repository} it's enough to extend this interface and
 * mark in as a {@link org.springframework.stereotype.Repository}.
 *
 * <p>
 * Resulted interface will support all features of Spring Data Jpa
 * like custom queries, since it extends spring-specific
 * {@link org.springframework.data.repository.Repository} too.
 *
 * @param <ID> concrete {@link Identifier} type implementation
 * @param <T> {@link AggregateRoot} type implementation
 */
@NoRepositoryBean
@Transactional(readOnly = true)
public interface SpringRepository<ID extends Identifier<?>, T extends AggregateRoot<ID>>
        extends Repository<ID, T>, JpaRepository<T, ID> {

    @Override
    default boolean contains(ID id) {
        Validate.notNull(id, "Identifier cannot be null");

        return exec(() -> exists(id));
    }

    @Override
    default long size() {
        return exec(() -> count());
    }

    @Override
    default boolean isEmpty() {
        return size() == 0L;
    }

    @Override
    default Optional<T> get(ID id) {
        Validate.notNull(id, "Identifier cannot be null");

        return Optional.ofNullable(exec(() -> findOne(id)));
    }

    @Override
    default Collection<T> list() {
        val elementsIterable = exec(() -> findAll());

        return StreamSupport.stream(elementsIterable.spliterator(), false)
            .collect(Collectors.toList());
    }

    static <E> E exec(Supplier<E> func) {
        try {
            return func.get();
        } catch (Exception e) {
            throw new RepositoryException(e);
        }
    }

    static void exec(Runnable func) {
        try {
            func.run();
        } catch (Exception e) {
            throw new RepositoryException(e);
        }
    }
}
