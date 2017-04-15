package eu.socialedge.ddd.domain.id;

import javax.persistence.MappedSuperclass;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Math.abs;

/**
 * A default implementation of {@link Identifier} with random
 * positive Double used as internal default value.
 */
@MappedSuperclass
public class DoubleIdentifier extends Identifier<Double> {

    public DoubleIdentifier() {
        super(abs(ThreadLocalRandom.current().nextDouble()));
    }

    public DoubleIdentifier(Double value) {
        super(value);
    }
}
