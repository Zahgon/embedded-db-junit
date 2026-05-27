package org.zapodot.junit.db.internal;

import org.zapodot.junit.db.common.CompatibilityMode;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class HyperSqlJdbcUrlFactory implements JdbcUrlFactory {

    public static final String HSQLDB_MEM_URL = "jdbc:hsqldb:mem:";

    private static final String ENABLED_SETTING = Boolean.TRUE.toString();

    static final String SQL_SYNTAX_MSSQLSERVER = "sql.syntax_mss";

    static final String SQL_SYNTAX_DB2 = "sql.syntax_db2";

    static final String SQL_SYNTAX_ORACLE = "sql.syntax_ora";

    static final String SQL_SYNTAX_MYSQL = "sql.syntax_mys";

    static final String SQL_SYNTAX_POSTGRESQL = "sql.syntax_pgs";

    private static final String DATABASE_CREATE_SETTING = "create";

    private static final String DATABASE_SHUTDOWN_WHEN_CLOSED = "shutdown";

    @Override
    public String connectionUrlForInitialization(final String name, final Map<String, String> properties) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String connectionUrl(final String name, final Map<String, String> properties) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private Map<String, String> addCreateProperty(final Map<String, String> properties) {
        final Map<String, String> props = new LinkedHashMap<>();
        props.putAll(properties);
        props.put(DATABASE_CREATE_SETTING, ENABLED_SETTING);
        props.put(DATABASE_SHUTDOWN_WHEN_CLOSED, ENABLED_SETTING);
        return props;
    }

    @Override
    public Map<String, String> compatibilityModeParam(final CompatibilityMode compatibilityMode) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private Map<String, String> createEnabledSettingMap(final String property) {
        return Collections.singletonMap(property, ENABLED_SETTING);
    }
}
