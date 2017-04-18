package eu.socialedge.ddd.domain.id;

import javax.persistence.MappedSuperclass;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Math.abs;

/**
 * {@link Double} based {@link Identifier} implementation with default
 * constructor, that creates an instance with random {@link Double} value inside
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
