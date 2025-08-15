# Genesis OS - Technical Documentation

## Overview

Genesis OS is a cutting-edge Android framework that integrates advanced AI capabilities with a modular architecture. This document provides comprehensive technical details about the project structure, build system, dependencies, and development workflow.

## Architecture

### Project Structure

```
GenesisOs/
├── app/                          # Main Android application
│   ├── src/main/cpp/            # Native C++ components (C++20)
│   └── build.gradle.kts         # Main app build configuration
├── core-module/                 # Core functionality and base components
├── feature-module/              # Feature modules and extensions
├── secure-comm/                 # Secure communication framework (Java 8)
├── collab-canvas/               # Collaborative workspace with native libs
├── colorblendr/                 # Advanced theming and styling
├── datavein-oracle-native/      # Native AI processing components
├── oracle-drive-integration/    # Cloud and storage integration
├── romtools/                    # System-level utilities with native code
├── sandbox-ui/                  # UI components and theming
└── modules-a-f/                 # Additional modular components
```

### Technology Stack

- **Language**: Kotlin 2.2.20-Beta2 with K2 compiler
- **Java Version**: Java 24 (Oracle OpenJDK 24.02) with auto-provisioning
- **Android**: API 24+ with NDK support for C++20
- **UI Framework**: Jetpack Compose with Material 3
- **Dependency Injection**: Hilt + KSP
- **Database**: Room with KTX
- **Network**: Retrofit + OkHttp + Kotlinx Serialization
- **Native Code**: C++20 with CMake 3.22.1
- **Architecture**: ARM64-v8a, ARMv7, x86_64

## Build System

### Gradle Configuration

The project uses Gradle 8.13.0 with the following key features:

1. **Auto-provisioning Toolchains**: Automatic JDK 24 and NDK download
2. **Version Catalog**: Centralized dependency management via `libs.versions.toml`
3. **Multi-module Architecture**: 12+ interconnected modules
4. **OpenAPI Generation**: Automated client generation for 5+ API specifications

### Key Build Tasks

```bash
# Clean and build all modules
./gradlew clean build

# Generate OpenAPI clients
./gradlew generateAllOpenApiClients

# Build native libraries
./gradlew externalNativeBuildDebug

# Run tests
./gradlew test

# Assemble debug APK
./gradlew assembleDebug

# Install on device
./gradlew installDebug
```

### OpenAPI Integration

The project includes automated OpenAPI client generation for:
- Genesis API
- Customization API
- Oracle Drive API
- ROM Tools API
- Sandbox API

Generated clients are automatically included in the build process.

### Native Code (C++20)

Several modules include native components:
- **app**: Core AI processing
- **collab-canvas**: Collaborative rendering
- **romtools**: System utilities
- **datavein-oracle-native**: AI data processing

Native builds use:
- CMake 3.22.1
- C++20 standard
- Android NDK 27.0.12077973
- Ninja build system

## Dependencies

### Core Dependencies

- **Android Jetpack**: Activity, Fragment, Navigation, Lifecycle
- **Compose BOM**: UI toolkit with Material 3
- **Firebase**: Analytics, Crashlytics, Authentication
- **Hilt**: Dependency injection framework
- **Room**: Local database
- **Retrofit**: HTTP client
- **Xposed Framework**: System hooks and modifications

### Development Dependencies

- **KSP**: Kotlin Symbol Processing
- **Timber**: Logging
- **LeakCanary**: Memory leak detection
- **JUnit 5**: Testing framework
- **Espresso**: UI testing

## Development Workflow

### Prerequisites

1. **Java 24**: Auto-provisioned by Gradle toolchain
2. **Android Studio**: Arctic Fox or later
3. **Android SDK**: API 24+ with latest build tools
4. **Git**: Version control

### Setup Instructions

1. Clone the repository:
   ```bash
   git clone https://github.com/AuraFrameFx/GenesisOs.git
   cd GenesisOs
   ```

2. Build the project:
   ```bash
   ./gradlew build
   ```

3. Run on device/emulator:
   ```bash
   ./gradlew installDebug
   ```

### Module Dependencies

