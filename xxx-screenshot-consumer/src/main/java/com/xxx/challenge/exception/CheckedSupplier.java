package com.xxx.challenge.exception;

import java.util.Objects;
import java.util.function.Supplier;

/**
 * Created by vinodjagwani on 11/27/18.
 */

public interface CheckedSupplier<T> extends Supplier<T> {


    static <T, E extends Throwable> T rethrow(final Throwable error) throws E {
        Objects.requireNonNull(error, "Error cannot be null");
        if (error.getCause() == null) {
            throw (E) error;
        } else {
            throw (E) error.getCause();
        }
    }

    T provide() throws Exception;

    @Override
    default T get() {
        try {
            return provide();
        } catch (final Exception e) {
            return rethrow(e);
        }
    }

}
