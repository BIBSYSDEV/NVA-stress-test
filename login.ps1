python ..\aws-cli-tools\samlauth\samlauth.py -f -a NVAtest
# python .\aws.py | ForEach-Object {
#     #Expecting all result to be $env:NAME=VALUE for variables that need to be set
#     $a = $_ -replace '\$env:(\w+)=(.*)', '$1;$2' -split ';'
#
#     #Setting variables at process-level. Can be replaced with "User" and "Machine" for permanent variables
#     [System.Environment]::SetEnvironmentVariable($a[0], $a[1], "Process")
# }
# e2e
#[System.Environment]::SetEnvironmentVariable("CYPRESS_AWS_USER_POOL_ID", "eu-west-1_lfd37eQxM", "Process")
#[System.Environment]::SetEnvironmentVariable("CYPRESS_AWS_CLIENT_ID", "4ekg7vjqp0upin62bp3ikj00ts", "Process")

# test
[System.Environment]::SetEnvironmentVariable("CYPRESS_AWS_USER_POOL_ID", "eu-west-1_zROj9DNSx", "Process")
[System.Environment]::SetEnvironmentVariable("CYPRESS_AWS_CLIENT_ID", "h82a2vupd55fipmjkhhrjije8", "Process")


[System.Environment]::SetEnvironmentVariable("CYPRESS_DEVUSER", "osteloff", "Process")
[System.Environment]::SetEnvironmentVariable("CYPRESS_DEVPASSWORD", "osteloff", "Process")