/**
 * A Spore absztrakt osztály a különböző típusú spórák közös tulajdonságait és viselkedését definiálja.
 * A spórák a gombatestek által termelt egységek, amelyek új gombafonalak és gombatestek fejlődését segítik,
 * valamint különböző hatásokat gyakorolhatnak a rovarokra. Az Entity osztályból származik, és implementálja
 * a SporeInterface interfészt.
 */
public abstract class Spore extends Entity implements SporeInterface {

    /**
     * A Spore osztály konstruktora.
     * Kiírja a konzolra, hogy egy új Spore objektum jött létre.
     */
    public Spore() {
        System.out.println("New Spore Created");
    }
}