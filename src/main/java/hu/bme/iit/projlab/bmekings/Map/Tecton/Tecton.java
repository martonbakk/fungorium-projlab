package hu.bme.iit.projlab.bmekings.Map.Tecton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import hu.bme.iit.projlab.bmekings.Entities.Fungal.FungalBody;
import hu.bme.iit.projlab.bmekings.Entities.Fungal.Hyphal;
import hu.bme.iit.projlab.bmekings.Entities.Insect.Insect;
import hu.bme.iit.projlab.bmekings.Interface.Listener.Listener;
import hu.bme.iit.projlab.bmekings.Interface.SporeInterface.SporeInterface;
import hu.bme.iit.projlab.bmekings.Logger.Loggable;
import hu.bme.iit.projlab.bmekings.Logic.GameLogic.GameLogic;
import hu.bme.iit.projlab.bmekings.Logic.IDGenerator.IDGenerator;
import hu.bme.iit.projlab.bmekings.Player.Mycologist.Mycologist;


/**
 * A Tecton osztály a Fungorium bolygó felszínét alkotó egységeket reprezentálja, amelyek különböző
 * tulajdonságokkal rendelkezhetnek. Felelős a játéktér struktúrájának biztosításáért, a rajta lévő spórák
 * tárolásáért, valamint a tektonok gombafonal menti kapcsolódásának kezeléséért.
 * Implementálja a Listener interfészt, így minden leszármazottjának biztosítania kell az update() metódus implementációját.
 */

@Loggable("Tecton")
public class Tecton implements Listener{
    private Queue<SporeInterface> spores = new LinkedList<>();
    public ArrayList<Tecton> neighbours = new ArrayList<>();
    public HashMap<Tecton, ArrayList<Hyphal>> connectedNeighbours = new HashMap<>();
    private ArrayList<Insect> insects = new ArrayList<>();
    
    private String id;
    private double splitChance;
    private boolean occupiedByInsect;
    private boolean occupiedByFungalBody; 
    private FungalBody fungalBody;

    protected Flags flags;

    private int posX = -1;
    private int posY = -1;
    
    public void setPosition(int x, int y) {
        this.posX = x;
        this.posY = y;
    }
    
    public int getPosX() {
        return posX;
    }
    
    public int getPosY() {
        return posY;
    }

    public class Flags{
        public boolean fungalApproved;
        public boolean hyphalApproved;
        public boolean oneHyphalApproved;

        public Flags(){
            fungalApproved=false;
            hyphalApproved=false;
            oneHyphalApproved=false;
        }
    }

    public Flags getFlag() { return flags; }

    @Loggable
    public String getId() { return id; }

    @Loggable
    public double getSplitChance() { return splitChance; }

    @Loggable
    public boolean isOccupiedByFungus() { return occupiedByFungalBody; }

    @Loggable
    public boolean isOccupiedByInsect() { return occupiedByInsect; }

    @Loggable
    public Queue<SporeInterface> getSpores() { return spores; }

    @Loggable
    public ArrayList<Tecton> getNeighbors() { return neighbours; }

    @Loggable
    public HashMap<Tecton, ArrayList<Hyphal>> getConnectedNeighbors() { return connectedNeighbours; }

    @Loggable
    public SporeInterface getNextSporeToEat() {  return spores.peek(); }
    
    @Loggable
    public FungalBody getFungalBody() { return fungalBody; }

    @Loggable
    public void setOccupiedByFungus(boolean value) { occupiedByFungalBody = value; }

    @Loggable
    public void setSplitChance(double d) { 
        System.out.println("[" + id + "] [splitChance] megvaltozott:");
        System.out.println("[" + splitChance + "] -> [" + d + "]");
        splitChance = d;
    }

    public Tecton() {
        this.id=IDGenerator.generateID("T"); 
        this.splitChance=0;
        this.occupiedByInsect=false;
        this.occupiedByFungalBody=false;
        this.flags=new Flags();
        System.out.println("Uj objektum [" + id + "] letrejott!");
    }

    public Tecton(double splitChance, boolean occupiedByInsect, boolean occupiedByFungalBody) {
        this.id=IDGenerator.generateID("T");
        this.splitChance=splitChance;
        this.occupiedByInsect=occupiedByInsect;
        this.occupiedByFungalBody=occupiedByFungalBody;
        this.flags=new Flags();

        System.out.println("Uj objektum [" + id + "] letrejott!");
    }

    @Loggable
    public void addSpore(SporeInterface spore) {
        spores.add(spore);
    }

