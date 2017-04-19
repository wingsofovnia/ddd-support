package eu.socialedge.ddd.domain.id;

import javax.persistence.MappedSuperclass;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Math.abs;

/**
 * {@link Long} based {@link Identifier} implementation with default
 * constructor, that creates an instance with random {@link Long} value inside
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
