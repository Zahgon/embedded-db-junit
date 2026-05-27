package org.zapodot.junit.db.internal;

import org.slf4j.LoggerFactory;
import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.logging.Logger;

/**
 * A simple DataSource implementation that simply wraps a single Connection
 * Needs to be public to be used by ByteBuddy. Part of internal api, so it may be changed or removed without prior warning
 */
public class EmbeddedDataSource implements DataSource {

    static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(EmbeddedDataSource.class);

    private final Connection connection;

    private EmbeddedDataSource(final Connection connection) {
        this.connection = connection;
    }

    public static EmbeddedDataSource create(final Connection connection) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Connection getConnection() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Connection getConnection(final String username, final String password) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public PrintWriter getLogWriter() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void setLogWriter(final PrintWriter out) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void setLoginTimeout(final int seconds) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int getLoginTimeout() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Logger getParentLogger() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public <T> T unwrap(final Class<T> iface) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean isWrapperFor(final Class<?> iface) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
