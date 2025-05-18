package hu.bme.iit.projlab.bmekings.Entities.Fungal;

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
import hu.bme.iit.projlab.bmekings.Entities.Spore.HyphalProtectorSpore;
import hu.bme.iit.projlab.bmekings.Entities.Spore.NormalSpore;
import hu.bme.iit.projlab.bmekings.Entities.Spore.SlowSpore;
import hu.bme.iit.projlab.bmekings.Entities.Spore.SpeedSpore;
import hu.bme.iit.projlab.bmekings.Entities.Spore.Spore;
import hu.bme.iit.projlab.bmekings.Entities.Spore.StunSpore;
import hu.bme.iit.projlab.bmekings.Interface.SporeInterface.SporeInterface;
import hu.bme.iit.projlab.bmekings.Logger.Loggable;
import hu.bme.iit.projlab.bmekings.Logic.GameLogic.GameLogic;
import hu.bme.iit.projlab.bmekings.Logic.IDGenerator.IDGenerator;
import hu.bme.iit.projlab.bmekings.Map.Tecton.Tecton;
import hu.bme.iit.projlab.bmekings.Player.Mycologist.Mycologist;


/**
 * A FungalBody osztály a gombatestek adatait tárolja, és a fejlődéssel valamint spóraszórással kapcsolatos
 * műveleteket kezeli. A gombatestek a játék kulcselemei, amelyek spórákat termelnek, és terjesztik a gombafonalakat.
 * Az Entity absztrakt osztályból származik, így örökli annak attribútumait és metódusait.
 */

@Loggable("FungalBody")
public class FungalBody extends Entity {

    /** A gombatest által kilőtt spórák listája. */ 
    private Queue<SporeInterface> spores = new LinkedList<>();
    private int currLevel;              // jelenlegi szint 
    private int shotSporesNum;          // a kiloheto sporak szama
    private TypeCharacteristics characteristics=new TypeCharacteristics(0, 0, 0, 0);
    private int callNum;                // hívasok száma, ugye a leveUpnal mindig 3 hívás egyenlő egy szintel (currLevel)
    private Mycologist owner;

    //kosntrukktorba az owner felvetele!!!
    public FungalBody() {
        super();  

        //this.id=IDGenerator.generateID("FB");
        this.currLevel = 1;
        this.shotSporesNum = 2; // elején 8
        this.callNum = 0;
    }

    public FungalBody(int currLevel, int shotSporesNum, TypeCharacteristics characteristics, Tecton baseLocation, Mycologist owner){
        super(IDGenerator.generateID("FB"), baseLocation);
        this.characteristics = characteristics;
        this.currLevel = currLevel;
        this.shotSporesNum = shotSporesNum;
        this.callNum = 0;
        this.owner=owner;
    }

    /// occupiedByFungalbody-hoz kell
    public FungalBody(TypeCharacteristics characteristics, Tecton baseLocation){
        super(IDGenerator.generateID("FB"),baseLocation); 
        
        this.characteristics = characteristics;
        this.spores = new LinkedList<>();
        this.currLevel = 1;
        this.callNum = 0;
    }

    public Queue<SporeInterface> getSpores(){
        return spores;
    }

    @Loggable
    public int getCurrLvl() { return currLevel; }

    @Loggable
    public int getShotSporesNum() { return shotSporesNum; }

    @Loggable
    public TypeCharacteristics getCharacteristics() { return characteristics; }
    
    @Loggable
    public Mycologist getOwner() { return owner; }

    @Loggable
    public void setLvlNoWrite(int lvl) { currLevel = lvl; }

    @Loggable
    public void setShotSporesNumNoWrite(int s) { shotSporesNum = s; }

    // objektum_neve [attributum] megvaltozott:
    // [regi_ertek] -> [új_érték]
    @Loggable
    public void setLevel(int lvl) { 
    System.out.println("[" + this.getId() + "] [currlevel] megvaltozott:");
    System.out.println("[" + this.currLevel + "] -> [" + lvl + "]"); 
    this.currLevel = lvl;
    }

    @Loggable
    public void setShotSporesNum(int s) { 
    System.out.println("[" + this.getId() + "] [shotSporesNum] megvaltozott:");
    System.out.println("[" + shotSporesNum + "] -> [" + s + "]");
    shotSporesNum = s; 
    }

