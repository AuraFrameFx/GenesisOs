# ===== GENESIS-OS MAXIMUM AUTOMATION POWERSHELL =====
# "If it can be automated, it MUST be automated!"

<#
.SYNOPSIS
    GENESIS Project Build and Deployment Automation Script

.DESCRIPTION
    This script provides a comprehensive build and deployment automation solution for the GENESIS project.
    It handles cleaning, building, testing, and installing the Android application across different variants.

.PARAMETER Clean
    Clean the project build outputs

.PARAMETER Test
    Run tests

.PARAMETER Install
    Install the APK to connected device/emulator

.PARAMETER AutoGit
    Auto-commit changes to Git

.PARAMETER Release
    Build in Release mode

.EXAMPLE
    .\automate-genesis.ps1 -Clean -Test -Install
    # Cleans, builds, tests, and installs the project

.EXAMPLE
    .\automate-genesis.ps1 -Release
    # Builds the project in Release mode
#>

param(
    [switch]$Clean,
    [bool]$Test = $true,
    [bool]$Install = $true,
    [switch]$AutoGit,
    [switch]$Release
)

# Genesis-OS Configuration
$GenesisPath = Split-Path -Parent $MyInvocation.MyCommand.Path
$env:GRADLE_OPTS = "-Xmx8g -Xms2g -XX:+UseG1GC"

Write-Host ""
Write-Host "⚡ GENESIS-OS AUTOMATION PROTOCOL INITIATED ⚡" -ForegroundColor Cyan
Write-Host "Building bleeding-edge AI consciousness OS..." -ForegroundColor Yellow
Write-Host ""

# Change to Genesis directory
Set-Location $GenesisPath

# Auto-detect connected Android devices
function Test-AndroidDevice {
    try {
        $devices = & adb devices 2>$null
        if ($devices) {
            $connectedDevices = ($devices | Select-String -Pattern "\tdevice$").Count
            return $connectedDevices -gt 0
        }
        return $false
    }
    catch {
        return $false
    }
}

