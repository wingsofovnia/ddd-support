package eu.socialedge.ddd.domain.id;

import java.io.Serializable;

/**
 * Provides custom generation strategies for the internal values
 * of {@link Identifier}.
 *
 * @param <T> internal identifier's type
 */
public abstract class GeneratableIdentifier<T extends Serializable> extends Identifier<T> {

    public GeneratableIdentifier() {
        this.value = generate();
    }

    public GeneratableIdentifier(T value) {
        super(value);
    }

    protected abstract T generate();
}
