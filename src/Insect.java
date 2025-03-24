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

    /**
     * A rovar spóra elfogyasztását kezeli.
     * Interaktívan megkérdezi a felhasználótól, hogy vannak-e spórák a tektonon, és ha igen,
     * milyen típusú spórát fogyaszt el a rovar. A spóra típusától függően aktiválja annak hatását.
     */
    public void eatSpore() {
        System.out.println("-> eatSpore()");
        Scanner var1 = new Scanner(System.in);
        System.out.println("-> decreaseSpores()");
        System.out.println("Are there any Spores on the Tecton? Y/N");
        String var2 = var1.nextLine();
        if (var2.equals("Y")) {
            System.out.println("What type of Spore is on the Tecton? \n\t1. Normal\t2.Stun\t3.Slow\t4.Speed\t5.Hunger\t6.HyphalProtector");
            var2 = var1.nextLine();
            System.out.println("-> activateEffect()");
        } else if (var2.equals("N")) {
            System.out.println("No Spore to eat!");
        } else {
            System.out.println("Wrong input");
        }
    }

    /**
     * A rovar gombafonal elvágását kezeli.
     * Interaktívan megkérdezi a felhasználótól, hogy a vágás képessége cooldown-on van-e.
     * Ha nincs cooldown, a rovar elvágja a gombafonalat, különben hibaüzenetet ír ki.
     */
    public void cutHyphal() {
        System.out.println("-> cutHyphal()");
        Scanner var1 = new Scanner(System.in);
        System.out.println("Is cut on cooldown? Y/N");
        String var2 = var1.nextLine();
        if (var2.equals("Y")) {
            System.out.println("Can't cut");
        } else if (var2.equals("N")) {
            System.out.println("-> disconnectTecton()");
        } else {
            System.out.println("Wrong input");
        }
    }

    /**
     * Frissíti a rovar állapotát.
     * A metódus a Listener interfészből származik, de ebben az implementációban üres.
     */
    @Override
    public void update() {
    }
}