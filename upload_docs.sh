#!/bin/bash

# Need to get a connection string from the storage account in azure portal to make this work
cd lib
mvn clean package
cd ..
rm -r docs
jar -xvf lib/target/atg-api-*-SNAPSHOT-javadoc.jar -C docs

az storage blob upload-batch   --account-name atgapi --destination-path="v1"   --destination=\$web   --source docs --overwrite