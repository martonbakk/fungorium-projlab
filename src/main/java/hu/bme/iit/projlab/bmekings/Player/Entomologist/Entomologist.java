package hu.bme.iit.projlab.bmekings.Player.Entomologist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import hu.bme.iit.projlab.bmekings.Entities.Fungal.Hyphal;
import hu.bme.iit.projlab.bmekings.Entities.Insect.Insect;
import hu.bme.iit.projlab.bmekings.Logger.Loggable;
import hu.bme.iit.projlab.bmekings.Logic.IDGenerator.IDGenerator;
import hu.bme.iit.projlab.bmekings.Map.Tecton.Tecton;
import hu.bme.iit.projlab.bmekings.Player.Player;
import hu.bme.iit.projlab.bmekings.Program.Params;

@Loggable("Entomologist")
public class Entomologist extends Player {
    private ArrayList<Insect> controlledInsects = new ArrayList<>();
    private Insect selectedInsect = null;

    public Entomologist() {
        super("E");
        System.out.println("Uj objektum [" + getPlayerID() + "] hozzaadva!");
    }

    public Entomologist(String userName, String type) {
        super(IDGenerator.generateID("E"));
        this.userName = userName;
        this.type = type;
        System.out.println("Uj objektum [" + getPlayerID() + "] hozzaadva!");
    }

    public Entomologist(String userName) {
        super(IDGenerator.generateID("E"));
        this.userName = userName;
        this.type = null;
        System.out.println("Uj objektum [" + getPlayerID() + "] hozzaadva!");
    }

    @Override
    public List<String> getAvailableActions() { return Arrays.asList("Skip","Eat Spore", "Move", "Cut Hyphal"); }

    public List<Insect> getControlledInsects() { return controlledInsects; }

    public Insect getSelectedInsect() { return selectedInsect; }

    @Loggable
    @Override
    public void SelectAction(int actionType, Params param ){
        switch (actionType) {
            case 1:
                selectInsect(param.selectedInsect);
                break;
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
    private boolean checkSelectedInsect() {
        boolean checkSelectedInsect = this.selectedInsect == null;
        if (checkSelectedInsect) {
            throw new RuntimeException("Nem választottál ki rovart...");
        }

        return checkSelectedInsect;
    }

    @Loggable
    public void MoveInsect(Tecton tectonToStepOn) {
        if (checkSelectedInsect()) {
            return;
        }
        selectedInsect.move(tectonToStepOn);
        selectedInsect=null;
    }

    @Loggable
    public void EatSpore() {
        if (checkSelectedInsect()) {
            return;
        }
        selectedInsect.eatSpore();
        selectedInsect=null;
        
        
    }

    @Loggable
    public void CutHyphalInsect(Hyphal hyphalToCut) {
        if (checkSelectedInsect()) {
            return;
        }
        if (hyphalToCut == null) {
            throw new RuntimeException("Nem választottál ki fonalat!");
        }
        selectedInsect.cutHyphal(hyphalToCut);
        selectedInsect = null;        
    }

    @Loggable
    public void deleteControlledInsect(Insect controlledInsect) {
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