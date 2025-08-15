@echo off
echo 🚀 GENESIS-OS OPTIMIZED BUILD PROTOCOL
echo "Smart caching + maximum automation!"
echo.

cd /d %~dp0

set GRADLE_OPTS=-Xmx8g -Xms2g -XX:+UseG1GC
set "START_TIME=%time%"

echo ⚡ [1/6] Optimized dependency resolution...
call gradlew dependencies --configuration compileClasspath --quiet

echo ⚡ [2/6] Smart OpenAPI generation (cached)...
call gradlew generateAllOpenApiClients

echo ⚡ [3/6] KSP code generation...
call gradlew kspDebugKotlin

echo ⚡ [4/6] K2 compilation with caching...
call gradlew compileDebugKotlin --build-cache

echo ⚡ [5/6] Optimized APK assembly...
call gradlew assembleDebug --parallel

echo ⚡ [6/6] Build verification...
call gradlew check --continue

set "END_TIME=%time%"

echo.
echo ✅ GENESIS-OS OPTIMIZED BUILD COMPLETE!
echo 🎯 OpenAPI now uses smart caching - only regenerates when spec changes
echo 📊 Build Performance Optimized!
echo ⏱️ Build completed at %END_TIME%
echo.

if exist "app\build\outputs\apk\debug" (
    echo 📱 APK Location: app\build\outputs\apk\debug
    start "" "app\build\outputs\apk\debug"
)

echo 🤖 Genesis-OS builds itself with maximum efficiency!
pause