The build order follows these dependencies:
1. `secure-comm` (Java 8, standalone)
2. `core-module` (Java 21, base module)
3. `oracle-drive-integration` (Java 21)
4. `colorblendr` (Java 21)
5. `sandbox-ui` (Java 21)
6. `collab-canvas` (Java 21 + C++20)
7. `romtools` (Java 21 + C++20)
8. `app` (Main application, depends on all)

## Build Optimization

### Performance Settings

- **JVM Args**: `-Xmx6g -Xms2g -XX:+UseG1GC`
- **Parallel Builds**: Disabled for stability
- **Configuration Cache**: Disabled for compatibility
- **Build Cache**: Disabled for fresh builds

### Memory Management

The project requires substantial memory due to:
- Multiple modules with native compilation
- OpenAPI code generation
- Large dependency graph
- AI/ML model processing

Minimum recommended system specs:
- 16GB RAM
- 8GB available for Gradle JVM
- Fast SSD storage

## Testing

### Test Structure

- **Unit Tests**: JUnit 5 with Kotlin coroutines testing
- **Integration Tests**: Hilt testing for DI
- **UI Tests**: Compose testing with Espresso
- **Native Tests**: C++ unit tests for native components

### Running Tests

```bash
# Run all unit tests
./gradlew test

# Run Android instrumentation tests
./gradlew connectedAndroidTest

# Run specific module tests
./gradlew :core-module:test
```

## Security

### Features

- End-to-end encryption for communications
- Secure key storage using Android Keystore
- Firebase security rules
- Proguard/R8 code obfuscation
- Runtime application self-protection (RASP)

### Sensitive Data

The project handles:
- Firebase configuration (externalized)
- API keys and secrets (environment variables)
- User authentication tokens
- AI model parameters

## Deployment

### CI/CD Pipeline

GitHub Actions workflow includes:
1. **Code Analysis**: CodeQL security scanning
2. **Dependency Updates**: Dependabot automation
3. **Build Verification**: Multi-architecture builds
4. **Testing**: Automated test execution
5. **APK Generation**: Release artifact creation

### Release Process

1. Version bump in `gradle.properties`
2. Update `CHANGELOG.md`
3. Create release branch
4. Run full test suite
5. Generate signed APK
6. Deploy to distribution channels

## Troubleshooting

### Common Issues

1. **Java Version Conflicts**: Ensure Java 24 toolchain is properly configured
2. **Native Build Failures**: Check NDK installation and CMake version
3. **Memory Issues**: Increase Gradle JVM heap size
4. **OpenAPI Generation**: Verify API specification validity
5. **Module Dependencies**: Ensure proper build order

### Debug Commands

```bash
# Check Java toolchain
./gradlew -q javaToolchains

# Verify dependencies
./gradlew dependencies

# Debug native builds
./gradlew :app:externalNativeBuildDebug --debug

# Clean problematic builds
./gradlew clean --quiet
```

## Contributing

### Code Style

- Kotlin coding conventions
- ktlint for formatting
- Detekt for static analysis
- C++ clang-format for native code

### Pull Request Process

1. Fork the repository
2. Create feature branch
3. Implement changes with tests
4. Run quality checks
5. Submit pull request with detailed description

### Module Development

When adding new modules:
1. Follow existing module structure
2. Update `settings.gradle.kts`
3. Add to dependency graph
4. Include appropriate tests
5. Update documentation

## Performance Metrics

### Build Times

- Clean build: ~15-20 minutes
- Incremental build: ~2-5 minutes
- OpenAPI generation: ~30 seconds
- Native compilation: ~5-10 minutes per module

### APK Metrics

- Debug APK: ~50-80 MB
- Release APK: ~30-50 MB (with ProGuard)
- Native libraries: ~15-25 MB total
- Dex size: ~20-30 MB

## Future Roadmap

### Planned Enhancements

1. **Kotlin Multiplatform**: Expand to iOS and Desktop
2. **AI Model Integration**: On-device ML model deployment
3. **Plugin Architecture**: Dynamic module loading
4. **Performance Optimization**: Build time reduction
5. **Testing Enhancement**: Automated UI testing

### Technology Updates

- Migration to Kotlin 2.x stable
- Compose Multiplatform adoption
- Latest Android API integration
- Enhanced security features

---

For questions or support, please refer to the project's GitHub issues or discussion forums.