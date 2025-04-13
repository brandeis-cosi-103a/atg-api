#!/bin/bash

set -x
set -e
set -o pipefail

if [ "$#" -ne 1 ]; then
    echo "Usage: $0 <version num>"
    exit 1
fi

VERSION=$1
if [[ ! $VERSION =~ ^[0-9]+\.[0-9]+\.[0-9]+$ ]]; then
    echo "Version must be in the format X.Y.Z"
    exit 1
fi

if [ -z "$AZURE_STORAGE_CONNECTION_STRING" ]; then
    echo "AZURE_STORAGE_CONNECTION_STRING is not set"
    exit 1
fi

echo "Uploading docs for version $VERSION"

# Create the directory for the version
rm -r docs
jar -xvf lib/target/atg-api-$VERSION-javadoc.jar -C docs

URL="https://atgapi.blob.core.windows.net/\$web/$VERSION/index.html"
cat <<EOF > index.html
<!DOCTYPE HTML>
<html lang="en-US">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="refresh" content="0; url=$URL">
        <script type="text/javascript">
            window.location.href = "$URL";
        </script>
        <title>Page Redirection</title>
    </head>
    <body>
        If you are not redirected automatically, follow this <a href='$URL'>link to the documentation</a>.
    </body>
</html>
EOF

IFS='.' read -r MAJOR MINOR PATCH <<< "$VERSION"
echo "Parsed version: Major=$MAJOR, Minor=$MINOR, Patch=$PATCH"

az storage blob upload   --account-name atgapi --container \$web --name latest/index.html --file index.html --overwrite
az storage blob upload   --account-name atgapi --container \$web --name $MAJOR/index.html --file index.html --overwrite
az storage blob upload   --account-name atgapi --container \$web --name $MAJOR.$MINOR/index.html --file index.html --overwrite
az storage blob upload-batch   --account-name atgapi --destination-path="$MAJOR.$MINOR.$PATCH"   --destination=\$web   --source docs --overwrite
