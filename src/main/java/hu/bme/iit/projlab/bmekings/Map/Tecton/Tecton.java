package hu.bme.iit.projlab.bmekings.Map.Tecton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;
import java.util.LinkedList;

import hu.bme.iit.projlab.bmekings.Entities.Fungal.Hyphal;
import hu.bme.iit.projlab.bmekings.Entities.Fungal.FungalBody;
import hu.bme.iit.projlab.bmekings.Interface.SporeInterface.SporeInterface;

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
    

    public Tecton() {
        this.id="";
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

    public ArrayList<Tecton> getConnectedNeighbors() {
        return new ArrayList<>();
    }

    public void setOccupiedByFungus(boolean b) {
        if (b && !occupiedByFungalBody) {
            FungalBody newFungalBody = new FungalBody();
            occupiedByFungalBody = true;
        } else if (!b){
            
        }
    }

    public ArrayList<SporeInterface> decreaseSpore(int sporesNeed) {
        ArrayList<SporeInterface> sporeList = null;
        if (spores.size() < sporesNeed) return null; 

        for (int i = 0; i < sporesNeed; i++) {
            sporeList.add(spores.poll());
        }
        return sporeList;
    }


    public void disconnectTecton(Tecton tc) {
    }

    public void connectTecton(Tecton tc) {

    }

    public ArrayList<Tecton> getNeighbors() {
        return new ArrayList<Tecton>();
    }

    public SporeInterface getNextSporeToEat(){return spores.peek();}

    public void runSpecialEffect() {}
    

}