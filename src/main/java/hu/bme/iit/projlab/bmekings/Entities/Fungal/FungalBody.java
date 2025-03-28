package hu.bme.iit.projlab.bmekings.Entities.Fungal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import hu.bme.iit.projlab.bmekings.Entities.Entity;
import hu.bme.iit.projlab.bmekings.Interface.SporeInterface.SporeInterface;
import hu.bme.iit.projlab.bmekings.Map.Tecton.Tecton;

/**
 * A FungalBody osztály a gombatestek adatait tárolja, és a fejlődéssel valamint spóraszórással kapcsolatos
 * műveleteket kezeli. A gombatestek a játék kulcselemei, amelyek spórákat termelnek, és terjesztik a gombafonalakat.
 * Az Entity absztrakt osztályból származik, így örökli annak attribútumait és metódusait.
 */
public class FungalBody extends Entity {

    /** A gombatest által kilőtt spórák listája. */
    private Queue<SporeInterface> spores;
    private ArrayList<Hyphal> hyphalList;
    private int currLevel;              // jelenlegi szint 
    private int shotSporesNum;          // a kiloheto sporak szama
    private TypeCharacteristics characteristics;
    private int callNum;                // hívasok száma, ugye a leveUpnal mindig 3 hívás egyenlő egy szintel (currLevel)
    
    public FungalBody() {
        super();  

        this.characteristics = new TypeCharacteristics(0, 0, 0, 0);
        this.hyphalList=new ArrayList<>();
        this.spores = new LinkedList<>();
        this.currLevel = 0;
        this.shotSporesNum = 0;
        this.callNum = 0;
    }

    public FungalBody(int currLevel, int shotSporesNum, TypeCharacteristics characteristics, ArrayList<Hyphal> hyphalList, Queue<SporeInterface> spores,String id, Tecton baseLocation){
        super(id, baseLocation);
        
        this.characteristics = characteristics;
        this.hyphalList = hyphalList;
        this.spores=spores;
        this.currLevel = shotSporesNum;
        this.shotSporesNum = currLevel;
        this.callNum = 0;
    }

    // FLAG VALTOZAS AZ UML BEN PLUSZ PARAMÉTER
    public void shootSpore(Tecton tecton) {
        for (int i=0; i<this.shotSporesNum; i++){
            tecton.addSpore(spores.poll());
        }
    }
    
    public void levelUp() {
        if (currLevel == 3) {
            System.out.println("Elérted a maximális szintet (3)!");
            return;
        }

        this.callNum= callNum + 1;
        
        if (callNum == 3) {
            callNum = 0;
            this.currLevel++;
            this.characteristics.shootingRange += 1;
            this.characteristics.sporeProductionIntensity += 1;
            this.characteristics.sporeCapacity += 5; 
            System.out.println("Szintlépés történt! Új szint: " + currLevel);
        } else {
            System.out.println("Még " + (3 - callNum) + " hívás kell a szintlépéshez. " + "Jelenlegi szint: " + currLevel);
        }
    }

    // MYCOLOGIST FOGJA KEZELNI A FONALAKAT ES A TESTEKET
    /*
     * Koncepció a következő:
     * A fonál base és szomszéd tektonját elmentjük, végig megynük utána a szomszéd szomszédjával. 
     */
    public void keepHyphalAlive() {
        HashMap<Tecton, Set<Tecton>> neighbours = new HashMap<>();
        Set<Hyphal> hyphalSet = new HashSet<>();
        Set<Tecton> visited = new HashSet<>();
        Queue<Tecton> queue = new LinkedList<>();

        Tecton startTecton = this.baseLocation;
        queue.add(startTecton);
        visited.add(startTecton);

        while (!queue.isEmpty()) {
            Tecton currentTecton = queue.poll();
            for (Hyphal hyphal : this.hyphalList) {
                Tecton base = hyphal.getBase();
                Tecton connected = hyphal.getConnectedTecton();
                if (base.equals(currentTecton) || connected.equals(currentTecton)) {
                    hyphalSet.add(hyphal);
        
                    neighbours.computeIfAbsent(base, k -> new HashSet<>()).add(connected);
                    neighbours.computeIfAbsent(connected, k -> new HashSet<>()).add(base);
                    
                    Tecton nextTecton = base.equals(currentTecton) ? connected : base;
                    if (!visited.contains(nextTecton)) {
                        visited.add(nextTecton);
                        queue.add(nextTecton);
                    }
                }
            }
        }

        for (Hyphal hyphal : hyphalSet) {
            hyphal.setLifeTime(3);
        }
    }

    @Override
    public void update() {
        keepHyphalAlive();
    }

    private class TypeCharacteristics{
        int shootingRange;
        int sporeProductionIntensity;
        int startingHyphalNum;
        int sporeCapacity;

        public TypeCharacteristics(int shootingRange, int sporeProductionIntensity, int startingHyphalNum, int sporeCapacity) {
            this.shootingRange = shootingRange;
            this.sporeProductionIntensity = sporeProductionIntensity;
            this.startingHyphalNum = startingHyphalNum;
            this.sporeCapacity = sporeCapacity;
        }
    }
}