#!/bin/bash

OPTS=""

if [ ! -z $HELLO_NAME ]; then
    OPTS=" -Dhello.name=$HELLO_NAME"
fi

java $OPTS -jar /app.jar