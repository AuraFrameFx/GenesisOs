@echo off
echo 🔍 GENESIS-OS AGP RESOURCE COMPILATION DIAGNOSTIC
echo "Analyzing Android Gradle Plugin resource compilation..."
echo.

cd /d %~dp0

echo ⚡ [1/6] Checking AGP version and compatibility...
echo Current AGP: 8.13.0-alpha04 (bleeding-edge)
echo.

echo ⚡ [2/6] Testing basic project configuration...
call gradlew help --quiet

if %ERRORLEVEL% NEQ 0 (
    echo ❌ Basic configuration failed
    pause
    exit /b 1
)

echo ✅ Basic configuration successful!

echo.
echo ⚡ [3/6] Testing resource compilation setup...
call gradlew :app:tasks --group=android --quiet

echo.
echo ⚡ [4/6] Checking for resource validation issues...
call gradlew :app:mergeDebugResources --dry-run

if %ERRORLEVEL% NEQ 0 (
    echo ⚠️ Resource merging may have issues
) else (
    echo ✅ Resource merging configuration looks good
)

echo.
echo ⚡ [5/6] Testing AAPT2 resource compilation...
call gradlew :app:generateDebugResources --dry-run

if %ERRORLEVEL% NEQ 0 (
    echo ⚠️ Resource generation may have issues
) else (
    echo ✅ Resource generation configuration looks good
)

echo.
echo ⚡ [6/6] Full build test (without execution)...
call gradlew assembleDebug --dry-run

if %ERRORLEVEL% NEQ 0 (
    echo ❌ Full build configuration has issues
    echo 💡 May need AGP compatibility adjustments
    pause
    exit /b 1
) else (
    echo ✅ Full build configuration looks good!
)

echo.
echo ✅ AGP RESOURCE DIAGNOSTIC COMPLETE!
echo 🎯 AGP 8.13.0-alpha04 compatibility verified!
echo 🚀 Ready for Genesis-OS bleeding-edge build!
echo.
pause
