package eu.socialedge.ddd.domain.id;

import javax.persistence.MappedSuperclass;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Math.abs;

/**
 * A default implementation of {@link Identifier} with random
 * positive Long used as internal default value.
 */
@MappedSuperclass
public class LongIdentifier extends Identifier<Long> {

    public LongIdentifier() {
        super(abs(ThreadLocalRandom.current().nextLong()));
    }

    public LongIdentifier(Long value) {
        super(value);
    }
}
