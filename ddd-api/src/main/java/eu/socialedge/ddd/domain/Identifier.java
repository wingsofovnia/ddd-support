package eu.socialedge.ddd.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Represents abstract identity of {@link Entity} objects.
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
@Getter
@Accessors(fluent = true)
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(force = true)
@MappedSuperclass @Access(AccessType.FIELD)
public abstract class Identifier<T extends Serializable> extends ValueObject {

    @GeneratedValue
    @Column(name = "value", nullable = false)
    protected final T value;

    public Identifier(T value) {
        this.value = Objects.requireNonNull(value);
    }

    @JsonValue
    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
