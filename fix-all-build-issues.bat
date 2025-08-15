@echo off
:: ===== GENESIS-OS COMPLETE BUILD FIXER =====
:: Fixes all missing dependencies and build issues

echo.
echo 🔧 GENESIS-OS COMPLETE BUILD FIXER 🔧
echo "Fixing all build dependencies and issues..."
echo.

set GENESIS_PATH=%~dp0
cd /d "%GENESIS_PATH%"

echo 📦 [1/5] Cleaning previous build...
.\gradlew clean

echo 🔄 [2/5] Syncing project dependencies...
.\gradlew --refresh-dependencies

echo 📝 [3/5] Fixing OpenAPI generated TODOs...
if exist "fix-openapi-todos.bat" (
    call ".\fix-openapi-todos.bat"
) else (
    echo ⚠️ TODO fixer not found - skipping
)

echo 🛠️ [4/5] Generating all code with KSP...
.\gradlew kspDebugKotlin

echo ✅ [5/5] Building Genesis-OS...
.\gradlew assembleDebug

if %ERRORLEVEL% EQU 0 (
    echo.
    echo 🎉 BUILD SUCCESS! 🎉
    echo Genesis-OS is ready!
    echo.
) else (
    echo.
    echo ❌ BUILD FAILED - Check errors above
    echo.
)

pause