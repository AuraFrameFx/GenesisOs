@echo off
setlocal enabledelayedexpansion

echo ⚡ GENESIS-OS NUCLEAR BUILD FIXER ⚡
echo "When automation fails, go NUCLEAR!"
echo.

cd /d %~dp0

echo 🚀 [1/12] Stopping all Java/Gradle processes...
taskkill /f /im java.exe 2>nul
taskkill /f /im kotlin.exe 2>nul
taskkill /f /im gradle.exe 2>nul

echo 🚀 [2/12] Killing all Gradle daemons...
call gradlew --stop

echo 🚀 [3/12] Nuclear clean - all build files...
call gradlew clean

echo 🚀 [4/12] Deleting .gradle directory...
if exist ".gradle" (
    echo     Removing .gradle...
    rmdir /s /q ".gradle"
)

echo 🚀 [5/12] Deleting all build directories...
for /d /r . %%d in (build) do (
    if exist "%%d" (
        echo     Removing %%d
        rmdir /s /q "%%d" 2>nul
    )
)

echo 🚀 [6/12] Deleting all generated directories...
for /d /r . %%d in (generated) do (
    if exist "%%d" (
        echo     Removing %%d
        rmdir /s /q "%%d" 2>nul
    )
)

echo 🚀 [7/12] Deleting KSP cache...
for /d /r . %%d in (ksp) do (
    if exist "%%d" (
        echo     Removing %%d
        rmdir /s /q "%%d" 2>nul
    )
)

echo 🚀 [8/12] Clearing IDE caches...
if exist ".idea\caches" rmdir /s /q ".idea\caches"
if exist ".idea\modules" rmdir /s /q ".idea\modules"
if exist ".idea\libraries" rmdir /s /q ".idea\libraries"

echo 🚀 [9/12] Clearing intermediates...
for /d /r . %%d in (intermediates) do (
    if exist "%%d" (
        echo     Removing %%d
        rmdir /s /q "%%d" 2>nul
    )
)

echo 🚀 [10/12] Clearing tmp directories...
for /d /r . %%d in (tmp) do (
    if exist "%%d" (
        echo     Removing %%d
        rmdir /s /q "%%d" 2>nul
    )
)

echo 🚀 [11/12] Waiting for file system...
timeout /t 3 /nobreak > nul

echo 🚀 [12/12] Verifying Gradle wrapper...
if not exist "gradlew.bat" (
    echo     ERROR: gradlew.bat not found!
    pause
    exit /b 1
)

echo.
echo ✅ NUCLEAR CLEAN COMPLETE!
echo 🎯 All cached namespaces should be gone!
echo 🚀 Ready for completely fresh build!
echo.

echo ⚡ Starting fresh build...
call gradlew build --no-daemon --rerun-tasks

if %ERRORLEVEL% NEQ 0 (
    echo.
    echo ❌ Build still failed. Let's check what's wrong...
    echo.
    pause
    exit /b 1
) else (
    echo.
    echo ✅ GENESIS-OS BUILD SUCCESS!
    echo 🎉 Nuclear option worked!
    echo.
)

pause
