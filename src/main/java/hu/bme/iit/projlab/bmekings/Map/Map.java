package hu.bme.iit.projlab.bmekings.Map;

import java.util.ArrayList;
import java.util.Random;

import hu.bme.iit.projlab.bmekings.Map.Tecton.*;
import hu.bme.iit.projlab.bmekings.Entities.Spore.*;
import hu.bme.iit.projlab.bmekings.Entities.Insect.*;
import hu.bme.iit.projlab.bmekings.Logic.GameLogic.GameLogic;
import hu.bme.iit.projlab.bmekings.Player.Entomologist.Entomologist;
import hu.bme.iit.projlab.bmekings.Player.Mycologist.Mycologist;
import hu.bme.iit.projlab.bmekings.Entities.Fungal.Hyphal;
import hu.bme.iit.projlab.bmekings.Logger.Loggable;

/**
 * A Map osztály felelős a játékterep generálásáért, a tektonok kezelésére, azok kettéhasadásának
 * lebonyolításáért, valamint a térkép frissítéséért. A játékban lévő tektonokat egy listában tárolja,
 * és biztosítja a játék térbeli struktúrájának fenntartását.
 */

@Loggable("Map")
public class Map {
    private ArrayList<Tecton> tectons = new ArrayList<>();

    

    public Map(){

    }

    @Loggable
    public void addTecton(Tecton t) {
        tectons.add(t);
    }

    @Loggable
    public void generateMap() {
        
        ArrayList<Mycologist> mycologists = GameLogic.getMycologists();
        ArrayList<Entomologist> entomologists = GameLogic.getEntomologists();
        Random random = new Random();
        for (int i = 0; i < (mycologists.size() + entomologists.size()) * 4; i++) {
            int type = random.nextInt(5); // Generates 0 to 4
        Tecton tc;
        switch (type) {
        case 0:
            tc = new HyphalPreserverTecton(0, false, false);
            break;
        case 1:
            tc = new NoFungusTecton(0, false, false);
            break;
        case 2:
            tc = new ToxicTecton(6, 0, false, false);
            break;
        case 3:
            tc = new WeakTecton(false, 0, false, false);
            break;
        default: 
            tc = new Tecton(0, false, false);
            break;
        }   //random uj tektonokat bele kell rakni
            tectons.add(tc);
        }
        // szomszédok beállítása
        for (int i = 0; i < tectons.size(); i++) {
            for (int j = i + 1; j < tectons.size(); j++) {
                if (random.nextInt(10) < 4) {
                    Tecton tectonA = tectons.get(i);
                    Tecton tectonB = tectons.get(j);
                    tectonA.neighbours.add(tectonB);
                    tectonB.neighbours.add(tectonA);
                }
            }
        }
        // egy pár gombatest + fonál + rovar + spóra
        for (int i = 0; i < mycologists.size(); i++){
            for(int j = 0; j < 2; j++){
                int idx = random.nextInt(tectons.size());
                Tecton baseTecton = tectons.get(idx);
                if(!baseTecton.createFungalBody(mycologists.get(i))){
                    j--;
                }
            }
        }
        ArrayList<Tecton> availableTectons = tectons; 
        // only used to avoid placing several insects on the same tecton in
        // the generateMap() function 
        for (int i = 0; i < entomologists.size(); i++){
            int idx = random.nextInt(availableTectons.size());
            Tecton baseTecton = availableTectons.get(idx);
            availableTectons.remove(idx);
            
            baseTecton.addInsect(new Insect(1,1,1,1,1,baseTecton,entomologists.get(i)));
            
            for(int j = 0; j < 3; j++){
                idx = random.nextInt(tectons.size());
                baseTecton = tectons.get(idx);
                int type = random.nextInt(7);
                Spore sp;
                switch (type) {
                case 0:
                    sp = new DuplicateSpore(10, baseTecton);
                    break;
                case 1:
                    sp = new HungerSpore(10, baseTecton);
                    break;
                case 2:
                    sp = new StunSpore(10, baseTecton);
                    break;
                case 3:
                    sp = new HyphalProtectorSpore(10, baseTecton);
                    break;
                case 4:
                    sp = new SpeedSpore(10, baseTecton);
                    break;
                case 5:
                    sp = new StunSpore(10, baseTecton);
                    break;
                default: 
                    sp = new NormalSpore(10, baseTecton);
                    break;
                }
                baseTecton.addSpore(sp);
            }
        }
    }

    @Loggable
    public void splitTecton(Tecton tecton) {
        if(tecton.isOccupiedByInsect())
            return;
        tecton.destroyFungalBody();
        updateTectons(tecton);
    }

    @Loggable
    public void updateTectons(Tecton tecton) {
        ArrayList<Tecton> connectedTectons = (ArrayList<Tecton>)tecton.getConnectedNeighbors().keySet();
        for(Tecton connected : connectedTectons){
            for(Hyphal hyphal : tecton.getConnectedNeighbors().get(connected)){
                connected.disconnectTecton(tecton, hyphal);
            }
        }
        for(Tecton neighbor : tecton.getNeighbors()){
            neighbor.removeNeighbor(tecton);
        }
        tectons.remove(tecton);
        Random random = new Random();
        ArrayList<Tecton> newNeighbors1 = new ArrayList<>();
        ArrayList<Tecton> newNeighbors2 = new ArrayList<>();

        for (Tecton neighbor : tecton.getNeighbors()) {
            if (random.nextBoolean()) {
                newNeighbors1.add(neighbor);
            } else {
                newNeighbors2.add(neighbor);
            }
        }
        tectons.add(new Tecton());
        tectons.getLast().setNeighbors(newNeighbors1);
        tectons.add(new Tecton());
        tectons.getLast().setNeighbors(newNeighbors2);
        
    }

    @Loggable
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


    @Loggable
    public ArrayList<Tecton> getAllTectons(){
        
        return tectons;

    }


}