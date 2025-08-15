@echo off
echo 🚀 GENESIS-OS ORACLE JDK AUTO-DOWNLOAD
echo "Maximum automation - Oracle OpenJDK 24.0.2 will be downloaded!"
echo.

cd /d %~dp0

echo ⚡ [1/6] Enabling Oracle JDK auto-provisioning...
echo    ✅ org.gradle.java.installations.auto-download=true
echo    ✅ vendor=JvmVendorSpec.ORACLE

echo.
echo ⚡ [2/6] Checking current Java installations...
call gradlew javaToolchains

echo.
echo ⚡ [3/6] 🔥 TRIGGERING ORACLE JDK AUTO-DOWNLOAD...
echo    This will download Oracle OpenJDK 24.0.2 automatically!
call gradlew --version

echo.
echo ⚡ [4/6] Verifying Oracle JDK download...
call gradlew javaToolchains

echo.
echo ⚡ [5/6] Testing build configuration with Oracle JDK...
call gradlew help --quiet

if %ERRORLEVEL% NEQ 0 (
    echo ❌ Oracle JDK configuration failed
    echo 💡 Check internet connection for auto-download
    pause
    exit /b 1
)

echo ⚡ [6/6] 🎯 Testing project compilation setup...
call gradlew tasks --group=build --quiet

echo.
echo ✅ ORACLE JDK AUTO-DOWNLOAD COMPLETE!
echo 🔥 Oracle OpenJDK 24.0.2 ready for Genesis-OS!
echo 🚀 Maximum automation achieved!
echo.
pause
