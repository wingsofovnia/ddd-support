package eu.socialedge.ddd.domain.id;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import eu.socialedge.ddd.domain.ValueObject;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Objects;

/**
 * Represents abstract identity of {@link eu.socialedge.ddd.domain.Entity} objects.
 * <p>
 * You may want to rename {@link Identifier#value} param in domain-specific
 * {@link Identifier} implementation for JPA mappings. This can be easily done
 * with @AttributeOverride annotation.
 * <p>
 * For example, you may want to annotate your {@code OperatorId extends Identifier}
 * implementation with
 * {@code @AttributeOverride(name = "value", column = @Column(name = "operator_id"))}
 * so {@code operator_id} column will appear in database instead of {@code value}.
 *
 * @param <T> internal identifier's type
 */
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(force = true, access = AccessLevel.PROTECTED) // Required by JPA
@MappedSuperclass @Access(AccessType.FIELD)
public abstract class Identifier<T extends Serializable> implements ValueObject {

    @Column(name = "value", nullable = false)
    protected final T value;

    @JsonCreator
    public Identifier(T value) {
        this.value = Objects.requireNonNull(value);
    }

    public T value() {
        return value;
    }

    @JsonValue
    @Override
    public String toString() {
        return value.toString();
    }
}
