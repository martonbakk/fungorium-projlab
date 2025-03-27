package main.java.hu.bme.iit.projlab.bmekings.Map.Tecton;
import java.util.ArrayList;

import main.java.hu.bme.iit.projlab.bmekings.Entities.Spore.Spore;
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

    public Tecton() {

    }

    public void addSpore() {

    }

    public ArrayList<Tecton> getConnectedNeighbors() {
        return new ArrayList<>();
    }

    public void setOccupiedByFungus() {

    }


    public boolean decreaseSpore() {
        return false;
    }


    public void disconnectTecton() {
    }

    public void connectTecton() {

    }

    public void getNeighbor() {
    }


    public void runSpecialEffect() {}
}