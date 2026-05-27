package org.zapodot.junit.db;

import org.junit.jupiter.api.extension.*;
import org.junit.platform.commons.util.AnnotationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zapodot.junit.db.annotations.ConfigurationProperty;
import org.zapodot.junit.db.annotations.EmbeddedDatabase;
import org.zapodot.junit.db.annotations.EmbeddedDatabaseTest;
import org.zapodot.junit.db.common.CompatibilityMode;
import org.zapodot.junit.db.common.EmbeddedDatabaseCreator;
import org.zapodot.junit.db.common.Engine;
import org.zapodot.junit.db.internal.AbstractEmbeddedDatabaseCreatorBuilder;
import org.zapodot.junit.db.internal.InternalEmbeddedDatabaseCreator;
import javax.sql.DataSource;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.Parameter;
import java.sql.Connection;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * A JUnit5 Extension that makes it easy to test JDBC integration code
 */
public class EmbeddedDatabaseExtension implements EmbeddedDatabaseCreator, BeforeEachCallback, AfterEachCallback, TestInstancePostProcessor, ParameterResolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmbeddedDatabaseExtension.class);

    private static final ExtensionContext.Namespace EMBEDDED_DB_EXT = ExtensionContext.Namespace.create("org.zapodot.junit.db");

    private static final String STORE_PROPERTY_DATABASE_CREATOR = "embeddedDatabaseCreator";

    private static final String TEST_INSTANCE = "testInstance";

    private final InternalEmbeddedDatabaseCreator embeddedDatabaseCreator;

    public EmbeddedDatabaseExtension() {
        this(null);
    }

    private EmbeddedDatabaseExtension(final InternalEmbeddedDatabaseCreator embeddedDatabaseCreator) {
        this.embeddedDatabaseCreator = embeddedDatabaseCreator;
    }

    @Override
    public void afterEach(final ExtensionContext context) throws Exception {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void beforeEach(final ExtensionContext context) throws Exception {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void postProcessTestInstance(final Object testInstance, final ExtensionContext context) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean supportsParameter(final ParameterContext parameterContext, final ExtensionContext extensionContext) throws ParameterResolutionException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Object resolveParameter(final ParameterContext parameterContext, final ExtensionContext extensionContext) throws ParameterResolutionException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Connection getConnection() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public DataSource getDataSource() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean isAutoCommit() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String getConnectionJdbcUrl() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private void tryToInjectDataSourceOrConnection(final Object testInstance, final EmbeddedDatabaseCreator embeddedDatabaseCreator) {
        findInjectCandidateFields(testInstance.getClass()).stream().forEach(field -> injectDataSourceOrConnection(testInstance, field, embeddedDatabaseCreator));
    }

    private List<Field> findInjectCandidateFields(final Class type) {
        return AnnotationUtils.findAnnotatedFields(type, EmbeddedDatabase.class, f -> DataSource.class.isAssignableFrom(f.getType()) || Connection.class.isAssignableFrom(f.getType()));
    }

    private void injectDataSourceOrConnection(final Object testInstance, final Field field, final EmbeddedDatabaseCreator embeddedDatabaseCreator) {
        boolean accessibleOriginal = field.canAccess(testInstance);
        field.setAccessible(true);
        try {
            if (DataSource.class.isAssignableFrom(field.getType())) {
                LOGGER.debug("Will inject javax.sql.DataSource to field {}", field.getName());
                field.set(testInstance, embeddedDatabaseCreator.getDataSource());
            } else if (Connection.class.isAssignableFrom(field.getType())) {
                LOGGER.debug("Will inject java.sql.Connection to field {}", field.getName());
                field.set(testInstance, embeddedDatabaseCreator.getConnection());
            }
        } catch (IllegalAccessException | IllegalArgumentException e) {
            throw new IllegalStateException("Could not inject embedded DataSource/Connection to field", e);
        } finally {
            field.setAccessible(accessibleOriginal);
        }
    }

    private String extractNameFromExtensionContext(final ExtensionContext extensionContext) {
        return extensionContext.getTestClass().map(Class::getSimpleName).orElse(extensionContext.getUniqueId());
    }

    private static Optional<InternalEmbeddedDatabaseCreator> tryToCreateFromExtensionContext(final ExtensionContext extensionContext) {
        LOGGER.debug("Constructing DataSource configuration using annotations");
        final Optional<EmbeddedDatabaseTest> dataSourceConfig = findAnnotation(extensionContext.getElement(), EmbeddedDatabaseTest.class);
        if (!dataSourceConfig.isPresent()) {
            LOGGER.warn("No configuration found. There should be an @DataSourceConfig annotation on either the test class or the method");
            return Optional.empty();
        } else {
            final EmbeddedDatabaseTest dataSourceConfigValue = dataSourceConfig.get();
            final Builder builder;
            if (Engine.HSQLDB.equals(dataSourceConfigValue.engine())) {
                builder = Builder.hsqldb();
            } else {
                builder = Builder.h2();
            }
            if (dataSourceConfigValue.name() == null && !dataSourceConfigValue.name().equals("")) {
                builder.withName(dataSourceConfigValue.name());
            }
            if (dataSourceConfigValue.compatibilityMode() == null) {
                builder.withMode(CompatibilityMode.REGULAR);
            } else {
                builder.withMode(dataSourceConfigValue.compatibilityMode());
            }
            final ConfigurationProperty[] properties = dataSourceConfigValue.properties();
            if (properties != null) {
                Arrays.stream(properties).forEach(cp -> builder.withProperty(cp.name(), cp.value()));
            }
            if (!dataSourceConfigValue.autoCommit()) {
                builder.withoutAutoCommit();
            }
            if (dataSourceConfigValue.initialSqls() != null) {
                Arrays.asList(dataSourceConfigValue.initialSqls()).forEach(builder::withInitialSql);
            }
            if (dataSourceConfigValue.initialSqlResources() != null) {
                Arrays.asList(dataSourceConfigValue.initialSqlResources()).forEach(builder::withInitialSqlFromResource);
            }
            return Optional.of(builder.buildInternalEmbeddedDatabaseCreator());
        }
    }

    private static <A extends Annotation> Optional<A> findAnnotation(Optional<? extends AnnotatedElement> element, Class<A> annotationType) {
        return element.flatMap(e -> findAnnotationForElement(annotationType, e));
    }

    private static <A extends Annotation> Optional<A> findAnnotationForElement(final Class<A> annotationType, final AnnotatedElement e) {
        return AnnotationUtils.findAnnotation(e, annotationType);
    }

    public static class Builder extends AbstractEmbeddedDatabaseCreatorBuilder<EmbeddedDatabaseExtension> {

        private Builder(final Engine engine) {
            super(engine);
        }

        /**
         * Creates a builder for the H2 engine
         *
         * @return a builder for creating an {@link EmbeddedDatabaseExtension} that will use the H2 engine
         */
        public static Builder h2() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Creates a builder for the H2 engine
         *
         * @return a builder for creating an {@link EmbeddedDatabaseExtension} that will use the HSQLDB engine
         */
        public static Builder hsqldb() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        InternalEmbeddedDatabaseCreator buildInternalEmbeddedDatabaseCreator() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public EmbeddedDatabaseExtension build() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
}
