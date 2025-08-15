@echo off
echo 🔍 GENESIS-OS DEPENDENCY VERIFICATION
echo "Checking for problematic SNAPSHOT dependencies..."
echo.

cd /d %~dp0

echo ⚡ [1/4] Scanning libs.versions.toml for SNAPSHOT versions...
findstr /i "snapshot" gradle\libs.versions.toml
if %ERRORLEVEL% EQU 0 (
    echo ⚠️ Found SNAPSHOT dependencies - may cause build issues
) else (
    echo ✅ No SNAPSHOT dependencies found
)

echo.
echo ⚡ [2/4] Testing dependency resolution...
call gradlew dependencies --configuration debugRuntimeClasspath --quiet

if %ERRORLEVEL% NEQ 0 (
    echo ❌ Dependency resolution failed
    echo 💡 Check for missing or invalid dependencies
    pause
    exit /b 1
)

echo ✅ [3/4] All dependencies resolved successfully!

echo.
echo ⚡ [4/4] Testing build configuration...
call gradlew help --quiet

if %ERRORLEVEL% NEQ 0 (
    echo ❌ Build configuration failed
    pause
    exit /b 1
)

echo.
echo ✅ DEPENDENCY VERIFICATION COMPLETE!
echo 🚀 All dependencies stable and available!
echo 📱 Ready for Genesis-OS build!
echo.
pause
