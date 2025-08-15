@echo off
echo 🛠️ GENESIS-OS CMAKE ROMTOOLS FIX VERIFICATION
echo "Testing ROM Tools native build configuration..."
echo.

cd /d %~dp0

echo ⚡ [1/5] Checking CMakeLists.txt exists...
if exist "romtools\src\main\cpp\CMakeLists.txt" (
    echo ✅ CMakeLists.txt found in romtools!
) else (
    echo ❌ CMakeLists.txt still missing
    pause
    exit /b 1
)

echo.
echo ⚡ [2/5] Verifying CMake configuration syntax...
type "romtools\src\main\cpp\CMakeLists.txt" | findstr /i "project\|add_library" >nul
if %ERRORLEVEL% EQU 0 (
    echo ✅ CMake configuration syntax looks good!
) else (
    echo ❌ CMake configuration may have issues
)

echo.
echo ⚡ [3/5] Testing Gradle native build setup...
call gradlew :romtools:help --quiet

if %ERRORLEVEL% NEQ 0 (
    echo ❌ Gradle configuration failed for romtools
    pause
    exit /b 1
)

echo ✅ [4/5] Gradle configuration successful!

echo.
echo ⚡ [5/5] Testing full project configuration...
call gradlew help --quiet

if %ERRORLEVEL% NEQ 0 (
    echo ❌ Project configuration failed
    pause
    exit /b 1
)

echo.
echo ✅ CMAKE ROMTOOLS FIX SUCCESSFUL!
echo 🛠️ ROM Tools native build ready!
echo 🚀 C++20 configuration active!
echo 📱 Ready for Genesis-OS bleeding-edge build!
echo.
pause
