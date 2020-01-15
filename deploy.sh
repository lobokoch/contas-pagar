#!/bin/bash
echo "Starting project deploy..."
cd modules

echo "Packing and installing modules..."
mvn clean install -DskipTests

cd app

echo "Building Docker image..."
mvn dockerfile:build -DskipTests

echo "Verifing..."
mvn verify -DskipTests

echo "Pushing Docker image..."
mvn dockerfile:push -DskipTests

echo "DONE"
