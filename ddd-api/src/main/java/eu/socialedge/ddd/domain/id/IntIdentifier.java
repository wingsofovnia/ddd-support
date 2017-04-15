package eu.socialedge.ddd.domain.id;

import javax.persistence.MappedSuperclass;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Math.abs;

/**
 * A default implementation of {@link Identifier} with random
 * positive Integer used as internal default value.
 */
@MappedSuperclass
public class IntIdentifier extends Identifier<Integer> {

    public IntIdentifier() {
        super(abs(ThreadLocalRandom.current().nextInt()));
    }

    public IntIdentifier(Integer value) {
        super(value);
    }
}
