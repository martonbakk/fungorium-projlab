# FUNGORIUM_PROJLAB

Ez a dokumentum a `FUNGORIUM_PROJLAB` projekt telepítéséhez és futtatásához szükséges lépéseket tartalmazza. Kövesd az alábbi utasításokat a Maven telepítéséhez, valamint a kód futtatásához és teszteléséhez.

## 1. Maven telepítése

A projekt futtatásához és buildeléséhez szükséged lesz a Maven-re. Ha még nincs telepítve a gépeden, kövesd az alábbi lépéseket.

### 1.1. Letöltés és kicsomagolás

1. **Letöltés**:
   - Töltsd le a Maven legfrissebb verzióját az hivatalos weboldalról: [Apache Maven](https://maven.apache.org/download.cgi).
   - Válaszd a bináris fájlt: `apache-maven-x.x.x-bin.zip` (Windows) vagy `apache-maven-x.x.x-bin.tar.gz` (Linux/Mac).

2. **Kicsomagolás**:
   - Csomagold ki a letöltött fájlt egy tetszőleges mappába:
     - **Windows**: Például `C:\Program Files\Maven\`.
     - **Linux/Mac**: Például `/usr/local/maven/`.

### 1.2. Környezeti változók beállítása

#### **Windows**
1. Hozz létre egy új rendszerváltozót:
   - Név: `M2_HOME`
   - Érték: A Maven telepítési mappája (pl. `C:\Program Files\Maven\apache-maven-x.x.x`).
2. Add hozzá a Maven `bin` mappáját a `Path` környezeti változóhoz:
   - Keresd meg a `Path` változót, és add hozzá: `%M2_HOME%\bin`.

#### **Linux/Mac**
1. Nyiss egy terminált, és szerkeszd a shell konfigurációs fájlt (pl. `~/.bashrc` vagy `~/.zshrc`):
   ```bash
   export M2_HOME=/usr/local/maven/apache-maven-x.x.x
   export PATH=$M2_HOME/bin:$PATH

Ellenőrzés: Nyiss egy terminált, és írd be:
mvn -version

Ha mindent jól csináltál, látnod kell a Maven verzióját és a Java verzióját.

## 2. Kód futtatása és tesztelése
### 2.1. Script futtatása 
- Add futtatási jogosultságot a run.sh scripthez: chmod +x run.sh
- Futtasd a scriptet a projekt gyökeréből: ./run.sh
### 2.2 Mit csinál a script?
- Ellenőrzi, hogy a Java és a Maven telepítve van-e.
- Buildeli a projektet a mvn clean package paranccsal, amely:
- Törli a korábbi build eredményeit (clean).
- Lefordítja a kódot (compile).
- Futtatja a teszteket (test).
- Csomagolja a projektet egy JAR fájlba (package).
-Futtatja a generált JAR fájlt: target/fungorium-app-1.0-SNAPSHOT.jar.
