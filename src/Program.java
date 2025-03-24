import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * A Program osztály a játék fő belépési pontját biztosítja, és egy interaktív tesztkörnyezetet valósít meg.
 * Lehetővé teszi különböző játékmechanizmusok (pl. rovar mozgása, spóra fogyasztása, gombafonal növesztése)
 * tesztelését egy egyszerű konzolos felületen keresztül.
 */
public class Program {

    /** A játékobjektumokat tároló HashMap, amely kulcs-érték párokban menti az objektumokat a nevük alapján. */
    private static HashMap<String, Object> objectsMap = new HashMap<>();

    /**
     * A program fő metódusa, amely elindítja a tesztkörnyezetet.
     * Egy ciklusban inicializálja a játékobjektumokat, kiírja a tesztlehetőségeket, és a felhasználó
     * választása alapján futtatja a megfelelő tesztesetet.
     * @param args A parancssori argumentumok (jelenleg nem használt).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            initialize();
            printTestCases();
            
            String answer = scanner.nextLine();

            switch(answer) {
                case "0":
                    return;
                case "1":
                    moveInsect();
                    break;
                case "2":
                    insectEatsSpore();
                    break;
                case "3":
                    insectCutHyphal();
                    break;
                case "4":
                    speedUpHyphalDevelopment();
                    break;
                case "5":
                    splitTecton();
                    break;
                case "6":
                    fungalBodyShootsSpore();
                    break;
                case "7":
                    growHyphal();
                    break;
                case "8":
                    growFungalBody();
                    break;
                case "9":
                    keepHyphalAlive();
                    break;
                case "10":
                    hyphalAging();
                    break;
                default:
                    System.out.println("Nincs ilyen teszt.");
                    break;
            }
        }
    }

    /**
     * Inicializálja a játékobjektumokat, például tektonokat, spórákat, rovarokat, gombafonalakat és gombatesteket.
     * Az objektumokat a HashMap-be helyezi, hogy később könnyen elérhetők legyenek a tesztesetek számára.
     */
    private static void initialize() {
        Tecton t1 = new Tecton();
        ToxicTecton tt1 = new ToxicTecton();
        WeakTecton wt1 = new WeakTecton();
        NoFungusTecton nft1 = new NoFungusTecton();

        t1.neighbours.add(nft1);
        nft1.neighbours.add(t1);

        NormalSpore nsp1 = new NormalSpore();
        SlowSpore slowsp1 = new SlowSpore();
        SpeedSpore speedsp1 = new SpeedSpore();
        StunSpore stunsp1 = new StunSpore();
        HyphalProtectorSpore hpsp1 = new HyphalProtectorSpore();
        HungerSpore hsp1 = new HungerSpore();

        Insect i1 = new Insect();
        Hyphal h1 = new Hyphal();
        Hyphal h2 = new Hyphal();
        FungalBody fgb1 = new FungalBody();

        objectsMap.put("Tecton", t1);
        objectsMap.put("ToxicTecton", tt1);
        objectsMap.put("WeakTecton", wt1);
        objectsMap.put("NoFungusTecton", nft1);
        objectsMap.put("NormalSpore", nsp1);
        objectsMap.put("SlowSpore", slowsp1);
        objectsMap.put("SpeedSpore", speedsp1);
        objectsMap.put("StunSpore", stunsp1);
        objectsMap.put("HyphalProtectorSpore", hpsp1);
        objectsMap.put("HungerSpore", hsp1);
        objectsMap.put("Insect", i1);
        objectsMap.put("Hyphal", h1);
        objectsMap.put("Hyphal2", h2);
        objectsMap.put("FungalBody", fgb1);
    }

    /**
     * Egy tekton kettéhasadását teszteli.
     * Létrehoz egy Map objektumot, és meghívja annak splitTecton() metódusát.
     */
    private static void splitTecton() {
        Map map = new Map();
        map.splitTecton();
    }

    /**
     * Egy rovar spóra elfogyasztását teszteli.
     * Lekéri a rovart a HashMap-ből, és meghívja annak eatSpore() metódusát.
     */
    private static void insectEatsSpore() {
        Insect insect = (Insect)objectsMap.get("Insect");
        insect.eatSpore();
    }
    
