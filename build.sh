#!/bin/bash
echo "Starting build generation..."
cd modules

echo "Packing modules..."
mvn clean package

cd app

echo "Building Docker image..."
mvn dockerfile:build

echo "DONE"