package org.zapodot.junit.db.internal;

import org.zapodot.junit.db.common.CompatibilityMode;
import org.zapodot.junit.db.plugin.InitializationPlugin;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class InternalEmbeddedDatabaseCreator extends EmbeddedDatabaseCreatorImpl {

    public InternalEmbeddedDatabaseCreator(final boolean autoCommit, final String name, final Map<String, String> jdbcUrlProperties, final List<InitializationPlugin> initializationPlugins, final JdbcUrlFactory jdbcUrlFactory, final CompatibilityMode compatibilityMode) {
        super(autoCommit, name, jdbcUrlProperties, initializationPlugins, jdbcUrlFactory, compatibilityMode);
    }

    @Override
    public void setupConnection(final String name) throws SQLException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void takeDownConnection() throws SQLException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String getPredefinedName() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
