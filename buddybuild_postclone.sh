#!/bin/bash

CONFIGURATION="${BUDDYBUILD_SECURE_FILES}/configurations.xml"
TARGET_CONFIGURATION="${BUDDYBUILD_WORKSPACE}/app/src/main/res/values/configurations.xml"
if [[ -f "$CONFIGURATION" ]]; then
    cp "${CONFIGURATION}" "${TARGET_CONFIGURATION}"
else
    cp "${TARGET_CONFIGURATION}.dist" "${TARGET_CONFIGURATION}"
fi
