#!/bin/bash

jar -xvf lib/target/atg-api-1.4.1-SNAPSHOT-javadoc.jar -C docs
# Need to get a connection string from the storage account in azure portal to make this work
az storage blob upload-batch   --account-name atgapi --destination-path="v1"   --destination=\$web   --source docs --overwrite