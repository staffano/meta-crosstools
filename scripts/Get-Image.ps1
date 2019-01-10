# Get-Image.ps1
# syntax:
#       Get-Image.ps1 TargetName
# Downloads the image from the build volume

Param(

   [parameter(Mandatory=$true, Position=0)]
    [String]
    $TargetName

)
# Build the docker image
$aa=(get-item $PSScriptRoot).Parent.FullName
Push-Location -Path $aa

# The idea is to create a container with the bb-tmp volume mounted
# then we use docker cp to get the expected file out of the container.

@"
FROM scratch
CMD
"@ | docker build -q -t nothing -

docker container create --name dummy -v bb-tmp:/meta-crosstools/build/tmp nothing
docker cp dummy:/meta-crosstools/build/tmp/$TargetName/IMAGES ./
docker rm dummy

# Restore location where we were before script
Pop-Location