package main.java.hu.bme.iit.projlab.bmekings.Map.Tecton;
import java.util.ArrayList;
import java.util.HashMap;
import main.java.hu.bme.iit.projlab.bmekings.Entities.Fungal.Hyphal;

import main.java.hu.bme.iit.projlab.bmekings.Entities.Spore.Spore;
/**
 * A Tecton osztály a Fungorium bolygó felszínét alkotó egységeket reprezentálja, amelyek különböző
 * tulajdonságokkal rendelkezhetnek. Felelős a játéktér struktúrájának biztosításáért, a rajta lévő spórák
 * tárolásáért, valamint a tektonok gombafonal menti kapcsolódásának kezeléséért.
 * Implementálja a Listener interfészt, így minden leszármazottjának biztosítania kell az update() metódus implementációját.
 */
public class Tecton {
    public ArrayList<Spore> spores = new ArrayList<>();
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

    public void addSpore(Spore s) {

    }

    public ArrayList<Tecton> getConnectedNeighbors() {
        return new ArrayList<>();
    }

    public void setOccupiedByFungus(boolean b) {

    }


    public boolean decreaseSpore(int n, int d) {
        return false;
    }


    public void disconnectTecton(Tecton tc) {
    }

    public void connectTecton(Tecton tc) {

    }

    public ArrayList<Tecton> getNeighbors() {
        return new ArrayList<Tecton>();
    }


    public void runSpecialEffect() {}
}