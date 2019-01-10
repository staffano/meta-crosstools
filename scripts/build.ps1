# Build the docker image
$sp=(get-item ($PSScriptRoot))
Set-Location -Path $sp.Parent.Parent

docker build -t mcb .

# check if volumes exist
$dv = (docker volume ls -q)
if (!$dv -match 'bb-tmp') {

}
docker volume create bb-