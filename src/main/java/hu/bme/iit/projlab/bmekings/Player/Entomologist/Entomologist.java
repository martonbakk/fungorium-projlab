package hu.bme.iit.projlab.bmekings.Player.Entomologist;

import java.util.List;
import java.util.ArrayList;

import hu.bme.iit.projlab.bmekings.Entities.Fungal.Hyphal;
import hu.bme.iit.projlab.bmekings.Entities.Insect.Insect;
import hu.bme.iit.projlab.bmekings.Interface.SporeInterface.SporeInterface;
import hu.bme.iit.projlab.bmekings.Logger.Loggable;
import hu.bme.iit.projlab.bmekings.Logic.IDGenerator.IDGenerator;
import hu.bme.iit.projlab.bmekings.Map.Tecton.Tecton;
import hu.bme.iit.projlab.bmekings.Player.Player;
import hu.bme.iit.projlab.bmekings.Program.Params;

@Loggable("Entomologist")
public class Entomologist extends Player{
    private ArrayList<Insect> controlledInsects = new ArrayList<>();
    private Insect selectedInsect=null;

    public Entomologist(String id) {
        super(id);
        System.out.println("Uj objektum [" + getPlayerID() + "] hozzaadva!");
    }

    public Entomologist() {
        super(IDGenerator.generateID("E"));
        System.out.println("Uj objektum [" + getPlayerID() + "] hozzaadva!");
    }
    public List<Insect> getControlledInsects() { return controlledInsects; }

    @Loggable
    @Override
    public void SelectAction(int actionType, Params param ){
        switch (actionType) {
            case 1:
                selectInsect(param.selectedInsect);
                break;/*
            case 2:
                // selectTecton
                break;*/
            case 2:
                MoveInsect(param.selectedTecton);
                break;
            case 3:
                EatSpore();
                break;
            case 4:
                CutHyphalInsect(param.selectedHyphal);
                break;
            default:
                System.out.println("Invalid action type");
        }
    }

    @Loggable
    public void MoveInsect(Tecton tectonToStepOn){
        selectedInsect.move(tectonToStepOn);
        selectedInsect=null;
    }

    @Loggable
    public void EatSpore() {
        selectedInsect.eatSpore();
        selectedInsect=null;

    }

    @Loggable
    public void CutHyphalInsect(Hyphal hyphalToCut){
        System.out.println("Cutting hyphal: " + hyphalToCut.getId() + " with insect: " + this.getPlayerID());
        selectedInsect.cutHyphal(hyphalToCut);
        selectedInsect=null;
    }

    @Loggable
    public void deleteControlledInsect(Insect controlledInsect){
        controlledInsects.remove(controlledInsect);
    }

    //placeholder !!!
    @Loggable
    public void addInsect(Insect insect){
        controlledInsects.add(insect);
    }

    @Loggable
    public void selectInsect(Insect insect){
        for (Insect controlledInsect : this.controlledInsects) {
            if (controlledInsect.getId().equals(insect.getId())) {
                this.selectedInsect = controlledInsect;
                break;  // egyszerre egyhez tudjuk hozzaadni ne menjen tovabb a loop
            }
        }
    }

}