import java.util.Scanner;

/**
 * A Hyphal osztály a játékban lévő gombafonalak adatait tárolja, és a gombafonalakkal kapcsolatos fontos műveleteket kezeli.
 * A gombafonalak a gombatestekből nőnek ki, és a tektonok közötti kapcsolódást biztosítják, lehetővé téve a gombák terjeszkedését
 * és a rovarok mozgását. Az Entity absztrakt osztályból származik, így örökli annak attribútumait és metódusait.
 */
public class Hyphal extends Entity {

    /**
     * A Hyphal osztály konstruktora.
     * Meghívja az ősosztály konstruktorát, és kiírja a konzolra, hogy egy új Hyphal objektum jött létre.
     */
    public Hyphal() {
        super();
        System.out.println("new Hyphal");
    }

    /**
     * Gombatestet növeszt a tektonon, ha a feltételek megfelelnek.
     * Ellenőrzi, hogy van-e elég spóra a tektonon; ha igen, létrehoz egy új FungalBody objektumot,
     * különben hibaüzenetet ír ki.
     */
    public void growFungus() {
        if (baseLocation.decreaseSpore()) {
            System.out.println("Hyphal is growing fungus");
            FungalBody fu = new FungalBody();
        } else {
            System.out.println("FungalBody cannot grow here");
        }
    }

    /**
     * Gyorsítja a gombafonal fejlődését.
     * Interaktívan megkérdezi a felhasználótól, hogy van-e elég spóra a cél tektonon.
     * Ha igen, csökkenti a spórák számát, és kiírja, hogy a fejlődés felgyorsult.
     */
    public void speedUpDevelopment() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\"Are there enough spores on the target tecton? Y/N\""); 

        String answer = scanner.nextLine();

        if (answer.equals("Y")) {
            System.out.println("->decreaseSpores()");
            System.out.println("Spores destroyed");
            System.out.println("Hyphal is speeding up development");
        }
    }

    /**
     * A gombafonal öregedését kezeli.
     * Csökkenti a gombafonal élettartamát, majd interaktívan megkérdezi a felhasználótól,
     * hogy ez volt-e az utolsó élettartam pont. Ha igen, a gombafonal elhal.
     */
    public void aging() {
        System.out.println("->update");
        System.out.println("Hyphals lifetime decreased by one."); 
        
        System.out.println("Was this the Hyphals last life point? Y/N"); 
        
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();

        if (answer.equals("Y")) {
            System.out.println("Hyphal dies"); 
        } 
    }

    /**
     * Frissíti a gombafonal állapotát.
     * A metódus a Listener interfészből származik, és kiírja a konzolra, hogy a gombafonal frissítve lett.
     */
    @Override
    public void update() {
        System.out.println("Hyphal is updated");
    }

    /**
     * Növeli a gombafonal élettartamát.
     * A metódus kiírja a konzolra, hogy az élettartam a maximumra lett feltöltve.
     */
    public void increaseLifetime() {
        System.out.println("-> increaseLifetime()");
        System.out.println("Hyphal lifetime is refilled to max");
    }
}