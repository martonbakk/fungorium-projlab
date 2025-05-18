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
public class Map implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private ArrayList<Tecton> tectons = new ArrayList<>();
    private boolean generatedMap = false;
    

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

        for (int i = 0; i < tectonCount; i++) {
            int type = random.nextInt(15);
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
            }
            tectons.add(tc);
        }

        // Set up neighbors (dont judge)
        Integer[] array1 = {0, 14, 28, 42, 56, 70, 84, 98, 112};
        Integer[] array2 = {13, 27, 41, 55, 69, 83, 97, 111, 125}; 
        ArrayList<Integer> leftBorder = new ArrayList<Integer>(Arrays.asList(array1));
        ArrayList<Integer> rightBorder = new ArrayList<Integer>(Arrays.asList(array2));
        for (Integer i = 0; i < tectons.size(); i++) {
            // Order is important because of TectonPanel!!!!
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

        tectons.get(8).setSplitChance(98);
        tectons.get(124).setSplitChance(98);
        tectons.get(66).setSplitChance(98);

        // Add FungalBodies
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
    
        // Place insects on tectons with a FungalBody using a copy
        ArrayList<Tecton> availableTectons = new ArrayList<>();
        tectons.forEach(e -> {
            if (e.isOccupiedByFungus()) {
                availableTectons.add(e);
            }
        });
        for (Entomologist entomologist : entomologists) {
            if (availableTectons.isEmpty()) break; // No available tectons
            int idx = random.nextInt(availableTectons.size());
            Tecton baseTecton = availableTectons.remove(idx);
            Insect insect = new Insect(1, 0, 100, 0, 3, baseTecton, entomologist);
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
        for(Tecton neighbor : tecton.getNeighbors()) {
            neighbor.removeNeighbor(tecton);
        }
        Random random = new Random();
        ArrayList<Tecton> leftTectonNeighbors = new ArrayList<>();
        ArrayList<Tecton> rightTectonNeighbors = new ArrayList<>();
        
        String[] splitId = tecton.getId().split("-");

        Integer[] leftArray = {15, 29, 43, 57, 71, 85, 99};
        Integer[] rightArray = {28, 42, 56, 70, 84, 98, 112};
        ArrayList<Integer> leftBorder = new ArrayList<Integer>(Arrays.asList(leftArray));
        ArrayList<Integer> rightBorder = new ArrayList<Integer>(Arrays.asList(rightArray));

        Tecton leftTecton;
        Tecton rightTecton;

        if (tecton instanceof HyphalPreserverTecton) {
            leftTecton = new HyphalPreserverTecton();
            rightTecton = new HyphalPreserverTecton();
        }
        else if (tecton instanceof NoFungusTecton) {
            leftTecton = new NoFungusTecton();
            rightTecton = new NoFungusTecton();
        }
        else if (tecton instanceof ToxicTecton) {
            leftTecton = new ToxicTecton();
            rightTecton = new ToxicTecton();
        }
        else if (tecton instanceof WeakTecton) {
            leftTecton = new WeakTecton();
            rightTecton = new WeakTecton();
        }
        else {
            leftTecton = new Tecton();
            rightTecton = new Tecton();
        }

        leftTecton.setBroken(true);
        rightTecton.setBroken(true);

        // Az első sorban tört egy tekton, de nem a szélén
        if (Integer.parseInt(splitId[1]) >= 02 && Integer.parseInt(splitId[1]) <= 13) {
            for (Tecton t : tectons) {
                String[] splitT = t.getId().split("-");

                // Bal Tekton szomszédjainak meghatározása (-1 és +14)
                if (Integer.parseInt(splitT[1]) == Integer.parseInt(splitId[1]) - 1) {
                    leftTectonNeighbors.add(t);
                    t.addNeighbor(leftTecton);
                }
                if (Integer.parseInt(splitT[1]) == Integer.parseInt(splitId[1]) + 14) {
                    leftTectonNeighbors.add(t);
                    t.addNeighbor(leftTecton);
                }

                // Jobb Tekton szomszédainak meghatározása (+1 és +14)
                if (Integer.parseInt(splitT[1]) == Integer.parseInt(splitId[1]) + 1) {
                    rightTectonNeighbors.add(t);
                    t.addNeighbor(rightTecton);
                }
                if (Integer.parseInt(splitT[1]) == Integer.parseInt(splitId[1]) + 14) {
                    rightTectonNeighbors.add(t);
                    t.addNeighbor(rightTecton);
                }
            }
        }
        // Az utolsó sorban tört egy tekton, de nem a szélén
        else if (Integer.parseInt(splitId[1]) >= 114 && Integer.parseInt(splitId[1]) <= 125) {
            for (Tecton t : tectons) {
                String[] splitT = t.getId().split("-");

                // Bal Tekton szomszédjainak meghatározása (-1 és -14)
                if (Integer.parseInt(splitT[1]) == Integer.parseInt(splitId[1]) - 1) {
                    leftTectonNeighbors.add(t);
                    t.addNeighbor(leftTecton);
                }
                if (Integer.parseInt(splitT[1]) == Integer.parseInt(splitId[1]) - 14) {
                    leftTectonNeighbors.add(t);
                    t.addNeighbor(leftTecton);
                }

                // Jobb Tekton szomszédainak meghatározása (+1 és -14)
                if (Integer.parseInt(splitT[1]) == Integer.parseInt(splitId[1]) + 1) {
                    rightTectonNeighbors.add(t);
                    t.addNeighbor(rightTecton);
                }
                if (Integer.parseInt(splitT[1]) == Integer.parseInt(splitId[1]) - 14) {
                    rightTectonNeighbors.add(t);
                    t.addNeighbor(rightTecton);
                }
            }
        }
        // Az első sor első tektonja tört
        else if (Integer.parseInt(splitId[1]) == 01) {
            for (Tecton t : tectons) {
                String[] splitT = t.getId().split("-");

                // Bal Tekton szomszédjainak meghatározása (csak +14)
                if (Integer.parseInt(splitT[1]) == Integer.parseInt(splitId[1]) + 14) {
                    leftTectonNeighbors.add(t);
                    t.addNeighbor(leftTecton);
                }

                // Jobb Tekton szomszédainak meghatározása (+1 és +14)
                if (Integer.parseInt(splitT[1]) == Integer.parseInt(splitId[1]) + 1) {
                    rightTectonNeighbors.add(t);
                    t.addNeighbor(rightTecton);
                }
                if (Integer.parseInt(splitT[1]) == Integer.parseInt(splitId[1]) + 14) {
                    rightTectonNeighbors.add(t);
                    t.addNeighbor(rightTecton);
                }
            }
        }
        // Az első sor utolsó tektonja tört
        else if (Integer.parseInt(splitId[1]) == 14) {
            for (Tecton t : tectons) {
                String[] splitT = t.getId().split("-");

                // Bal Tekton szomszédjainak meghatározása (-1 és +14)
                if (Integer.parseInt(splitT[1]) == Integer.parseInt(splitId[1]) - 1) {
                    leftTectonNeighbors.add(t);
                    t.addNeighbor(leftTecton);
                }
                if (Integer.parseInt(splitT[1]) == Integer.parseInt(splitId[1]) + 14) {
                    leftTectonNeighbors.add(t);
                    t.addNeighbor(leftTecton);
                }

                // Jobb Tekton szomszédainak meghatározása (csak +14)
                if (Integer.parseInt(splitT[1]) == Integer.parseInt(splitId[1]) + 14) {
                    rightTectonNeighbors.add(t);
                    t.addNeighbor(rightTecton);
                }
            }
        }
        // Az utolsó sor első tektonja tört
        else if (Integer.parseInt(splitId[1]) == 113) {
            for (Tecton t : tectons) {
                String[] splitT = t.getId().split("-");

                // Bal Tekton szomszédjainak meghatározása (csak -14)
                if (Integer.parseInt(splitT[1]) == Integer.parseInt(splitId[1]) - 14) {
                    leftTectonNeighbors.add(t);
                    t.addNeighbor(leftTecton);
                }

                // Jobb Tekton szomszédainak meghatározása (+1 és -14)
                if (Integer.parseInt(splitT[1]) == Integer.parseInt(splitId[1]) + 1) {
                    rightTectonNeighbors.add(t);
                    t.addNeighbor(rightTecton);
                }
                if (Integer.parseInt(splitT[1]) == Integer.parseInt(splitId[1]) - 14) {
                    rightTectonNeighbors.add(t);
                    t.addNeighbor(rightTecton);
                }
            }
        }
        // Az utolsó sor utolsó tektonja tört
        else if (Integer.parseInt(splitId[1]) == 126) {
            for (Tecton t : tectons) {
                String[] splitT = t.getId().split("-");

                // Bal Tekton szomszédjainak meghatározása (-1 és -14)
                if (Integer.parseInt(splitT[1]) == Integer.parseInt(splitId[1]) - 1) {
                    leftTectonNeighbors.add(t);
                    t.addNeighbor(leftTecton);
                }
                if (Integer.parseInt(splitT[1]) == Integer.parseInt(splitId[1]) - 14) {
                    leftTectonNeighbors.add(t);
                    t.addNeighbor(rightTecton);
                }

                // Jobb Tekton szomszédainak meghatározása (csak -14)
                if (Integer.parseInt(splitT[1]) == Integer.parseInt(splitId[1]) - 14) {
                    rightTectonNeighbors.add(t);
                    t.addNeighbor(rightTecton);
                }
            }
        }
        // Bal szélső tekton tört
        else if (leftBorder.contains(Integer.parseInt(splitId[1]))) {
            for (Tecton t : tectons) {
                String[] splitT = t.getId().split("-");

                // Bal Tekton szomszédjainak meghatározása (-14 és +14)
                if (Integer.parseInt(splitT[1]) == Integer.parseInt(splitId[1]) - 14) {
                    leftTectonNeighbors.add(t);
                    t.addNeighbor(leftTecton);
                }
                if (Integer.parseInt(splitT[1]) == Integer.parseInt(splitId[1]) + 14) {
                    leftTectonNeighbors.add(t);
                    t.addNeighbor(leftTecton);
                }

                // Jobb Tekton szomszédainak meghatározása (+1 és -14 és +14)
                if (Integer.parseInt(splitT[1]) == Integer.parseInt(splitId[1]) + 1) {
                    rightTectonNeighbors.add(t);
                    t.addNeighbor(rightTecton);
                }
                if (Integer.parseInt(splitT[1]) == Integer.parseInt(splitId[1]) - 14) {
                    rightTectonNeighbors.add(t);
                    t.addNeighbor(rightTecton);
                }
                if (Integer.parseInt(splitT[1]) == Integer.parseInt(splitId[1]) + 14) {
                    rightTectonNeighbors.add(t);
                    t.addNeighbor(rightTecton);
                }
            }
        }
        // Jobb szélső tekton tört
        else if (rightBorder.contains(Integer.parseInt(splitId[1]))) {
            for (Tecton t : tectons) {
                String[] splitT = t.getId().split("-");

                // Bal Tekton szomszédjainak meghatározása (-1 és -14 és +14)
                if (Integer.parseInt(splitT[1]) == Integer.parseInt(splitId[1]) - 1) {
                    leftTectonNeighbors.add(t);
                    t.addNeighbor(leftTecton);
                }
                if (Integer.parseInt(splitT[1]) == Integer.parseInt(splitId[1]) - 14) {
                    leftTectonNeighbors.add(t);
                    t.addNeighbor(leftTecton);
                }
                if (Integer.parseInt(splitT[1]) == Integer.parseInt(splitId[1]) + 14) {
                    leftTectonNeighbors.add(t);
                    t.addNeighbor(leftTecton);
                }

                // Jobb Tekton szomszédainak meghatározása (-14 és +14)
                if (Integer.parseInt(splitT[1]) == Integer.parseInt(splitId[1]) - 14) {
                    rightTectonNeighbors.add(t);
                    t.addNeighbor(rightTecton);
                }
                if (Integer.parseInt(splitT[1]) == Integer.parseInt(splitId[1]) + 14) {
                    rightTectonNeighbors.add(t);
                    t.addNeighbor(rightTecton);
                }
            }
        } else {
            // Középen történt a törés
            for (Tecton t : tectons) {
                String[] splitT = t.getId().split("-");

                // Bal Tekton szomszédjainak meghatározása (-1 és -14 és +14)
                if (Integer.parseInt(splitT[1]) == Integer.parseInt(splitId[1]) - 1) {
                    leftTectonNeighbors.add(t);
                    t.addNeighbor(leftTecton);
                }
                if (Integer.parseInt(splitT[1]) == Integer.parseInt(splitId[1]) - 14) {
                    leftTectonNeighbors.add(t);
                    t.addNeighbor(leftTecton);
                }
                if (Integer.parseInt(splitT[1]) == Integer.parseInt(splitId[1]) + 14) {
                    leftTectonNeighbors.add(t);
                    t.addNeighbor(leftTecton);
                }

                // Jobb Tekton szomszédainak meghatározása (+1 és -14 és +14)
                if (Integer.parseInt(splitT[1]) == Integer.parseInt(splitId[1]) + 1) {
                    rightTectonNeighbors.add(t);
                    t.addNeighbor(rightTecton);
                }
                if (Integer.parseInt(splitT[1]) == Integer.parseInt(splitId[1]) - 14) {
                    rightTectonNeighbors.add(t);
                    t.addNeighbor(rightTecton);
                }
                if (Integer.parseInt(splitT[1]) == Integer.parseInt(splitId[1]) + 14) {
                    rightTectonNeighbors.add(t);
                    t.addNeighbor(rightTecton);
                }
            }
        }
        
        leftTectonNeighbors.add(rightTecton);
        rightTectonNeighbors.add(leftTecton);

        leftTecton.setNeighbors(leftTectonNeighbors);
        rightTecton.setNeighbors(rightTectonNeighbors);
        

        tectons.add(tectons.indexOf(tecton), leftTecton);
        tectons.add(tectons.indexOf(tecton) + 1, rightTecton);
        tectons.remove(tecton);
        System.out.println("Tecton torlodott id: ["+ tecton.getId() +"]");       
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