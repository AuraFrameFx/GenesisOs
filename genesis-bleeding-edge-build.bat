@echo off
echo 🩸 GENESIS-OS BLEEDING-EDGE BUILD PROTOCOL 🩸
echo "ALWAYS regenerate - Genesis-OS stays fresh!"
echo.

cd /d %~dp0

set GRADLE_OPTS=-Xmx8g -Xms2g -XX:+UseG1GC
set "START_TIME=%time%"

echo.
echo ================================================
echo 🔥 NUCLEAR OPENAPI REGENERATION PROTOCOL
echo ================================================
echo.

echo 💥 [1/8] NUKING all OpenAPI cache...
call gradlew cleanOpenApiGenerated

echo.
echo 🔥 [2/8] FORCE REGENERATING OpenAPI clients...
call gradlew generateAllOpenApiClients --no-build-cache

echo.
echo ================================================
echo 🚀 GENESIS-OS BUILD CONTINUATION
echo ================================================
echo.

echo ⚡ [3/8] Dependency resolution...
call gradlew dependencies --configuration compileClasspath --quiet

echo ⚡ [4/8] KSP code generation...
call gradlew kspDebugKotlin

echo ⚡ [5/8] Auto-fixing OpenAPI TODOs...
if exist "fix-openapi-todos.bat" (
    call fix-openapi-todos.bat
) else (
    echo ⚠️ TODO fixer not found - skipping
)

echo ⚡ [6/8] K2 Kotlin compilation...
call gradlew compileDebugKotlin

echo ⚡ [7/8] APK assembly...
call gradlew assembleDebug

echo ⚡ [8/8] Build verification...
call gradlew check --continue

set "END_TIME=%time%"

echo.
echo ================================================
echo ✅ GENESIS-OS BLEEDING-EDGE BUILD COMPLETE!
echo ================================================
echo 🔥 OpenAPI ALWAYS regenerated fresh!
echo 💥 Zero cache - maximum bleeding-edge!
echo ⏱️ Build completed at %END_TIME%
echo.

if exist "app\build\outputs\apk\debug" (
    echo 📱 Fresh APK Location: app\build\outputs\apk\debug
    start "" "app\build\outputs\apk\debug"
)

echo 🩸 Genesis-OS: "If it can be automated, it MUST be regenerated!"
pause
