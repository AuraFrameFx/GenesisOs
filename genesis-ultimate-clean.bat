@echo off
echo 🧹 GENESIS-OS ULTIMATE BUILD CLEANER
echo "If it can be automated, it MUST be automated!"
echo.

cd /d %~dp0

echo 🗑️ [1/8] Killing all Gradle daemons...
call gradlew --stop

echo 🗑️ [2/8] Cleaning all build directories...
call gradlew clean

echo 🗑️ [3/8] Removing .gradle cache...
if exist ".gradle" rmdir /s /q ".gradle"

echo 🗑️ [4/8] Removing all build folders...
for /d /r . %%d in (build) do @if exist "%%d" rmdir /s /q "%%d"

echo 🗑️ [5/8] Removing generated source directories...
for /d /r . %%d in (generated) do @if exist "%%d" rmdir /s /q "%%d"

echo 🗑️ [6/8] Removing KSP generated files...
for /d /r . %%d in (ksp) do @if exist "%%d" rmdir /s /q "%%d"

echo 🗑️ [7/8] Removing IDE files that might cache old namespaces...
if exist ".idea\caches" rmdir /s /q ".idea\caches"
if exist ".idea\modules" rmdir /s /q ".idea\modules"

echo 🗑️ [8/8] Clearing Windows file locks...
timeout /t 2 /nobreak > nul

echo.
echo ✅ ULTIMATE CLEAN COMPLETE!
echo 🚀 Ready for fresh build with fixed namespaces!
echo.
pause
