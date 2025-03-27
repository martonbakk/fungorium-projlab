## 1. Maven telepítése
Először is, telepítened kell a Maven-t a gépedre, ha még nincs fent.

Windows/Linux/Mac:
    Letöltés: Töltsd le a Maven legfrissebb verzióját az hivatalos oldalról: [Apache Maven.](https://maven.apache.org/download.cgi) Válaszd a bináris zip/tar.gz fájlt (pl. apache-maven-x.x.x-bin.zip).
    Kicsomagolás: Csomagold ki egy tetszőleges mappába (pl. C:\Program Files\Maven\ vagy /usr/local/maven/).
Környezeti változók beállítása:
    Windows:
        Add hozzá a M2_HOME környezeti változót, amelynek értéke a Maven mappa helye (pl. C:\Program Files\Maven\apache-maven-x.x.x).
        Add hozzá a %M2_HOME%\bin-t a Path környezeti változóhoz.
    Linux/Mac:
        Nyiss egy terminált, és szerkeszd a ~/.bashrc vagy ~/.zshrc fájlt:
            export M2_HOME=/usr/local/maven/apache-maven-x.x.x
            export PATH=$M2_HOME/bin:$PATH
        Frissítsd a terminált: source ~/.bashrc vagy source ~/.zshrc.
Ellenőrzés: Nyiss egy terminált, és írd be:
mvn -version

Ha mindent jól csináltál, látnod kell a Maven verzióját és a Java verzióját.

## 2. Kód futtatása és tesztelése
chmod +x run.sh
./run.sh
