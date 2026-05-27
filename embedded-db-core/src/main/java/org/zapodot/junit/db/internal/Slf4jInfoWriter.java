package org.zapodot.junit.db.internal;

import java.io.Writer;

public class Slf4jInfoWriter extends Writer {

    private final org.slf4j.Logger logger;

    public Slf4jInfoWriter(final org.slf4j.Logger logger) {
        if (logger == null) {
            throw new IllegalArgumentException("No logger was provided");
        }
        this.logger = logger;
    }

    @SuppressWarnings("squid:S2629")
    @Override
    public void write(final char[] cbuf, final int off, final int len) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void flush() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void close() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
