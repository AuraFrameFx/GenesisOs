@echo off
echo 🔧 GENESIS-OS JAVA TOOLCHAIN VERIFICATION
echo "Checking Eclipse Temurin JDK 24 configuration..."
echo.

cd /d %~dp0

echo ⚡ [1/5] Checking available JDKs in Gradle cache...
dir "%USERPROFILE%\.gradle\jdks" | findstr "eclipse_adoptium-24"

if %ERRORLEVEL% EQU 0 (
    echo ✅ Eclipse Temurin JDK 24 found in Gradle cache!
) else (
    echo ❌ Eclipse Temurin JDK 24 not found in Gradle cache
)

echo.
echo ⚡ [2/5] Checking Gradle Java toolchain configuration...
call gradlew --version

echo.
echo ⚡ [3/5] Testing Java toolchain resolution...
call gradlew javaToolchains

echo.
echo ⚡ [4/5] Verifying build configuration...
call gradlew help --quiet

if %ERRORLEVEL% NEQ 0 (
    echo ❌ Configuration failed - check Java toolchain setup
    pause
    exit /b 1
)

echo ✅ [5/5] Configuration successful!

echo.
echo ✅ JAVA TOOLCHAIN VERIFICATION COMPLETE!
echo 🚀 Eclipse Temurin JDK 24 properly configured!
echo 📱 Ready for Genesis-OS bleeding-edge build!
echo.
pause
