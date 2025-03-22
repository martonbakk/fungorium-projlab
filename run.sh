#!/bin/bash

# Projekt gyökérkönyvtár
PROJECT_DIR=$(pwd)
SRC_DIR="$PROJECT_DIR/src"

# Ellenőrizzük, hogy a src mappa létezik-e
if [ ! -d "$SRC_DIR" ]; then
    echo "Hiba: A 'src' mappa nem található a $PROJECT_DIR könyvtárban!"
    exit 1
fi

# Navigáljunk a src mappába
cd "$SRC_DIR" || exit 1

# Ellenőrizzük, hogy a Java telepítve van-e
if ! command -v javac &> /dev/null; then
    echo "Hiba: A 'javac' parancs nem található. Kérlek, telepítsd a Java Development Kit-et (JDK)!"
    exit 1
fi

if ! command -v java &> /dev/null; then
    echo "Hiba: A 'java' parancs nem található. Kérlek, telepítsd a Java Runtime Environment-et (JRE)!"
    exit 1
fi

# Fordítsuk le az összes Java fájlt
echo "Fordítás folyamatban..."
javac *.java

# Ellenőrizzük, hogy a fordítás sikeres volt-e
if [ $? -ne 0 ]; then
    echo "Hiba: A fordítás sikertelen!"
    exit 1
fi

# Futtassuk a Program osztályt
echo "Program futtatása..."
java Program

# Ellenőrizzük, hogy a futtatás sikeres volt-e
if [ $? -ne 0 ]; then
    echo "Hiba: A program futtatása sikertelen!"
    exit 1
fi

echo "A program sikeresen lefutott!"