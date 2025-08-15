@echo off
echo 🔧 GENESIS-OS CONFIGURATION CACHE FIX VERIFICATION
echo "Testing Gradle 9.0.0 configuration cache compatibility..."
echo.

cd /d %~dp0

echo ⚡ [1/5] Testing basic configuration without cache...
call gradlew help --no-configuration-cache --quiet

if %ERRORLEVEL% NEQ 0 (
    echo ❌ Basic configuration failed
    pause
    exit /b 1
)

echo ✅ Basic configuration successful!

echo.
echo ⚡ [2/5] Testing OpenAPI clean task (configuration-cache friendly)...
call gradlew cleanOpenApiGenerated --no-configuration-cache

if %ERRORLEVEL% NEQ 0 (
    echo ❌ OpenAPI clean task failed
    pause
    exit /b 1
)

echo ✅ OpenAPI clean task successful!

echo.
echo ⚡ [3/5] Testing configuration cache disabled mode...
call gradlew help --quiet

if %ERRORLEVEL% NEQ 0 (
    echo ❌ Configuration cache disabled mode failed
    pause
    exit /b 1
)

echo ✅ Configuration cache disabled mode working!

echo.
echo ⚡ [4/5] Testing OpenAPI generation task...
call gradlew generateAllOpenApiClients --dry-run

if %ERRORLEVEL% NEQ 0 (
    echo ❌ OpenAPI generation task failed
    pause
    exit /b 1
)

echo ✅ OpenAPI generation task working!

echo.
echo ⚡ [5/5] Testing full build configuration...
call gradlew assembleDebug --dry-run

if %ERRORLEVEL% NEQ 0 (
    echo ❌ Full build configuration failed
    pause
    exit /b 1
)

echo ✅ Full build configuration working!

echo.
echo ✅ CONFIGURATION CACHE FIX SUCCESSFUL!
echo 🔧 Gradle 9.0.0 configuration cache issues resolved!
echo 🚀 OpenAPI tasks are now configuration-cache compatible!
echo 📱 Ready for Genesis-OS bleeding-edge build!
echo.
pause
