#!/bin/bash

# Projekt gyökérkönyvtár
PROJECT_DIR=$(pwd)
TARGET_DIR="$PROJECT_DIR/target"
ARTIFACT_ID="fungorium-app"
VERSION="1.0-SNAPSHOT"
JAR_FILE="$TARGET_DIR/$ARTIFACT_ID-$VERSION.jar"

# 1. Ellenőrizzük, hogy a Java telepítve van-e
if ! command -v java &> /dev/null; then
    echo "Hiba: A 'java' parancs nem található. Kérlek, telepítsd a Java-t (JDK)!"
    exit 1
fi

# Java verzió kiírás
JAVA_VERSION=$(java -version 2>&1 | awk -F '"' '/version/ {print $2}')
echo "Java verzió: $JAVA_VERSION"

# 2. Ellenőrizzük, hogy a Maven telepítve van-e
if ! command -v mvn &> /dev/null; then
    echo "Hiba: A 'mvn' parancs nem található. Kérlek, telepítsd a Maven-t!"
    exit 1
fi

# 3. Ha a "test" flag van megadva, csak tesztelünk
if [ "$1" = "test" ]; then
    echo "Tesztek futtatása Maven-nel..."
    mvn test
    if [ $? -ne 0 ]; then
        echo "Hiba: A tesztek futtatása sikertelen! Nézd meg a target/surefire-reports mappát."
        exit 1
    fi
    echo "Tesztek sikeresen lefutottak!"
    exit 0
fi

# 4. Első futtatásnál mvn clean install, utána clean package
if [ ! -d "$TARGET_DIR" ]; then
    echo "Első futtatás: 'mvn clean install' futtatása..."
    mvn clean install
else
    echo "Projekt buildelése Maven-nel (clean package)..."
    mvn clean package
fi

# Ellenőrizzük a build eredményét
if [ $? -ne 0 ]; then
    echo "Hiba: A Maven build sikertelen! Ellenőrizd a logot."
    exit 1
fi

# 5. Ellenőrizzük, hogy a JAR fájl létezik-e
if [ ! -f "$JAR_FILE" ]; then
    echo "Hiba: A JAR fájl nem található: $JAR_FILE"
    echo "Ellenőrizd a pom.xml-t és a build eredményt!"
    exit 1
fi

# 6. Fut a program
echo "Projekt futtatása..."
java -jar "$JAR_FILE"

if [ $? -ne 0 ]; then
    echo "Hiba: A program futtatása sikertelen!"
    exit 1
fi

echo "A program sikeresen lefutott!"
