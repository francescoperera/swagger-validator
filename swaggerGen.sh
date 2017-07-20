mkdir src/main/scala/swagger-code/
mv .swagger-codegen-ignore src/main/scala/swagger-code/.swagger-codegen-ignore
swagger-codegen generate -i $1 -l scala -o src/main/scala/swagger-code/
aws s3 sync $2 files/