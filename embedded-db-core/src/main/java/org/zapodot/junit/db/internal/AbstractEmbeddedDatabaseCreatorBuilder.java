package org.zapodot.junit.db.internal;

import org.zapodot.junit.db.common.CompatibilityMode;
import org.zapodot.junit.db.common.EmbeddedDatabaseCreator;
import org.zapodot.junit.db.common.EmbeddedDatabaseCreatorBuilder;
import org.zapodot.junit.db.common.Engine;
import org.zapodot.junit.db.plugin.InitializationPlugin;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;

public abstract class AbstractEmbeddedDatabaseCreatorBuilder<C extends EmbeddedDatabaseCreator> implements EmbeddedDatabaseCreatorBuilder<C> {

    protected final Map<String, String> properties = new LinkedHashMap<>();

    protected final List<InitializationPlugin> initializationPlugins = new LinkedList<>();

    protected String name;

    protected boolean autoCommit = true;

    protected final Engine engine;

    protected CompatibilityMode compatibilityMode = CompatibilityMode.REGULAR;

    public AbstractEmbeddedDatabaseCreatorBuilder(final Engine engine) {
        if (engine == null) {
            throw new IllegalArgumentException("The \"engine\" argument can not be null");
        }
        this.engine = engine;
    }

    @Override
    public AbstractEmbeddedDatabaseCreatorBuilder<C> withName(final String name) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private String normalizeString(final String input) {
        return Optional.ofNullable(input).map(i -> i.replaceAll("\n", "").replaceAll(";", "\\\\;").trim()).orElse(null);
    }

    @Override
    public AbstractEmbeddedDatabaseCreatorBuilder<C> withInitialSql(final String sql) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public AbstractEmbeddedDatabaseCreatorBuilder<C> withInitialSqlFromResource(final String resource) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public AbstractEmbeddedDatabaseCreatorBuilder<C> withInitialSqlFromResource(final String resource, final Charset charset) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public AbstractEmbeddedDatabaseCreatorBuilder<C> withMode(final String mode) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private CompatibilityMode mapToCompatibilityMode(final String mode) {
        if (mode == null) {
            throw new IllegalArgumentException("The \"mode\" argument can not be null");
        }
        return Arrays.stream(CompatibilityMode.values()).filter(c -> c.name().equalsIgnoreCase(mode)).findAny().orElseThrow(() -> new IllegalArgumentException("Could not map mode \"" + mode + "\" to a valid Compatibility mode"));
    }

    @Override
    public AbstractEmbeddedDatabaseCreatorBuilder<C> withMode(final CompatibilityMode compatibilityMode) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public <P extends InitializationPlugin> AbstractEmbeddedDatabaseCreatorBuilder<C> initializedByPlugin(final P plugin) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public AbstractEmbeddedDatabaseCreatorBuilder<C> withProperty(final String property, final String value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public AbstractEmbeddedDatabaseCreatorBuilder<C> withoutAutoCommit() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected Map<String, String> propertiesMap() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected JdbcUrlFactory createJdbcUrlFactory() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
