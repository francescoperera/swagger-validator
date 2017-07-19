swagger-codegen generate -i $1 -l scala -o swagger-code/
aws s3 sync $2 files/