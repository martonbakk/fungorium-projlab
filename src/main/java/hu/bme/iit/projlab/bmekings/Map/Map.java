package hu.bme.iit.projlab.bmekings.Map;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import hu.bme.iit.projlab.bmekings.Entities.Fungal.Hyphal;
import hu.bme.iit.projlab.bmekings.Entities.Insect.Insect;
import hu.bme.iit.projlab.bmekings.Entities.Spore.DuplicateSpore;
import hu.bme.iit.projlab.bmekings.Entities.Spore.HungerSpore;
import hu.bme.iit.projlab.bmekings.Entities.Spore.Spore;
import hu.bme.iit.projlab.bmekings.Entities.Spore.StunSpore;
import hu.bme.iit.projlab.bmekings.Logger.Loggable;
import hu.bme.iit.projlab.bmekings.Logic.GameLogic.GameLogic;
import hu.bme.iit.projlab.bmekings.Map.Tecton.HyphalPreserverTecton;
import hu.bme.iit.projlab.bmekings.Map.Tecton.NoFungusTecton;
import hu.bme.iit.projlab.bmekings.Map.Tecton.Tecton;
import hu.bme.iit.projlab.bmekings.Map.Tecton.ToxicTecton;
import hu.bme.iit.projlab.bmekings.Map.Tecton.WeakTecton;
import hu.bme.iit.projlab.bmekings.Player.Entomologist.Entomologist;
import hu.bme.iit.projlab.bmekings.Player.Mycologist.Mycologist;

/**
 * A Map osztály felelős a játékterep generálásáért, a tektonok kezelésére, azok kettéhasadásának
 * lebonyolításáért, valamint a térkép frissítéséért. A játékban lévő tektonokat egy listában tárolja,
 * és biztosítja a játék térbeli struktúrájának fenntartását.
 */

