package org.zapodot.junit.db.internal;

import org.zapodot.junit.db.common.CompatibilityMode;
import java.util.Map;

public interface JdbcUrlFactory {

    String connectionUrlForInitialization(final String name, final Map<String, String> properties);

    String connectionUrl(final String name, final Map<String, String> properties);

    Map<String, String> compatibilityModeParam(final CompatibilityMode compatibilityMode);

    default String createJdbcUrlParameterString(final Map<String, String> properties) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
