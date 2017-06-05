#!/bin/bash

OPTS=""

while read -r line; do
    OPTS="$OPTS -D$line"
done <<< $(env | grep "APP_" | sed s/_/\./g | sed -r "s/^APP\.(.*)$/\1/g")

start_command="java $OPTS -jar /app.jar"

echo "Starting with command: $start_command"

$start_command
