package org.zapodot.junit.db.plugin;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.MigrationVersion;
import org.flywaydb.core.api.configuration.Configuration;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zapodot.junit.db.internal.EmbeddedDataSource;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.util.Map;

/**
 * Plugin for initializing in-memory database using <a href="https://flywaydb.org">Flyway</a>.
 */
public class FlywayInitializer implements InitializationPlugin {

    private final FluentConfiguration flywayConfiguration;

    private static final Logger LOGGER = LoggerFactory.getLogger(FlywayInitializer.class);

    public static class Builder {

        private final FluentConfiguration fluentConfiguration = Flyway.configure();

        public Builder() {
            fluentConfiguration.encoding(StandardCharsets.UTF_8.name()).target(MigrationVersion.LATEST);
        }

        public Builder withInstalledBy(final String installedBy) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withAllowMixed() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Starting with FlyWay 9 this requires teams
         * @return builder instance
         * @deprecated use #withIgnoreMigrationPatterns instead
         */
        @Deprecated
        public Builder withIgnoreMissingMigrations() {
            return withIgnoreMigrationPatterns("repeatable:missing");
        }

        /**
         * Allows setting the pattern for ignoring migratins
         * @param patterns a <a href="https://flywaydb.org/documentation/configuration/parameters/ignoreMigrationPatterns>valid pattern</a>
         * @return builder instance
         */
        public Builder withIgnoreMigrationPatterns(String... patterns) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withDoNotValidateOnMigrate() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withLocations(final String... locations) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withEncoding(final String encoding) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withSchemas(final String... schemas) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withTable(final String table) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withPlaceholders(final Map<String, String> placeholders) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withPlaceholderPrefix(final String placeholderPrefix) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withPlaceholderSuffix(final String placeholderSuffix) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withTarget(final String targetVersion) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public FlywayInitializer build() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    public static Builder builder() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private FlywayInitializer(final FluentConfiguration flywayConfiguration) {
        this.flywayConfiguration = flywayConfiguration;
    }

    @Override
    public void connectionMade(final String name, final Connection connection) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    Configuration getFlywayConfiguration() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
