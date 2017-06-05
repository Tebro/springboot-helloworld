#!/bin/bash

if [ -f /etc/config/application.properties ]; then
    cp /etc/config/application.properties /app/application.properties
fi

OPTS=""

while read -r line; do
    if [ ! -z "${line// }" ]; then
        OPTS="$OPTS -D$line"
    fi
done <<< $(env | grep "APP_" | sed s/_/\./g | sed -r "s/^APP\.(.*)$/\1/g")

start_command="java $OPTS -jar /app/app.jar"

echo "Starting with command: $start_command"

$start_command
