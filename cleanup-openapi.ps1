# Script to clean up old OpenAPI files after consolidation
# This script will remove the old OpenAPI files that have been moved to the new location

Write-Host "🚀 Starting OpenAPI cleanup process..." -ForegroundColor Cyan

# List of directories to clean up
$directoriesToClean = @(
    "api-spec",
    "app\src\main\openapi",
    "app\src\main\resources\*.yaml",
    "app\src\main\resources\*.yml"
)

# First, verify the new location has the files
$newLocation = "$PSScriptRoot\openapi\specs"
if (-not (Test-Path $newLocation)) {
    Write-Host "❌ Error: New OpenAPI location not found at $newLocation" -ForegroundColor Red
    exit 1
}

$fileCount = (Get-ChildItem -Path $newLocation -File).Count
if ($fileCount -eq 0) {
    Write-Host "❌ Error: No files found in the new OpenAPI location" -ForegroundColor Red
    exit 1
}

Write-Host "✅ Verified $fileCount files in new OpenAPI location" -ForegroundColor Green

# Ask for confirmation
Write-Host ""
Write-Host "This will remove the following directories and files:" -ForegroundColor Yellow
$directoriesToClean | ForEach-Object { Write-Host "  - $_" -ForegroundColor Yellow }
Write-Host ""
$confirmation = Read-Host "Do you want to proceed with cleanup? (y/n)"

if ($confirmation -ne 'y') {
    Write-Host "Cleanup cancelled" -ForegroundColor Yellow
    exit 0
}

# Perform the cleanup
$removedCount = 0
foreach ($dir in $directoriesToClean) {
    $fullPath = Join-Path -Path $PSScriptRoot -ChildPath $dir
    
    if (Test-Path $fullPath) {
        try {
            if ($dir -match "\*\.ya?ml$") {
                # Handle file patterns
                Remove-Item -Path $fullPath -Force -ErrorAction Stop
                $count = (Get-Item $fullPath -ErrorAction SilentlyContinue | Measure-Object).Count
            } else {
                # Handle directories
                Remove-Item -Path $fullPath -Recurse -Force -ErrorAction Stop
                $count = 1
            }
            Write-Host "✅ Removed: $dir" -ForegroundColor Green
            $removedCount += $count
        } catch {
            Write-Host "⚠️  Warning: Could not remove $dir - $_" -ForegroundColor Yellow
        }
    } else {
        Write-Host "ℹ️  Not found (skipping): $dir" -ForegroundColor Cyan
    }
}

# Create a placeholder README in the old api-spec directory
$readmePath = Join-Path -Path $PSScriptRoot -ChildPath "api-spec\README.md"
if (-not (Test-Path (Split-Path $readmePath -Parent))) {
    New-Item -ItemType Directory -Path (Split-Path $readmePath -Parent) -Force | Out-Null
}

# Create README content as an array of strings
$readmeLines = @(
    "# OpenAPI Specifications",
    "",
    "All OpenAPI specifications have been moved to the root '/openapi/specs/' directory.",
    "",
    "This directory is kept for backward compatibility but should be considered deprecated.",
    "", bv
    "## New Location",
    "* Specs Directory: /openapi/specs/",
    "* Generated Code: /openapi/generated/",
    "* Templates: /openapi/templates/ (if applicable)",
    "",
    "## Migration Complete",
    "* [DONE] All specs have been consolidated",
    "* [DONE] OpenAPI version standardized to 3.1.0",
    "* [DONE] Build configurations updated",
    "* [DONE] Backups available in /openapi.backup/ and /openapi.backup.app/",
    "",
    "## Next Steps",
    "1. Update any documentation or scripts that reference the old locations",
    "2. The old directories can be safely removed once all references are updated"
)

# Write the content to the file
$readmeLines | Out-File -FilePath $readmePath -Encoding utf8 -Force

Write-Host ""
Write-Host "✨ Cleanup complete! Removed $removedCount items." -ForegroundColor Green
Write-Host "  - Old specs moved to: $PSScriptRoot\openapi\specs\" -ForegroundColor Cyan
Write-Host "  - Backups available in: $PSScriptRoot\openapi.backup\" -ForegroundColor Cyan
Write-Host "  - A README has been placed in the old api-spec directory" -ForegroundColor Cyan