    @Loggable
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
        throw new RuntimeException("A gombatest nem tudja elérni a megadott tekton-t!");
    }

    // FLAG VALTOZAS AZ UML BEN PLUSZ PARAMÉTER
    @Loggable
    public void shootSpore(Tecton tecton) {
        if(checkShootingRange(tecton)) {
            return;
        }

        if(this.spores.isEmpty()) {
            throw new RuntimeException("Nincs spóra a gombatestben!");
            
        }
        
        for (int i = 0; i < this.shotSporesNum; i++) {
            // Ez kiveszi a Spore láncolt listából a legújabb spórát, amit az AddSpore random generált
            SporeInterface spore = this.spores.poll();
            spore.setBaseLocation(tecton);
            tecton.addSpore(spore);
            
            if (this.spores.isEmpty()) {
                System.err.println("Nincs több spóra a gombatestben!");
                break;
            }
        }
    }

    public Spore getRandomSpore(Tecton tecton){
        Random rand = new Random();
        int value = rand.nextInt(7);
        Spore resultSpore;
        switch (value) {
            case 0:
                resultSpore=new NormalSpore(tecton);
                break;
            
            case 1:
                resultSpore=new HungerSpore(tecton);
                break;

            case 2:
                resultSpore=new HyphalProtectorSpore(tecton);
                break;

            case 3:
                resultSpore=new HyphalProtectorSpore(tecton);
                break;

            case 4:
                resultSpore=new DuplicateSpore(tecton);
                break;

            case 5:
                resultSpore=new SlowSpore(tecton);
                break;

            case 6:
                resultSpore=new SpeedSpore(tecton);
                break;

            default:
                resultSpore=new NormalSpore(tecton);
                break;
        }
        return resultSpore;
    }


    @Loggable
    public void levelUp() {
        if (currLevel == 3) {
            System.out.println("Elérted a maximális szintet (3)!");
            this.shotSporesNum++;
            return;
        }
        
        this.callNum= this.callNum + 1;
        
        if (callNum == 3) {
            this.callNum = 0;
            this.characteristics.shootingRange += 1;
            this.characteristics.sporeProductionIntensity += 1;
            this.characteristics.sporeCapacity += 5; 
            int nextlvl = currLevel + 1;
            this.setLevel(nextlvl);
        }
        // else {
        //     System.out.println("Még " + (3 - callNum) + " hívás kell a szintlépéshez. " + "Jelenlegi szint: " + currLevel);
        // }
    }

    @Loggable
    public void keepHyphalAlive() {
        HashMap<Tecton, Set<Tecton>> neighbours = new HashMap<>();
        Set<Hyphal> hyphalSet = new HashSet<>();
        Set<Tecton> visited = new HashSet<>();
        Queue<Tecton> queue = new LinkedList<>();
        
        ArrayList<Hyphal> hyphalList = owner.getHyphalList();

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

    @Loggable
    @Override
    public void update() {
        keepHyphalAlive();
        AddSpore();

        this.shotSporesNum = 10; // A kilőhető spórák számát vissza kell állítani a kezdeti értékre
    }

    @Loggable
    public void AddSpore() {
        SporeInterface spore;

        Random random = new Random();
        int sporeType = random.nextInt(11); 
        // default a NormalSpore mert arra legyen a legtöbb esély
        
        switch (sporeType) {
            case 0:
                spore = new SlowSpore(baseLocation);
                break;
            case 1:
                spore = new DuplicateSpore(baseLocation); 
                break;
            case 2:
                spore = new HungerSpore(baseLocation);
                break;
            case 3:
                spore = new SpeedSpore(baseLocation); 
                break;
            case 4:
                spore = new StunSpore(baseLocation); 
                break;
            default:
                spore = new NormalSpore(baseLocation); 
                break;
            }

        spores.add(spore);
    }


    /*
     * Ezt itt csekkoljátok le, nem fix, hogy jó....
     * Mindenféleképp a csekk logikától az összekötésig, hogy a BFS jól működjön
     */
    @Loggable
    public void growHyphal(Tecton connected) {
        if (this.baseLocation.connectedNeighbours.get(connected)!=null){
            throw new RuntimeException("Ez a fonál már létezik!");
        }
        baseLocation.connectTecton(connected, owner);
    }

    @Loggable
    public void destroyFungus(){
        // gombasz
        //Mycologist owner = this.getOwner();
        owner.destroyFungus(this);

        // entity
        GameLogic.deleteEntity(this);

        // tecton 
        this.baseLocation.destroyFungalBody();

        System.out.println("Fungus objektum torlodott id: [" + this.getId() + "]");

    }
    
}