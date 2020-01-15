#!/bin/bash
echo "Starting project deploy..."
cd modules

echo "Packing and installing modules..."
mvn clean install -DskipTests

cd app

echo "Building Docker image..."
mvn dockerfile:build

echo "Verifing..."
mvn verify

echo "Pushing Docker image..."
mvn dockerfile:push

echo "DONE"
