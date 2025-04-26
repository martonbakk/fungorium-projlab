package hu.bme.iit.projlab.bmekings.Map;

import java.util.ArrayList;
import java.util.Random;

import hu.bme.iit.projlab.bmekings.Entities.Fungal.FungalBody;
import hu.bme.iit.projlab.bmekings.Entities.Spore.*;
import hu.bme.iit.projlab.bmekings.Logic.GameLogic.GameLogic;
import hu.bme.iit.projlab.bmekings.Map.Tecton.*;
import hu.bme.iit.projlab.bmekings.Map.Tecton.Tecton;
import hu.bme.iit.projlab.bmekings.Player.Entomologist.*;
import hu.bme.iit.projlab.bmekings.Player.Mycologist.*;

/**
 * A Map osztály felelős a játékterep generálásáért, a tektonok kezelésére, azok kettéhasadásának
 * lebonyolításáért, valamint a térkép frissítéséért. A játékban lévő tektonokat egy listában tárolja,
 * és biztosítja a játék térbeli struktúrájának fenntartását.
 */
public class Map {
    ArrayList<Tecton> tectons = new ArrayList<>();

    public Map(){

    }

    public void addTecton(Tecton t) {
        tectons.add(t);
    }

    public void generateMap(GameLogic gameLogic) {
        
        ArrayList<Mycologist> mycologists = gameLogic.getMycologists();
        ArrayList<Entomologist> entomologists = gameLogic.getEntomologists();
        Random random = new Random();
        for (int i = 0; i < (mycologists.size() + entomologists.size()) * 4; i++) {
            int type = random.nextInt(5); // Generates 0 to 4
        Tecton tc;
        switch (type) {
        case 0:
            tc = new HyphalPreserverTecton("T-" + i + 1, 0, false, false);
            break;
        case 1:
            tc = new NoFungusTecton("T-" + i + 1, 0, false, false);
            break;
        case 2:
            tc = new ToxicTecton(6, "T-" + i + 1, 0, false, false);
            break;
        case 3:
            tc = new WeakTecton(false, "T-" + i + 1, 0, false, false);
            break;
        default: 
            tc = new Tecton("T-" + i + 1, 0, false, false);
            break;
        }   //random uj tektonokat bele kell rakni
            tectons.add(tc);
        }
        // egy pár gombatest + fonál + rovar + spóra
        for (int i = 0; i < mycologists.size(); i++){
            for(int j = 0; j < 2; j++){
                int idx = random.nextInt(tectons.size());
                Tecton baseTecton = tectons.get(idx);
                if(!baseTecton.createFungalBody(mycologists.get(i), "generateMap")){
                    j--;
                }
            }
        }
        for (int i = 0; i < entomologists.size(); i++){
            int idx = random.nextInt(tectons.size());
            Tecton baseTecton = tectons.get(idx);
            if(!baseTecton.createInsect(entomologists.get(i))){
                i--;
            }
            for(int j = 0; i < 3; i++){
                idx = random.nextInt(tectons.size());
                baseTecton = tectons.get(idx);
                int type = random.nextInt(7);
                Spore sp;
                switch (type) {
                case 0:
                    sp = new DuplicateSpore();
                    break;
                case 1:
                    sp = new HungerSpore();
                    break;
                case 2:
                    sp = new StunSpore();
                    break;
                case 3:
                    sp = new HyphalProtectorSpore();
                    break;
                case 4:
                    sp = new SpeedSpore();
                    break;
                case 5:
                    sp = new StunSpore();
                    break;
                default: 
                    sp = new NormalSpore();
                    break;
                }
                baseTecton.addSpore(sp);
            }
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


    public ArrayList<Tecton> getAllTectons(){
        
        return tectons;

    }


}