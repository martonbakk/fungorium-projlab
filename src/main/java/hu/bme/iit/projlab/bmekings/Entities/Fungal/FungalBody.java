package hu.bme.iit.projlab.bmekings.Entities.Fungal;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

import hu.bme.iit.projlab.bmekings.Entities.Entity;
import hu.bme.iit.projlab.bmekings.Entities.Spore.DuplicateSpore;
import hu.bme.iit.projlab.bmekings.Entities.Spore.HungerSpore;
import hu.bme.iit.projlab.bmekings.Entities.Spore.NormalSpore;
import hu.bme.iit.projlab.bmekings.Entities.Spore.SlowSpore;
import hu.bme.iit.projlab.bmekings.Entities.Spore.SpeedSpore;
import hu.bme.iit.projlab.bmekings.Entities.Spore.Spore;
import hu.bme.iit.projlab.bmekings.Entities.Spore.StunSpore;
import hu.bme.iit.projlab.bmekings.Interface.SporeInterface.SporeInterface;
import hu.bme.iit.projlab.bmekings.Logic.GameLogic.GameLogic;
import hu.bme.iit.projlab.bmekings.Map.Tecton.Tecton;
import hu.bme.iit.projlab.bmekings.Player.Mycologist.Mycologist;


/**
 * A FungalBody osztály a gombatestek adatait tárolja, és a fejlődéssel valamint spóraszórással kapcsolatos
 * műveleteket kezeli. A gombatestek a játék kulcselemei, amelyek spórákat termelnek, és terjesztik a gombafonalakat.
 * Az Entity absztrakt osztályból származik, így örökli annak attribútumait és metódusait.
 */
public class FungalBody extends Entity {

    /** A gombatest által kilőtt spórák listája. */
    private Queue<SporeInterface> spores;
    private int currLevel;              // jelenlegi szint 
    private int shotSporesNum;          // a kiloheto sporak szama
    private TypeCharacteristics characteristics;
    private int callNum;                // hívasok száma, ugye a leveUpnal mindig 3 hívás egyenlő egy szintel (currLevel)

    public FungalBody() {
        super();  

        //this.id=IDGenerator.generateID("FB");
        this.characteristics = new TypeCharacteristics(0, 0, 0, 0);
        this.spores = new LinkedList<>();
        this.currLevel = 1;
        this.shotSporesNum = 0;
        this.callNum = 0;
    }

    public FungalBody(int currLevel, int shotSporesNum, TypeCharacteristics characteristics, Queue<SporeInterface> spores,String id, Tecton baseLocation){
        super(id, baseLocation);
        //this.id=IDGenerator.generateID("FB");
        this.characteristics = characteristics;
        this.spores=spores;
        this.currLevel = currLevel;
        this.shotSporesNum = shotSporesNum;
        this.callNum = 0;
    }

    public FungalBody(String id, Tecton baseLocation, int currLevel, int shotSporesNum, int shootingRange, int sporeProductionIntensity, int startingHyphalNum, int sporeCapacity) {
        super(id, baseLocation);
        //this.id=IDGenerator.generateID("FB");
        this.spores = new LinkedList<>();
        this.characteristics = new TypeCharacteristics(shootingRange, sporeProductionIntensity, startingHyphalNum, sporeCapacity);
        this.currLevel = currLevel;
        this.shotSporesNum = shotSporesNum;
        this.callNum = 0;
        System.out.println("asd");
    }

    /// occupiedByFungalbody-hoz kell
    public FungalBody(TypeCharacteristics characteristics, String id, Tecton baseLocation){
        super(id,baseLocation); 
        this.characteristics = characteristics;
        this.spores = new LinkedList<>();
        this.currLevel = 1;
        this.callNum = 0;
    }

    public int getCurrLvl() { return currLevel; }

    public int getShotSporesNum() { return shotSporesNum; }

    public TypeCharacteristics getCharacteristics() { return characteristics; }

    public Boolean checkShootingRange(Tecton tecton) {
        if (baseLocation.equals(tecton)) {
            return true;
        }
        Queue<Tecton> queue = new LinkedList<>();
        HashMap<Tecton, Integer> distances = new HashMap<>();
    
        queue.add(baseLocation);
        distances.put(baseLocation, 0);
    
        // BFS 
        while (!queue.isEmpty()) {
            Tecton current = queue.poll();
            int currentDistance = distances.get(current);
            
            if (currentDistance >= this.characteristics.shootingRange) {
                continue;
            }
            for (Tecton neighbor : current.neighbours) {
                if (!distances.containsKey(neighbor)) {
                    distances.put(neighbor, currentDistance + 1);
                    queue.add(neighbor);
    
                    
                    if (neighbor.equals(tecton)) {
                        return false;
                    }
                }
            }
        }
        System.out.println("A gombatest nem tudja elérni a megadott tekton-t!");
        return true; 
    }