@Loggable("Map")
public class Map implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private ArrayList<Tecton> tectons = new ArrayList<>();
    private boolean generatedMap=false;
    

    public Map(){

    }

    public boolean isGeneratedMap() {
        return generatedMap;
    }

    @Loggable
    public void addTecton(Tecton t) {
        tectons.add(t);
    }

    @Loggable
    public void generateMap() {
        System.out.println("Generating map...");
        if(generatedMap) {
            tectons.clear(); // Map already generated
        }
        generatedMap=true;
        ArrayList<Mycologist> mycologists = GameLogic.getMycologists();
        ArrayList<Entomologist> entomologists = GameLogic.getEntomologists();
        Random random = new Random();
    
        System.err.println("Mycologists: " + mycologists.size() + ", Entomologists: " + entomologists.size());
        // Generate Tectons
        int tectonCount = 126;

        // if(((mycologists.size() + entomologists.size()) >= 2) && ((mycologists.size() + entomologists.size()) < 6)){
        //     tectonCount = 44;
        // }else if (((mycologists.size() + entomologists.size()) >= 6) && ((mycologists.size() + entomologists.size()) <= 12)){
        //     tectonCount = 88;
        // }else{
        //     System.err.println("Invalid number of players. Map generation failed.");
        // }


        for (int i = 0; i < tectonCount; i++) {
            int type = random.nextInt(5);
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
                    tc = new WeakTecton(false, 0, false, false); // Verify constructor signature
                    break;
                default:
                    tc = new Tecton(0, false, false);
                    break;
            }
            tectons.add(tc);
        }
        /*
        // Set up neighbors
        for (int i = 0; i < tectons.size(); i++) {
            for (int j = i + 1; j < tectons.size(); j++) {
                if (random.nextInt(10) < 2) {
                    Tecton tectonA = tectons.get(i);
                    Tecton tectonB = tectons.get(j);
                    tectonA.neighbours.add(tectonB);
                    tectonB.neighbours.add(tectonA);
                }
            }
        }
        */

        //temporary neighbor logic
        Integer[] array1 = {0, 14, 28, 42, 56, 70, 84, 98, 112};
        Integer[] array2 = {13, 27, 41, 55, 69, 83, 97, 111, 125}; 
        ArrayList<Integer> leftBorder = new ArrayList<Integer>(Arrays.asList(array1));
        ArrayList<Integer> rightBorder = new ArrayList<Integer>(Arrays.asList(array2));
        for (Integer i = 0; i < tectons.size(); i++) {
            // Add Right neighbor
            if(!(rightBorder.contains(i)))
                tectons.get(i).neighbours.add(tectons.get(i+1));
            // Add Down neighbor
            if (!(i == 112 || i == 113 || i == 114 || i == 115 || i == 116 || i == 117 || i == 118 || i == 119 || i == 120 || i == 121 || i == 122 || i == 123 || i == 124 || i == 125))
                tectons.get(i).neighbours.add(tectons.get(i+14));
            // Add Left Neighbor
            if(!(leftBorder.contains(i)))
                tectons.get(i).neighbours.add(tectons.get(i-1));
            // Add Up neighbor
            if (!(i == 0 || i == 1 || i == 2 || i == 3 || i == 4 || i == 5 || i == 6 || i == 7 || i == 8 || i == 9 || i == 10 || i == 11 || i == 12 || i == 13))
                    tectons.get(i).neighbours.add(tectons.get(i-14));
        }


        for (Mycologist mycologist : mycologists) {
            for (int j = 0; j < 2; j++) {
                boolean success = false;
                while (!success) {
                    int idx = random.nextInt(tectons.size());
                    Tecton baseTecton = tectons.get(idx);
                    success = baseTecton.createFungalBody(mycologist);
                }
            }
        }
    
        // Place insects on unique tectons using a copy
        ArrayList<Tecton> availableTectons = new ArrayList<>(tectons); // Copy
        for (Entomologist entomologist : entomologists) {
            if (availableTectons.isEmpty()) break; // No available tectons
            int idx = random.nextInt(availableTectons.size());
            Tecton baseTecton = availableTectons.remove(idx);
            Insect insect = new Insect(1, 0, 1, 1, 1, baseTecton, entomologist);
            baseTecton.addInsect(insect);
        }
    
        // Add spores for each entomologist (3 per entomologist)
        for (Entomologist entomologist : entomologists) {
            for (int j = 0; j < 3; j++) { // Fixed loop variable
                int idx = random.nextInt(tectons.size());
                Tecton baseTecton = tectons.get(idx);
                int type = random.nextInt(7);
                Spore sp;
                switch (type) {
                    case 0:
                        sp = new DuplicateSpore(baseTecton);
                        break;
                    case 1:
                        sp = new HungerSpore(baseTecton);
                        break;
                    case 2:
                        sp = new StunSpore(baseTecton);
                        break;
                    case 3:
                        sp = new HungerSpore(baseTecton);
                        break;
                    case 4:
                        sp = new HungerSpore(baseTecton);
                        break;
                    default:
                        sp = new HungerSpore(baseTecton);
                        break;
                }
                
                sp.spawnSpore();
            }
        }
        assignTectonPositions();
    }
    private void assignTectonPositions() {
        int centerX = 500;
        int centerY = 400;
        int radius = 200;

        boolean[] visited = new boolean[tectons.size()];
        java.util.Map<Tecton, Integer> indexMap = new java.util.HashMap<>();
        for (int i = 0; i < tectons.size(); i++) {
            indexMap.put(tectons.get(i), i);
        }

        ArrayList<Tecton> queue = new ArrayList<>();
        if (tectons.isEmpty()) return;

        queue.add(tectons.get(0));
        visited[0] = true;
        tectons.get(0).setPosition(centerX, centerY);

        while (!queue.isEmpty()) {
            Tecton current = queue.remove(0);
            int cx = current.getPosX();
            int cy = current.getPosY();

            ArrayList<Tecton> neighbors = current.getNeighbors();
            int n = neighbors.size();
            for (int i = 0; i < n; i++) {
                Tecton neighbor = neighbors.get(i);
                int idx = indexMap.get(neighbor);
                if (!visited[idx]) {
                    double angle = 2 * Math.PI * i / n;
                    int nx = cx + (int) (120 * Math.cos(angle));
                    int ny = cy + (int) (120 * Math.sin(angle));
                    neighbor.setPosition(nx, ny);
                    visited[idx] = true;
                    queue.add(neighbor);
                }
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
        for (Tecton connected : tecton.getConnectedNeighbors().keySet()) {
            for (Hyphal hyphal : tecton.getConnectedNeighbors().get(connected)){
                connected.disconnectTecton(tecton, hyphal);
            }
        }
        for(Tecton neighbor : tecton.getNeighbors()){
            neighbor.removeNeighbor(tecton);
        }
        tectons.remove(tecton);
        System.out.println("Tecton torlodott id: ["+ tecton.getId() +"]");
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
        tectons.get(tectons.size() - 1).setNeighbors(newNeighbors1);
        tectons.add(new Tecton());
        tectons.get(tectons.size() - 1).setNeighbors(newNeighbors2);
        
    }

    public ArrayList<Tecton> getTectons() {
        return tectons;
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