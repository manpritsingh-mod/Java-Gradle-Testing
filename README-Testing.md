# Testing Strategy with JavaGradleScript

This project uses a comprehensive testing strategy with different test profiles managed through the `JavaGradleScript.groovy` shared library.

## Test Profiles

### 1. Smoke Tests
- **Purpose**: Quick validation that basic functionality works
- **Duration**: ~5-10 minutes
- **Command**: `./gradlew test -Psmoke`
- **When to run**: After every commit, before merging PRs

### 2. Sanity Tests
- **Purpose**: Verify core features and main user workflows
- **Duration**: ~10-15 minutes
- **Command**: `./gradlew test -Psanity`
- **When to run**: Before releases, after major changes

### 3. Regression Tests
- **Purpose**: Comprehensive testing to ensure no functionality breaks
- **Duration**: ~30-60 minutes
- **Command**: `./gradlew test -Pregression`
- **When to run**: Before major releases, weekly scheduled runs

## Using JavaGradleScript in Jenkins

### Basic Commands

```groovy
// Build the project
sh JavaGradleScript.buildCommand()

// Run unit tests
sh JavaGradleScript.testCommand('junit5')

// Run code quality checks
sh JavaGradleScript.lintCommand('checkstyle')

// Install dependencies
sh JavaGradleScript.installDependenciesCommand()

// Run different test profiles
sh JavaGradleScript.smokeTestCommand()
sh JavaGradleScript.sanityTestCommand()
sh JavaGradleScript.regressionTestCommand()
```

### Pipeline Integration

The `ci-config.yaml` file controls which tests run in your pipeline:

```yaml
runSmokeTests: true      # Always run smoke tests
runSanityTests: true     # Run sanity tests
runRegressionTests: false # Only run regression tests when explicitly enabled
```

## Test Organization

Tests are organized by profile in separate packages:

```
src/test/java/com/example/
├── smoke/           # Quick smoke tests
├── sanity/          # Core feature tests
└── regression/      # Comprehensive regression tests
```

## Local Development

### Run all tests
```bash
./gradlew test
```

### Run specific test profiles
```bash
# Smoke tests only
./gradlew test -Psmoke

# Sanity tests only
./gradlew test -Psanity

# Regression tests only
./gradlew test -Pregression
```

### Run tests with coverage
```bash
./gradlew test jacocoTestReport
```

## CI/CD Configuration

The pipeline automatically:
1. Runs smoke tests on every commit
2. Runs sanity tests before merging
3. Runs regression tests on scheduled builds or manual triggers
4. Publishes test results and coverage reports
5. Sends notifications based on `ci-config.yaml` settings

## Best Practices

1. **Smoke Tests**: Keep them fast and focused on critical paths
2. **Sanity Tests**: Cover main user workflows and core features
3. **Regression Tests**: Be comprehensive but consider execution time
4. **Test Naming**: Use descriptive names that explain what's being tested
5. **Tags**: Use JUnit 5 tags to categorize tests properly

## Troubleshooting

### Tests not running with profile
- Ensure test classes are in the correct package structure
- Check that test class names match the include patterns in `build.gradle`
- Verify JUnit 5 tags are properly applied

### Pipeline failures
- Check `ci-config.yaml` for correct configuration
- Verify `JavaGradleScript.groovy` is available in your shared library
- Ensure Gradle wrapper permissions are set correctly