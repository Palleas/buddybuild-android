#!/bin/bash

CONFIGURATION="${BUDDYBUILD_SECURE_FILES}/configurations.xml"
TARGET_CONFIGURATION="${BUDDYBUILD_WORKSPACE}/app/src/main/res/values/configurations.xml"
if [[ -f "$CONFIGURATION" ]]; then
    cp "${CONFIGURATION}" "${TARGET_CONFIGURATION}"
else
    echo "<resources><string name=\"buddybuild_token\"></string></resources>" > "${TARGET_CONFIGURATION}"
fi

cat "${TARGET_CONFIGURATION}"
