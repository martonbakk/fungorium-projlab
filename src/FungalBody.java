import java.util.ArrayList;
import java.util.Scanner;

/**
 * A FungalBody osztály a gombatestek adatait tárolja, és a fejlődéssel valamint spóraszórással kapcsolatos
 * műveleteket kezeli. A gombatestek a játék kulcselemei, amelyek spórákat termelnek, és terjesztik a gombafonalakat.
 * Az Entity absztrakt osztályból származik, így örökli annak attribútumait és metódusait.
 */
public class FungalBody extends Entity {

    /** A gombatest által kilőtt spórák listája. */
    ArrayList<SporeInterface> spores = new ArrayList<>();

    /**
     * A FungalBody osztály konstruktora.
     * Meghívja az ősosztály konstruktorát, és kiírja a konzolra, hogy egy új FungalBody objektum jött létre.
     */
    public FungalBody() {
        super();
        System.out.println("\t new FungalBody");
    }

    /**
     * A gombatest spórákat lő ki a számára elérhető szomszédos tektonokra.
     * A metódus a baseLocation szomszédjaira helyezi a spórákat, és kiírja a konzolra a művelet lépéseit.
     */
    public void shootSpore() {
        System.out.println("-> shootSpore()");
        baseLocation.getNeighbor();
        baseLocation.neighbours.get(0).addSpore();
        System.out.println("-> Spore()");
        baseLocation.neighbours.get(1).addSpore();
        System.out.println("-> Spore()");
    }

    /**
     * A gombatest szintjének növelését végzi.
     * A metódus interaktívan kérdezi a felhasználótól, hogy szeretne-e szintet lépni (Y/N/S opciók),
     * és kezeli a gombatest szintjének változását (maximum 3-as szintig).
     */
    public void levelUp() {
        System.out.println("-> levelUp()");
        int allapot = 1;  
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Do you want to Level up? Y/N/S?");
            
            String answer = scanner.nextLine().toUpperCase();
            
            if (answer.equals("Y")) {
                if (allapot < 3) {
                    allapot += 1;
                    System.out.println("Your fungus is now lvl " + allapot);
                } else {
                    System.out.println("Cannot level up! Your fungus is already at maximum level (3)");
                }
            }
            else if (answer.equals("N")) {
                System.out.println("Your fungus remains at lvl " + allapot);
            }
            else if (answer.equals("S")) {
                System.out.println("Stopping. Final level: " + allapot);
                break;
            }
        }
        scanner.close();
    }

    /**
     * Értesíti a gombafonalakat, hogy a gombatest életben tartja őket, így növeli azok élettartamát.
     * A metódus végigmegy a kapott gombafonalak listáján, és mindegyik élettartamát megnöveli.
     * @param hyphalList A gombafonalak listája, amelyeket életben kell tartani.
     */
    public void keepHyphalAlive(ArrayList<Hyphal> hyphalList) {
        System.out.println("-> keepHyphalAlive()");
        System.out.println("-> getConnectedHyphals()");

        for (Hyphal element : hyphalList) {
            element.increaseLifetime();
        }
    }

    /**
     * Frissíti a gombatest állapotát.
     * A metódus a Listener interfészből származik, és kiírja a konzolra, hogy a gombatest frissítve lett.
     */
    @Override
    public void update() {
        System.out.println("-> update()");
        System.out.println("FungalBody updated");
    }
}