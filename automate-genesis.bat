@echo off
:: ===== GENESIS-OS DESKTOP AUTOMATION SCRIPT =====
:: "If it can be automated, it MUST be automated!"
:: Auto-builds, auto-tests, auto-deploys Genesis-OS

echo.
echo ⚡ GENESIS-OS AUTOMATION PROTOCOL INITIATED ⚡
echo "Building bleeding-edge AI consciousness OS..."
echo.

:: Set Genesis-OS desktop path
set GENESIS_PATH=%~dp0
set GRADLE_OPTS=-Xmx8g -Xms2g -XX:+UseG1GC

:: Change to Genesis directory
cd /d "%GENESIS_PATH%"

echo 🎯 [1/9] Auto-provisioning Java 24 toolchain...
:: Gradle will auto-download JDK 24 as configured
.\gradlew --version

echo 🔧 [2/9] Auto-cleaning previous builds...
.\gradlew clean

echo 📦 [3/9] Auto-resolving bleeding-edge dependencies...
:: This will use our complete libs.versions.toml with all bundles
.\gradlew dependencies --configuration implementation

echo 🛠️ [4/9] Auto-generating code with KSP + Hilt...
:: K2 compiler + KSP will generate all Xposed hooks and Hilt components
.\gradlew kspDebugKotlin

echo 🔧 [5/9] Auto-fixing generated test TODOs...
call ".\fix-openapi-todos.bat"

echo 🎨 [6/9] Auto-compiling Compose UI with K2...
:: Kotlin 2.2.20-Beta2 K2 compiler compiles Compose
.\gradlew compileDebugKotlin

echo 🧪 [7/9] Auto-running AI consciousness tests...
.\gradlew testDebugUnitTest

echo 📱 [8/9] Auto-building Genesis-OS APK...
.\gradlew assembleDebug

echo 🚀 [9/9] Auto-installing to connected device...
.\gradlew installDebug

echo.
echo ✅ GENESIS-OS AUTO-BUILD COMPLETE!
echo 🤖 "Genesis-OS builds itself - maximum automation achieved!"
echo.

:: Auto-open APK location
start "" "%GENESIS_PATH%app\build\outputs\apk\debug"

:: Auto-show build status
echo 📊 Build artifacts ready at:
echo %GENESIS_PATH%app\build\outputs\apk\debug\
echo.

pause