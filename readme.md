# FUNGORIUM_PROJLAB

This document outlines the steps required to install and run the `FUNGORIUM_PROJLAB` project. Follow the instructions below to install Maven, run the code, and execute tests.

## 1. Installing Maven

The project requires Maven for building and running. If Maven is not already installed on your system, follow the steps below.

### 1.1. Download and Extract

1. **Download**:
   - Download the latest version of Maven from the official website: [Apache Maven](https://maven.apache.org/download.cgi).
   - Choose the binary file: `apache-maven-x.x.x-bin.zip` (Windows) or `apache-maven-x.x.x-bin.tar.gz` (Linux/Mac).

2. **Extract**:
   - Extract the downloaded file to a directory of your choice:
     - **Windows**: For example, `C:\Program Files\Maven\`.
     - **Linux/Mac**: For example, `/usr/local/maven/`.

### 1.2. Set Up Environment Variables

#### **Windows**
1. Create a new system variable:
   - Name: `M2_HOME`
   - Value: The Maven installation directory (e.g., `C:\Program Files\Maven\apache-maven-x.x.x`).
2. Add the Maven `bin` directory to the `Path` environment variable:
   - Locate the `Path` variable and append: `%M2_HOME%\bin`.

#### **Linux/Mac**
1. Open a terminal and edit your shell configuration file (e.g., `~/.bashrc` or `~/.zshrc`):
   ```bash
   export M2_HOME=/usr/local/maven/apache-maven-x.x.x
   export PATH=$M2_HOME/bin:$PATH
   ```

### 1.3. Verify Installation
- Open a terminal and run:
  ```bash
  mvn -version
  ```
- If set up correctly, you should see the Maven version and the Java version.

## 2. Running and Testing the Code

### 2.1. Running the Script
- Grant execution permissions to the `run.sh` script:
  ```bash
  chmod +x run.sh
  ```
- Run the script from the project root:
  ```bash
  ./run.sh
  ```
- Run only tests: To execute tests only, use the test flag:
  ```bash
  ./run.sh test
  ```
- Run with GUI: To launch the program with a graphical interface:
  ```bash
  ./run.sh gui
  ```

### 2.2. What Does the Script Do?
- Checks if Java and Maven are installed.
- Builds the project using the `mvn clean package` command, which:
  - Deletes previous build outputs (`clean`).
  - Compiles the code (`compile`).
  - Runs the tests (`test`).
  - Packages the project into a JAR file (`package`).
- Executes the generated JAR file: `target/fungorium-app-1.0-SNAPSHOT.jar`.





# Hungarian documentation 

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
- Csak tesztek futtatása: Ha csak a teszteket szeretnéd futtatni, használd a test flaget: ./run.sh test
- A Program grafikus interface-el való futtatása: ./run.sh gui
### 2.2 Mit csinál a script?
- Ellenőrzi, hogy a Java és a Maven telepítve van-e.
- Buildeli a projektet a mvn clean package paranccsal, amely:
- Törli a korábbi build eredményeit (clean).
- Lefordítja a kódot (compile).
- Futtatja a teszteket (test).
- Csomagolja a projektet egy JAR fájlba (package).
-Futtatja a generált JAR fájlt: target/fungorium-app-1.0-SNAPSHOT.jar.