    @Loggable
    public boolean createFungalBody(Mycologist player) {
        //if tectontype alllows

        if(this.isOccupiedByFungus())
            return false;
                                 
        FungalBody newfungalBody = new FungalBody(1, 0, player.getTypeCharacteristics(), this, player);

        GameLogic.addEntity(fungalBody);

        occupiedByFungalBody=true;

        fungalBody=newfungalBody;

        player.addFungus(newfungalBody);
        
        return true;
    }

    @Loggable
    public boolean createFungalBodyFromSpore(Mycologist player) {
        if(this.isOccupiedByFungus())
            return false;

        if (spores.size()<2)
            return false;
        spores.peek().destroySpore();
        spores.peek().destroySpore();           
                               
        return createFungalBody(player);
    }

    /// hianyos
    @Loggable
    public void destroyFungalBody(){
        fungalBody=null;
        setOccupiedByFungus(false);
    }

    @Loggable
    public ArrayList<SporeInterface> decreaseSpore(int sporesNeed) {
        ArrayList<SporeInterface> sporeList = null;
        if (spores.size() < sporesNeed) return null; 

        for (int i = 0; i < sporesNeed; i++) {
            sporeList.add(spores.poll());
        }
        return sporeList;
    }
    /*
    public void disconnectTecton(Tecton tc, Hyphal hyphal) {
        for (HashMap.Entry<Tecton, ArrayList<Hyphal>> entry : connectedNeighbours.entrySet()) {
            Tecton key = entry.getKey();
            ArrayList<Hyphal> value = entry.getValue();

            if (key.equals(tc)) {
                // Itt történik valami, ha a Tecton kulcs megegyezik a tc-vel
                System.out.println("Találtunk egyezést: " + tc);
                for (Hyphal h : value) {
                    if (hyphal.equals(h)){
                        /// Ezt kell törölni
                        /// Kell a hyphalnak is egy destroy függvény
                        key.disconnectTecton(this,hyphal);
                        this.connectedNeighbours.remove(key,h);
                    }
                }
            }
        }
    }*/

    @Loggable
    public void disconnectTecton(Tecton tc, Hyphal hyphal) {
        // Ellenőrizzük, hogy a tc létezik-e a connectedNeighbours-ben
        ArrayList<Hyphal> hyphals = connectedNeighbours.get(tc);
        if (hyphals != null) {
        // Töröljük a megadott hyphal-t a listából
            hyphals.remove(hyphal);
        // Ha a lista üres lett, töröljük a Tecton kulcsot a HashMap-ből
            if (hyphals.isEmpty()) {
            connectedNeighbours.remove(tc);
            }
        // Hívjuk meg a másik Tecton disconnectTecton metódusát (reciprok kapcsolat)
            tc.disconnectTecton(this, hyphal);
        // Opcionálisan: hívjuk a Hyphal destroy metódusát, ha létezik
        }
    }

    @Loggable
    public void connectTecton(Tecton connected, Mycologist owner) {

        // Szerintem így egy fokkal jobb, de még mindig nem jó az összekötés
        if (!connected.getNeighbors().contains(this)) {
            System.out.println("Megkapott tekton nem szomszédos!");
            return;
        }
        Hyphal newHyphal = new Hyphal(connected, false, 3000, 3000, 300, this, owner);

        // Ellenőrizzük, hogy a két tekton össze van e már kötve
        ArrayList<Hyphal> hyphals = connectedNeighbours.get(connected);
        if (hyphals != null) {
            // Ha össze van frissítsük a hyphal listát
            hyphals.add(newHyphal);
            //connectedNeighbours.put(connected, hyphals);
        }
        // Ha még nincs, vegyük fel a HashMapbe
        else {
            hyphals = new ArrayList<>();
            hyphals.add(newHyphal);
            connectedNeighbours.put(connected, hyphals);
        }

        owner.addHyphal(newHyphal);
        GameLogic.addEntity(newHyphal);
    }

    @Loggable
    public Flags runSpecialEffect() {
        return this.flags;
    }
/*
    // még nagyon nem végleges, csak a generateMaphez kell valami
    public boolean createInsect(Entomologist player){
        if(!occupiedByInsect){
            // itt kreáljuk
            return true;
        } else {
            // itt nem
            return false;
        }
    }*/

    @Loggable
    public void addInsect(Insect insect){
        occupiedByInsect=true;
        insects.add(insect);
    }

    @Loggable
    public void removeSpore(SporeInterface spore){
        spores.remove();
    }

    @Loggable
    public void removeNeighbor(Tecton tecton){
        neighbours.remove(tecton);
    }

    @Loggable
    public void setNeighbors(ArrayList<Tecton> newNeighbors){
        neighbours = newNeighbors;
    }
    
    public void update() {
        runSpecialEffect();
    }

}