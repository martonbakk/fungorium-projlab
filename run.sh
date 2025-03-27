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

# Ellenőrizzük a Java verzióját
JAVA_VERSION=$(java -version 2>&1 | awk -F '"' '/version/ {print $2}')
echo "Java verzió: $JAVA_VERSION"

# 2. Ellenőrizzük, hogy a Maven telepítve van-e
if ! command -v mvn &> /dev/null; then
    echo "Hiba: A 'mvn' parancs nem található. Kérlek, telepítsd a Maven-t!"
    exit 1
fi

# 3. Buildeljük a projektet
echo "Projekt buildelése Maven-nel..."
mvn clean package -DskipTests

# Ellenőrizzük, hogy a build sikeres volt-e
if [ $? -ne 0 ]; then
    echo "Hiba: A Maven build sikertelen!"
    exit 1
fi

# 4. Ellenőrizzük, hogy a JAR fájl létezik-e
if [ ! -f "$JAR_FILE" ]; then
    echo "Hiba: A JAR fájl nem található: $JAR_FILE"
    echo "Ellenőrizd, hogy a pom.xml helyesen van-e konfigurálva, és a build sikeresen lefutott!"
    exit 1
fi

# 5. Futtassuk a JAR fájlt
echo "Projekt futtatása..."
java -jar "$JAR_FILE"

# Ellenőrizzük, hogy a futtatás sikeres volt-e
if [ $? -ne 0 ]; then
    echo "Hiba: A program futtatása sikertelen!"
    exit 1
fi

echo "A program sikeresen lefutott!"