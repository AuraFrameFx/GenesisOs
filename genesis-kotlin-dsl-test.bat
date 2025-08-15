@echo off
echo 🔧 GENESIS-OS KOTLIN DSL FIX VERIFICATION
echo "Testing modern compilerOptions DSL..."
echo.

cd /d %~dp0

echo ⚡ [1/3] Checking Gradle configuration...
call gradlew help --quiet

if %ERRORLEVEL% NEQ 0 (
    echo ❌ Configuration failed - check build.gradle.kts syntax
    pause
    exit /b 1
)

echo ✅ [2/3] Configuration syntax valid!

echo ⚡ [3/3] Testing compile configuration...
call gradlew compileDebugKotlin --dry-run

if %ERRORLEVEL% NEQ 0 (
    echo ❌ Kotlin compilation setup failed
    pause
    exit /b 1
)

echo.
echo ✅ KOTLIN DSL FIX SUCCESSFUL!
echo 🚀 Modern compilerOptions DSL working!
echo 📱 Ready for bleeding-edge build!
echo.
pause
