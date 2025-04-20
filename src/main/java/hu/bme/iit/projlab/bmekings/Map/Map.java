package hu.bme.iit.projlab.bmekings.Map;

import java.util.ArrayList;

import hu.bme.iit.projlab.bmekings.Map.Tecton.Tecton;

/**
 * A Map osztály felelős a játékterep generálásáért, a tektonok kezelésére, azok kettéhasadásának
 * lebonyolításáért, valamint a térkép frissítéséért. A játékban lévő tektonokat egy listában tárolja,
 * és biztosítja a játék térbeli struktúrájának fenntartását.
 */
public class Map {
    ArrayList<Tecton> tectons = new ArrayList<>();

    public Map(){

    }

    public void generateMap(int player) {
        for (int i = 0; i < player; i++) {
            Tecton tc = new Tecton();   //random uj tektonokat bele kell rakni
            tectons.add(tc);
        }
    }

    public void splitTecton(Tecton tc) {

    }

    public void updateTectons() {

    }

    public ArrayList<Tecton> getTectons(ArrayList<String> tectonIds) {
        ArrayList<Tecton> result = new ArrayList<>();
        for (Tecton tecton : tectons) {
            for (String id : tectonIds) {
                if (tecton.getId().equals(id)) {
                    result.add(tecton);
                }
            }
        }
        return result;
    }

}