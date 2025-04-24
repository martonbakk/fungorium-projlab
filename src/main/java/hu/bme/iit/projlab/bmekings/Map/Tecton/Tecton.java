package hu.bme.iit.projlab.bmekings.Map.Tecton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;
import java.util.LinkedList;
import java.util.List;

import hu.bme.iit.projlab.bmekings.Entities.Fungal.Hyphal;
import hu.bme.iit.projlab.bmekings.Entities.Fungal.FungalBody;
import hu.bme.iit.projlab.bmekings.Interface.SporeInterface.SporeInterface;
import hu.bme.iit.projlab.bmekings.Player.Entomologist.Entomologist;
import hu.bme.iit.projlab.bmekings.Player.Mycologist.Mycologist;
import hu.bme.iit.projlab.bmekings.Logic.IDGenerator.IDGenerator;


/**
 * A Tecton osztály a Fungorium bolygó felszínét alkotó egységeket reprezentálja, amelyek különböző
 * tulajdonságokkal rendelkezhetnek. Felelős a játéktér struktúrájának biztosításáért, a rajta lévő spórák
 * tárolásáért, valamint a tektonok gombafonal menti kapcsolódásának kezeléséért.
 * Implementálja a Listener interfészt, így minden leszármazottjának biztosítania kell az update() metódus implementációját.
 */
public class Tecton {
    private Queue<SporeInterface> spores = new LinkedList<>();
    public ArrayList<Tecton> neighbours = new ArrayList<>();
    public HashMap<Tecton, ArrayList<Hyphal>> connectedNeighbours = new HashMap<>();
    
    private String id;
    private double splitChance;
    private boolean occupiedByInsect;
    private boolean occupiedByFungalBody; 
    private FungalBody fungalBody;

    public String getId() { return id; }

    public double getSplitChance() { return splitChance; }

    public boolean isOccupiedByFungus() { return occupiedByFungalBody; }

    public boolean isOccupiedByInsect() { return occupiedByInsect; }

    public Queue<SporeInterface> getSpores() { return spores; }

    public ArrayList<Tecton> getNeighbors() { return neighbours; }

    public HashMap<Tecton, ArrayList<Hyphal>> getConnectedNeighbors() { return connectedNeighbours; }

    public SporeInterface getNextSporeToEat() { return spores.peek(); }

    public void setOccupiedByFungus(boolean value) { occupiedByFungalBody = value; }

    public Tecton() {
        this.id=IDGenerator.generateID("T");
        this.splitChance=0;
        this.occupiedByInsect=false;
        this.occupiedByFungalBody=false;
    }

    public Tecton(String id, double splitChance, boolean occupiedByInsect, boolean occupiedByFungalBody) {
        this.id=id;
        this.splitChance=splitChance;
        this.occupiedByInsect=occupiedByInsect;
        this.occupiedByFungalBody=occupiedByFungalBody;
        

    }

    public void addSpore(SporeInterface spore) {
        spores.add(spore);
    }

    public boolean createFungalBody(Mycologist player) {
        if (fungalBody != null) {
            //fungalBody = new FungalBody(player.getTypeCharacteristics(),12,this);
        }//minden
        return true; // valamit muszáj visszaadnunk
    }

    public FungalBody destroyFungalBody(){
        return fungalBody;
    }

    public ArrayList<SporeInterface> decreaseSpore(int sporesNeed) {
        ArrayList<SporeInterface> sporeList = null;
        if (spores.size() < sporesNeed) return null; 

        for (int i = 0; i < sporesNeed; i++) {
            sporeList.add(spores.poll());
        }
        return sporeList;
    }

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
    }

    public void connectTecton(Tecton tc, Hyphal newHyphal) {
        //connectedNeighbours.append(tc, );
    }

    public void runSpecialEffect() {}

    // még nagyon nem végleges, csak a generateMaphez kell valami
    public boolean createInsect(Entomologist player){
        if(!occupiedByInsect){
            // itt kreáljuk
            return true;
        } else {
            // itt nem
            return false;
        }
    }

}