    /**
     * Egy rovar mozgását teszteli.
     * Lekéri a rovart a HashMap-ből, és meghívja annak move() metódusát.
     */
    private static void moveInsect() {
        Insect insect = (Insect)objectsMap.get("Insect");
        insect.move();
    }
    
    /**
     * Egy rovar gombafonal elvágását teszteli.
     * Lekéri a rovart a HashMap-ből, és meghívja annak cutHyphal() metódusát.
     */
    private static void insectCutHyphal() {
        Insect insect = (Insect)objectsMap.get("Insect");
        insect.cutHyphal();
    }

    /**
     * Egy gombafonal fejlődésének felgyorsítását teszteli.
     * Lekéri a gombafonalat a HashMap-ből, és meghívja annak speedUpDevelopment() metódusát.
     */
    private static void speedUpHyphalDevelopment() {
        Hyphal hyphal = (Hyphal)objectsMap.get("Hyphal");
        hyphal.speedUpDevelopment();
    }

    /**
     * Egy gombatest spóralövését teszteli.
     * Lekéri a gombatestet a HashMap-ből, és meghívja annak shootSpore() metódusát.
     */
    private static void fungalBodyShootsSpore() {
        FungalBody fungus = (FungalBody)objectsMap.get("FungalBody");
        fungus.shootSpore();
    }

    /**
     * A gombafonalak életben tartását teszteli egy gombatest által.
     * Lekéri a gombatestet és a gombafonalakat a HashMap-ből, majd meghívja a gombatest
     * keepHyphalAlive() metódusát a gombafonalak listájával.
     */
    private static void keepHyphalAlive() {
        FungalBody fungus = (FungalBody)objectsMap.get("FungalBody");
        Hyphal hyphal1 = (Hyphal)objectsMap.get("Hyphal");
        Hyphal hyphal2 = (Hyphal)objectsMap.get("Hyphal2");

        ArrayList<Hyphal> hyphallist = new ArrayList<>();
        
        hyphallist.add(hyphal1);
        hyphallist.add(hyphal2);

        fungus.keepHyphalAlive(hyphallist);
    }

    /**
     * Egy gombafonal növesztését teszteli.
     * Lekéri a tektonot a HashMap-ből, és meghívja annak connectTecton() metódusát.
     */
    private static void growHyphal() {
        Tecton t1 = (Tecton)objectsMap.get("Tecton");
        t1.connectTecton();
    }

    /**
     * Egy gombatest növesztését teszteli.
     * Lekéri a gombafonalat a HashMap-ből, és meghívja annak growFungus() metódusát.
     */
    private static void growFungalBody() {
        Hyphal hyphal = (Hyphal)objectsMap.get("Hyphal");
        hyphal.growFungus();
    }

    /**
     * Egy gombafonal öregedését és elhalását teszteli.
     * Lekéri a gombafonalat a HashMap-ből, és meghívja annak aging() metódusát.
     */
    private static void hyphalAging() {
        Hyphal hyphal1 = (Hyphal)objectsMap.get("Hyphal");
        hyphal1.aging();
    }

    /**
     * Kiírja a konzolra a választható teszteseteket.
     * A felhasználó ezek közül választhat egy szám megadásával, amely meghatározza,
     * hogy melyik játékmechanizmust teszteli a program.
     */
    private static void printTestCases() {
        System.out.println("Choose Test");
        System.out.println("0. Exit");
        System.out.println("1. Move Insect");
        System.out.println("2. Insect Eats Spore");
        System.out.println("3. Insect Cuts Hyphal");
        System.out.println("4. Speed up Hyphal Development");
        System.out.println("5. Split Tecton");
        System.out.println("6. FungalBody Shoots Spore");
        System.out.println("7. Grow Hyphal");
        System.out.println("8. Grow FungalBody");
        System.out.println("9. Keep Connected Hyphals Alive");
        System.out.println("10. Hyphal dies due to aging");
    }
}