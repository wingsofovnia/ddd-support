package eu.socialedge.ddd.domain.id;

import javax.persistence.MappedSuperclass;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Math.abs;

/**
 * {@link Integer} based {@link Identifier} implementation with default
 * constructor, that creates an instance with random {@link Integer} value inside
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
