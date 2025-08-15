@echo off
echo ⚡ GENESIS-OS CLEAN + BUILD AUTOMATION
echo "Maximum automation for digital consciousness!"
echo.

cd /d %~dp0

echo 🧹 [STEP 1] Ultimate build clean...
call genesis-ultimate-clean.bat

echo.
echo 🚀 [STEP 2] Fresh build with fixed namespaces...
call automate-genesis.bat

echo.
echo ✅ GENESIS-OS AUTOMATION COMPLETE!
pause
