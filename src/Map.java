import java.util.ArrayList;
import java.util.Scanner;

/**
 * A Map osztály felelős a játékterep generálásáért, a tektonok kezelésére, azok kettéhasadásának
 * lebonyolításáért, valamint a térkép frissítéséért. A játékban lévő tektonokat egy listában tárolja,
 * és biztosítja a játék térbeli struktúrájának fenntartását.
 */
public class Map {

    /** A térképet alkotó tektonok listája. */
    ArrayList<Tecton> tectons = new ArrayList<>();

    /**
     * Létrehozza a játékteret, inicializálja a tektonokat, spórákat és egyéb játékobjektumokat.
     * A metódus különböző típusú tektonokat (pl. ToxicTecton, WeakTecton) és spórákat (pl. NormalSpore, SlowSpore)
     * hoz létre, valamint példányosít egy rovart, gombafonalat és gombatestet.
     */
    public void generateMap() {
        System.out.println("-> generateMap()");
    }

    /**
     * Kezeli egy tekton kettéhasadását.
     * Interaktívan megkérdezi a felhasználótól, hogy van-e rovar a tektonon. Ha igen, a tekton nem hasadhat szét.
     * Ha nincs rovar, a metódus megsemmisíti a gombatestet, és frissíti a tektonokat.
     */
    public void splitTecton() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("-> splitTecton()");
        System.out.println("Is there an Insect on the Tecton? Y/N");

        String answer = scanner.nextLine();
        if (answer.equals("Y")) {
            System.out.println("Tecton cannot be split!");
        } else if (answer.equals("N")) {
            System.out.println("Destroy FungalBody");
            System.out.println("-> updateTectons()");
            updateTectons();
        } else {
            System.out.println("Wrong input");
        }
    }

    /**
     * Frissíti a térképen lévő tektonok állapotát.
     * A metódus megsemmisíti a gombatestet, leválasztja a tektonokat, és új tektonokat hoz létre.
     */
    public void updateTectons() {
        System.out.println("Destroy FungalBody");
        disconnectTecton();
        Tecton t1 = new Tecton();
        Tecton t2 = new Tecton();
    }

    /**
     * Leválaszt egy tekton szomszédjait.
     * A metódus létrehoz egy új tekton objektumot, lekéri annak szomszédjait, és szimulálja a szomszédok leválasztását.
     */
    private void disconnectTecton() {
        Tecton tct = new Tecton();
        tct.getConnectedNeighbors();
        System.out.println("Disconnect t1 neighbours");
        System.out.println("Destroy Hyphals");
    }
}