import java.util.ArrayList;
import java.util.Scanner;

/**
 * A Tecton osztály a Fungorium bolygó felszínét alkotó egységeket reprezentálja, amelyek különböző
 * tulajdonságokkal rendelkezhetnek. Felelős a játéktér struktúrájának biztosításáért, a rajta lévő spórák
 * tárolásáért, valamint a tektonok gombafonal menti kapcsolódásának kezeléséért.
 * Implementálja a Listener interfészt, így minden leszármazottjának biztosítania kell az update() metódus implementációját.
 */
public class Tecton {

    /** A tektonon lévő spórák listája. */
    public ArrayList<Spore> spores = new ArrayList<>();

    /** A tekton szomszédjainak listája. */
    public ArrayList<Tecton> neighbours = new ArrayList<>();

    /** A tekton gombafonállal összekapcsolt szomszédjainak listája. */
    public ArrayList<Tecton> connectedNeighbours = new ArrayList<>();

    /**
     * A Tecton osztály konstruktora.
     * Kiírja a konzolra, hogy egy új Tecton objektum jött létre.
     */
    public Tecton() {
        System.out.println("new Tecton created");
    }

    /**
     * Hozzáad egy spórát a tekton spóráinak listájához.
     * A metódus kiírja a konzolra, hogy a spóra hozzáadódott a tektonhoz.
     */
    public void addSpore() {
        System.out.println("-> addSpore()");
        System.out.println("Spore added to tecton");
    }

    /**
     * Visszaadja a tekton gombafonállal összekapcsolt szomszédjainak listáját.
     * A metódus kiírja a konzolra, hogy a szomszédok lekérdezése megtörtént.
     * @return A gombafonállal összekapcsolt szomszédok listája.
     */
    public ArrayList<Tecton> getConnectedNeighbors() {
        System.out.println("->getConnectedNeighbors()");
        return connectedNeighbours;
    }

    /**
     * Beállítja, hogy a tektonon gombatest található.
     * A metódus létrehoz egy új FungalBody objektumot, és kiírja a konzolra, hogy a tekton gombatesttel elfoglalt.
     */
    public void setOccupiedByFungus() {
        System.out.println("-> setOccupiedByFungus()");
        FungalBody fb = new FungalBody();
    }

    /**
     * Csökkenti a tektonon lévő spórák számát, ha van elég spóra.
     * Interaktívan megkérdezi a felhasználótól, hogy van-e elég spóra a tektonon.
     * @return Igaz, ha van elég spóra és a csökkentés sikeres, különben hamis.
     */
    public boolean decreaseSpore() {
        System.out.println("-> decreaseSpore()");
        System.out.println("Is there enough Spore on the tecton? Y/N");

        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();

        if (answer.equals("Y")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Leválasztja a tekton gombafonalait.
     * A metódus kiírja a konzolra, hogy a gombafonal eltűnt.
     */
    public void disconnectTecton() {
        System.out.println("Hyphal disappeared");
    }

    /**
     * Összekapcsol két tektonot egy gombafonallal.
     * A metódus a szomszédok listájából kiválasztja az első tektonot, összekapcsolja a jelenlegi tektonnal,
     * létrehoz egy új gombafonalat, és kiírja a konzolra, hogy a tektonok összekapcsolódtak.
     */
    public void connectTecton() {
        Tecton t2;
        t2 = this.neighbours.get(0);
        System.out.println("-> connectTecton()");
        
        connectedNeighbours.add(t2);
        t2.connectedNeighbours.add(this);
        
        Hyphal h1 = new Hyphal();
        System.out.println("t1 and t2 connected!");
    }

    /**
     * Lekéri a tekton szomszédjait.
     * A metódus kiírja a konzolra, hogy a szomszédok lekérdezése megtörtént.
     */
    public void getNeighbor() {
        System.out.println("-> getNeighbor()");
    }

    /**
     * A tekton speciális hatását aktiválja.
     * Ez egy üres metódus, amelyet a leszármazottak írhatnak felül a saját speciális hatásuk implementálására.
     */
    public void runSpecialEffect() {}
}