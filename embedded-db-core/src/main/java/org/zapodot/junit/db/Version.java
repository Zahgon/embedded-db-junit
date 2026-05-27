package org.zapodot.junit.db;

import java.io.IOException;
import java.util.Properties;

public class Version {

    static final String PROP_FILE_BUILD_INFO = "build.info.properties";

    static final String PROP_FILE_GIT_PROPERTIES = "git.properties";

    private final String groupId;

    private final String artifactId;

    private final String projectVersion;

    private final String gitBranch;

    private final String gitCommit;

    public Version() {
        this(loadBuildInfoAndGitProperties());
    }

    private Version(Properties properties) {
        this(properties.getProperty("project.groupId"), properties.getProperty("project.artifactId"), properties.getProperty("project.version"), properties.getProperty("git.branch"), properties.getProperty("git.commit.id"));
    }

    private Version(final String groupId, final String artifactId, final String projectVersion, final String gitBranch, final String gitCommit) {
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.projectVersion = projectVersion;
        this.gitBranch = gitBranch;
        this.gitCommit = gitCommit;
    }

    public String getGroupId() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String getArtifactId() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String getVersion() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String getGitBranch() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String getGitCommit() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private static Properties loadBuildInfoAndGitProperties() {
        final Properties properties = loadBuildInfo();
        properties.putAll(loadGitProperties());
        return properties;
    }

    private static Properties loadBuildInfo() {
        return loadPropertiesFile(PROP_FILE_BUILD_INFO);
    }

    private static Properties loadGitProperties() {
        return loadPropertiesFile(PROP_FILE_GIT_PROPERTIES);
    }

    static Properties loadPropertiesFile(final String file) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
