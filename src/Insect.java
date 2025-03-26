// Source code is decompiled from a .class file using FernFlower decompiler.
import java.util.Scanner;

/**
 * Az Insect osztály a rovarok kezelésére szolgál a játékban.
 * A rovarokat a rovarászok irányítják, és fő feladatuk a spórák elfogyasztása, valamint a gombafonalak elvágása.
 * A rovarok mozgásához gombafonalakra van szükségük, és különböző akciókat végezhetnek a játék során.
 * Az Entity absztrakt osztályból származik, így örökli annak attribútumait és metódusait.
 */
public class Insect extends Entity {

    /**
     * Az Insect osztály konstruktora.
     * Kiírja a konzolra, hogy egy új Insect objektum jött létre.
     */
    public Insect() {
        System.out.println("\tnew Insect");
    }

    /**
     * A rovar mozgását kezeli a gombafonalak mentén a tektonok között.
     * Interaktívan megkérdezi a felhasználótól, hogy a rovar képes-e mozogni, és vannak-e szomszédos tektonok,
     * amelyekhez csatlakozhat. Ha igen, a rovar lép, különben hibaüzenetet ír ki.
     */
    public void move() {
        System.out.println("-> move");
        Scanner var1 = new Scanner(System.in);
        System.out.println("-> getConnectedNeighbors()");
        System.out.println("Can the Insect move? Y/N");
        String var2 = var1.nextLine();
        if (!var2.equals("N")) {
            System.out.println("Are there any connected neighbors? Y/N");
            var2 = var1.nextLine();
            if (var2.equals("Y")) {
                System.out.println("-> step()");
            } else if (var2.equals("N")) {
                System.out.println("Nowhere to move");
            } else {
                System.out.println("Wrong input");
            }
        }
    }

    public void eatSpore() {

    }

    public void cutHyphal() {

    }


    @Override
    public void update() {
        
    }
}