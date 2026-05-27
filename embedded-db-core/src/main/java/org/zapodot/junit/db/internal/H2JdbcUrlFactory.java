package org.zapodot.junit.db.internal;

import org.zapodot.junit.db.common.CompatibilityMode;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author zapodot
 */
public class H2JdbcUrlFactory implements JdbcUrlFactory {

    public static final String PROP_MODE = "MODE";

    public static final String PROP_INIT_SQL = "INIT";

    static final String H2_IN_MEMORY_JDBC_URL_PREFIX = "jdbc:h2:mem:";

    static Map<String, String> filterInitProperties(final Map<String, String> jdbcUrlProperties) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private String buildWithNameAndProperties(final String name, final Map<String, String> properties) {
        if (name == null) {
            throw new NullPointerException("The value of the \"name\" parameter can not be null");
        }
        return new StringBuilder(H2_IN_MEMORY_JDBC_URL_PREFIX).append(name).append(createJdbcUrlParameterString(properties)).toString();
    }

    @Override
    public String connectionUrlForInitialization(final String name, final Map<String, String> properties) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String connectionUrl(final String name, final Map<String, String> properties) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Map<String, String> compatibilityModeParam(final CompatibilityMode compatibilityMode) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
