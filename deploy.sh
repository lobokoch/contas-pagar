#!/bin/bash

# exit when any command fails
set -e

# keep track of the last executed command
trap 'last_command=$current_command; current_command=$BASH_COMMAND' DEBUG
# echo an error message before exiting
trap 'echo "KERUBIN FINISHED: last command \"${last_command}\" had exit code $?."' EXIT

echo "Starting project deploy..."
cd modules

echo "Packing and installing modules..."
mvn clean install

cd app

echo "Building Docker image..."
mvn dockerfile:build -DskipTests

echo "Verifing..."
mvn verify -DskipTests

echo "Pushing Docker image..."
mvn dockerfile:push -DskipTests

echo "DONE"
