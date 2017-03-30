package eu.socialedge.ddd.domain.id;

import lombok.NoArgsConstructor;

import java.util.concurrent.ThreadLocalRandom;

/**
 * A default implementation of {@link GeneratableIdentifier} with Double used
 * as internal value.
 */
@NoArgsConstructor
public abstract class DoubleIdentifier extends GeneratableIdentifier<Double> {

    private static final Double GEN_RAND_MIN_VALUE = 1D;
    private static final Double GEN_RAND_MAX_VALUE = Double.MAX_VALUE;

    public DoubleIdentifier(Double value) {
        super(value);
    }

    @Override
    protected Double generate() {
        return ThreadLocalRandom.current().nextDouble(GEN_RAND_MIN_VALUE, GEN_RAND_MAX_VALUE);
    }
}
