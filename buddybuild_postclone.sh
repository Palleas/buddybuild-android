#!/bin/bash

CONFIGURATION="${BUDDYBUILD_SECURE_FILES}/configuration.xml"
TARGET_CONFIGURATION="${BUDDYBUILD_WORKSPACE}/app/src/main/res/values/configuration.xml"
if [[ -f "$CONFIGURATION" ]]; then
    cp "${CONFIGURATION}" "${TARGET_CONFIGURATION}"
else
    cp "${TARGET_CONFIGURATION}.dist" "${TARGET_CONFIGURATION}"
fi
