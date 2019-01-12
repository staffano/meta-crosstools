# Build-Recipy.ps1
# Powershell script that runs the build.py inside a docker container.
# syntax:
#       Build-Recipy.ps1 [-clean|-c] [-verbosity|-v] [-ForceRebuild | -f] recipe target
# 
# Specify -clean if you want the recipe cleaned instead of built.
# Specify -ForceRebuild if you have updated the docker context and want to rebuild
# the docker build image mcb:latest.
#
# Will build docker image and create volumes if not present already.
# When the 

Param(

    [parameter(Mandatory=$false)]
    [alias("c")]
    [Switch]
    $clean,

    [parameter(Mandatory=$false)]
    [alias("v")]
    [Switch]
    $verbosity,

    [parameter(Mandatory=$false)]
    [alias("f")]
    [Switch]
    $ForceRebuild,


    [parameter(Mandatory=$true, Position=0)]
    [String]
    $Recipe,

    [parameter(Mandatory=$true, Position=1)]
    [String]
    $TargetName

)
# Build the docker image
$aa=(get-item $PSScriptRoot).Parent.FullName
Push-Location -Path $aa

# Build docker image if it not exists
if ($ForceRebuild -or !(docker images mcb:latest -q)) {
    Write-Host "Building docker image mcb:latest"
    docker build -t mcb .
    Write-Host "done."
}

# Create volumes if they don't exist
$dv = (docker volume ls -q)
if (!$dv -match 'bb-tmp') {
    docker volume create bb-tmp    
}
if (!$dv -match 'bb-downloads') {
    docker volume create bb-downloads    
}

# Build switchlist
$switches =@()

if ($clean) {
    $switches += "--clean"
}

if ($verbosity) {
    $switches += "--verbosity"
}



# Execute command
docker run --rm -it -v bb-download:/meta-crosstools/build/downloads -v bb-tmp:/meta-crosstools/build/tmp mcb:latest $switches $Recipe $TargetName

# Restore location where we were before script
Pop-Location