    // FLAG VALTOZAS AZ UML BEN PLUSZ PARAMÉTER
    public void shootSpore(Tecton tecton) {
        if(checkShootingRange(tecton)){
            return;
        }

        if(this.spores.isEmpty()){
            System.out.println("Nincs spóra a gombatestben!");
        }
        for (int i=0; i<this.shotSporesNum; i++){
            SporeInterface spore = this.spores.poll();
            spore.setBaseLocation(tecton);
            tecton.addSpore(spore);
            this.shotSporesNum--;
            if(this.spores.isEmpty()){
                System.out.println("Nincs több spóra a gombatestben!");
                break;
            }
            if (this.shotSporesNum == 0) {
                System.out.println("Nem tudsz kilőni több spórát!");
                break;
            }
        }
    }
    
    //public ArrayList<Hyphal> getHyphalList() {
    //    return this.hyphalList;
    //}

    public void levelUp() {
        if (currLevel == 3) {
            System.out.println("Elérted a maximális szintet (3)!");
            this.shotSporesNum++;
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
        
        Mycologist player  = this.getOwner();
        
        ArrayList<Hyphal> hyphalList = player.getHyphalList();

        Tecton startTecton = this.baseLocation;
        queue.add(startTecton);
        visited.add(startTecton);

        while (!queue.isEmpty()) {
            Tecton currentTecton = queue.poll();
            for (Hyphal hyphal : hyphalList) {
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
        AddSpore();

        this.shotSporesNum=10; // A kilőhető spórák számát vissza kell állítani a kezdeti értékre DE NEM BIZTOS HOGY EZT ÍGY KÉNE
        // egy adott idokozonkent majd amugy is termel sporat itt akkor random lehetne hozzaadogatni az AddSproe-val a sporakat
    }

    public void AddSpore() {
        Random random = new Random();
        int sporeType = random.nextInt(11); 
        // default a NormalSpore mert arra legyen a legtöbb esély
        SporeInterface spore;
        switch (sporeType) {
            case 0:
                spore = new SlowSpore(); 
                break;
            case 1:
                spore = new DuplicateSpore(); 
                break;
            case 2:
                spore = new HungerSpore();
                break;
            case 3:
                spore = new SpeedSpore(); 
                break;
            case 4:
                spore = new StunSpore(); 
                break;
            default:
                spore = new NormalSpore(); 
                break;
            }

        spores.add(spore);
    }


    /*
     * Ezt itt csekkoljátok le, nem fix, hogy jó....
     * Mindenféleképp a csekk logikától az összekötésig, hogy a BFS jól működjön
     */
    public void growHyphal(Tecton connected) {
        //Tecton connectedTecton, 
        //boolean developed, 
        //int developTime, 
        //int lifeTime, 
        //int cutTime, 
        //Tecton baseLocation
        if(this.baseLocation.connectedNeighbours.get(connected)!=null){
            System.out.println("Ez a fonál már létezik!");
            return;
        }

        // Ez a beégetett konstruktor paraméter szerintem nem jó konstrukció, valahogy ezt egységesen kellene beállítani szerintem pl static-al mas ötlet?
        Hyphal newHyphal = new Hyphal(connected, false, 3000, 3000, 300,this.baseLocation);
        
        // Mindkettőhöz hozzáadjuk a másikat?? Szerintem igen
       
        // Ezt nem a tectonon belül kellene megoldani?????
        //this.baseLocation.connectedNeighbours.get(this.baseLocation).add(newHyphal);
        //connected.connectedNeighbours.get(this.baseLocation).add(newHyphal);

        

        baseLocation.connectTecton(connected, newHyphal);
        /// mycologist list
        /// gamelogic entitilist

        Mycologist player = this.getOwner();
        
        player.getHyphalList().add(newHyphal);
    }

    public class TypeCharacteristics{
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

       public int getShootingRange() { return shootingRange; }
       
       public int getSporeProductionIntensity() { return sporeProductionIntensity; }
       
       public int getStartingHyphalNum() { return startingHyphalNum; }
       
       public int getSporeCapacity() { return sporeCapacity; }
    }


    public Mycologist getOwner(){
        Mycologist player = null;
        for (Mycologist mycologist : GameLogic.getMycologists()){
            for (FungalBody fungalbody : mycologist.getControlledFunguses()){
                if (this == fungalbody){
                    player = mycologist;
                }
            }
        }
        return player;
    }
}