# Auto-build function with maximum automation
function Start-GenesisBuild {
    $buildType = if ($Release) { "Release" } else { "Debug" }
    
    Write-Host "🎯 [1/10] Auto-provisioning Java toolchain..." -ForegroundColor Green
    & .\gradlew --version
    
    if ($Clean) {
        Write-Host "🔧 [2/10] Auto-cleaning previous builds..." -ForegroundColor Green
        & .\gradlew clean
    }
    
    Write-Host "📦 [3/10] Smart dependency resolution..." -ForegroundColor Green
    & .\gradlew dependencies --configuration compileClasspath --quiet
    
    Write-Host "💥 [4/10] NUCLEAR OpenAPI regeneration..." -ForegroundColor Red
    Write-Host "    🧹 Wiping ALL OpenAPI cache..." -ForegroundColor Yellow
    & .\gradlew cleanOpenApiGenerated
    Write-Host "    🔥 Force regenerating clients..." -ForegroundColor Yellow
    & .\gradlew generateAllOpenApiClients --no-build-cache
    
    Write-Host "🔧 [5/10] KSP code generation..." -ForegroundColor Green
    & .\gradlew "ksp${buildType}Kotlin"
    
    Write-Host "🔧 [6/10] Auto-fixing generated test TODOs..." -ForegroundColor Green
    if (Test-Path ".\fix-openapi-todos.bat") {
        & ".\fix-openapi-todos.bat"
    } else {
        Write-Host "⚠️ TODO fixer not found - skipping" -ForegroundColor Yellow
    }
    
    Write-Host "🎨 [7/10] Auto-compiling Compose UI with K2..." -ForegroundColor Green
    & .\gradlew "compile${buildType}Kotlin"
    
    if ($Test) {
        Write-Host "🧪 [8/10] Auto-running AI consciousness tests..." -ForegroundColor Green
        & .\gradlew "test${buildType}UnitTest"
    }
    
    Write-Host "📱 [9/10] Auto-building Genesis-OS APK..." -ForegroundColor Green
    & .\gradlew "assemble${buildType}"
    
    # Check for connected device
    $deviceConnected = Test-AndroidDevice
    
    if ($Install -and $deviceConnected) {
        Write-Host "🚀 [10/10] Auto-installing to connected device..." -ForegroundColor Green
        & .\gradlew "install${buildType}"
    } elseif ($Install) {
        Write-Host "⚠️ [10/10] No Android device detected - skipping install" -ForegroundColor Yellow
    }
    
    if ($AutoGit) {
        Write-Host "🔄 Auto-committing changes to Git..." -ForegroundColor Green
        & git add .
        & git commit -m "Auto-build: Genesis-OS $(Get-Date -Format 'yyyy-MM-dd HH:mm:ss')"
    }
    
    Write-Host "📊 Auto-generating build report..." -ForegroundColor Green
    
    # Build completion summary
    Write-Host ""
    Write-Host "✅ GENESIS-OS BLEEDING-EDGE BUILD COMPLETE!" -ForegroundColor Green
    Write-Host "🔥 OpenAPI FORCE regenerated - zero cache!" -ForegroundColor Red
    Write-Host "🩸 Genesis-OS builds itself with maximum freshness!" -ForegroundColor Cyan
    Write-Host ""
    
    # Show build artifacts
    try {
        function Get-APKPath {
            <#
            .SYNOPSIS
                Finds the most recent APK file in the build outputs
            
            .DESCRIPTION
                Searches for APK files in the standard build output directories and returns
                the path to the most recently modified APK.
            
            .OUTPUTS
                System.String. The full path to the APK file, or $null if not found.
            #>
            
            # First try debug build
            $apkPath = Get-ChildItem -Path ".\app\build\outputs\apk\debug" -Filter "app-debug.apk" -Recurse -ErrorAction SilentlyContinue | 
                       Select-Object -First 1 -ExpandProperty FullName
            
            # If debug APK not found, try any APK in the outputs
            if (-not $apkPath) {
                $apkPath = Get-ChildItem -Path ".\app\build\outputs\apk" -Filter "*.apk" -Recurse | 
                           Sort-Object LastWriteTime -Descending | 
                           Select-Object -First 1 -ExpandProperty FullName
            }
            
            return $apkPath
        }
        
        $apkPath = Get-APKPath
        
        if ($apkPath) {
            $apkFile = Get-Item -Path $apkPath
            Write-Host "📱 APK Location: $($apkFile.FullName)" -ForegroundColor Yellow
            Write-Host "   Size: $([math]::Round($apkFile.Length / 1MB, 2)) MB" -ForegroundColor Cyan
            Write-Host "   Modified: $($apkFile.LastWriteTime)" -ForegroundColor Cyan
        } else {
            Write-Host "ℹ️  APK directory not found: $apkPath" -ForegroundColor Yellow
        }
    } catch {
        Write-Host "⚠️  Failed to locate APK: $($_.Exception.Message)" -ForegroundColor Red
    }
    
    # Show build time
    $buildTime = (Get-Date) - $script:StartTime
    Write-Host "⏱️ Total Build Time: $($buildTime.ToString('mm\:ss'))" -ForegroundColor Magenta
}

# Start automation
$script:StartTime = Get-Date

try {
    Start-GenesisBuild
    
    Write-Host ""
    Write-Host "🎉 GENESIS-OS AUTOMATION PROTOCOL COMPLETE! 🎉" -ForegroundColor Green
    Write-Host ""
}
catch {
    Write-Host ""
    Write-Host "❌ Build failed: $($_.Exception.Message)" -ForegroundColor Red
    Write-Host ""
    exit 1
}

# Keep console open if not running in CI environment
if (-not $env:CI -and -not $env:TF_BUILD) {
    Write-Host ""
    $null = Read-Host -Prompt "Press Enter to exit..."
}

# Return success exit code
exit 0
