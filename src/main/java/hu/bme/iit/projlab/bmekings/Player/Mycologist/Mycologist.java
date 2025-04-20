package hu.bme.iit.projlab.bmekings.Player.Mycologist;

import java.util.List;

import hu.bme.iit.projlab.bmekings.Entities.Fungal.FungalBody;
import hu.bme.iit.projlab.bmekings.Entities.Fungal.Hyphal;
import hu.bme.iit.projlab.bmekings.Interface.SporeInterface.SporeInterface;
import hu.bme.iit.projlab.bmekings.Map.Tecton.Tecton;
import hu.bme.iit.projlab.bmekings.Player.Player;
import hu.bme.iit.projlab.bmekings.Program.Params;

public class Mycologist extends Player{
    private List<FungalBody> controlledFunguses;
    private FungalBody selectedFungus;
    private Hyphal selectedHyphal;
    // Fungus type lekezelese hianyzik

    public Mycologist(String playerId) {
        super(playerId);
    }

    @Override
    public void SelectAction(int actionType, Params params) {
        switch (actionType) {
            case 1:
                selectFungus(params.selectedFungus);
                break;
            case 2:
                selectHyphal(params.selectedHyphal);
                break;
            case 3:
                growFungus(params.selectedFungus);
                break;
            case 4:
                growHyhal(params.selectedTecton);
                break;
            case 5:
                destroyFungus(params.selectedFungus);
                break;
            case 6:
                shootSpore(params.selectedTectons);
                break;
            case 7:
                addSpore(params.sporeToAdd);
                break;
            default:
                System.out.println("Invalid action type");
        }
    }

    private boolean checkControlledFunguses(){
        boolean checkControlledFungus= this.controlledFunguses.isEmpty();
        if (checkControlledFungus) {
            System.out.println("Nem növesztettél még gombatestet...");   
        }
        return checkControlledFungus;
    }

    private boolean checkControlledFungus(){
        boolean checkControlledFungus= this.selectedFungus == null;
        if (checkControlledFungus) {
            System.out.println("Nem választottál ki gombatestet...");
        }
        return checkControlledFungus;
    }

    private boolean checkControlledFungusHyphal(){
        boolean checkControlledFungus= this.selectedFungus.getHyphalList().isEmpty();
        if (checkControlledFungus) {
            System.out.println("Nincs a gombatestnek fonala...");
        }
        return checkControlledFungus;
    }

    private boolean checkSelectedHyhpal(){
        boolean checkControlledFungus= this.selectedHyphal == null;
        if (checkControlledFungus) {
            System.out.println("Nem választottál ki fonalat...");
        }
        return checkControlledFungus;
    }

    private void addSpore(SporeInterface sporeToAdd){
        if(checkControlledFungus()){
            return;
        }
        this.selectedFungus.AddSpore(sporeToAdd);
    }

    private void shootSpore(List<Tecton> selectedTectons){
        if(checkControlledFungus()){
            return;
        }
        for (Tecton tecton : selectedTectons) {
            this.selectedFungus.shootSpore(tecton);
        }
    }

    private void selectFungus(FungalBody fungus){
        if(checkControlledFunguses()){
            return;
        }

        for (FungalBody controlledFungus : this.controlledFunguses) {
            if (controlledFungus.getId().equals(fungus.getId())) {
                this.selectedFungus = controlledFungus;
                break;  // egyszerre egyhez tudjuk hozzaadni ne menjen tovabb a loop
            }
        }
    }

    private void growFungus(FungalBody fungus){
        if (fungus.getBase().isOccupiedByFungus()){
            System.out.println("Tecton already has a fungus...");
            return;
        }else{
            fungus.getBase().setOccupiedByFungus(true);
            this.controlledFunguses.add(fungus);
        }
    }

    private void selectHyphal(Hyphal hyphal){
        if (checkControlledFungus()){
            return;
        }

        if(checkControlledFungusHyphal()){
            return;
        }

        for (Hyphal controlledHyphal : this.selectedFungus.getHyphalList()) {
            if (controlledHyphal.getId().equals(hyphal.getId())) {
                this.selectedHyphal = controlledHyphal;
                break;  // egyszerre egyhez tudjuk hozzaadni ne menjen tovabb a loop
            }
        }
    }

    private void growHyhal(Tecton tecton){
        if (checkControlledFungus()){
            return;
        }
        this.selectedFungus.growHyphal(tecton);
    }

    private void destroyFungus(FungalBody fungalToDestroy){
        if (checkControlledFungus()){
            return;
        }
        // Check, hogy tényleg az a tektone az
        if (fungalToDestroy.getBase().isOccupiedByFungus()){
            fungalToDestroy.getBase().setOccupiedByFungus(false);
            this.controlledFunguses.remove(fungalToDestroy);
        }
    }
   
   
    // getTypeCharacteristics(){}
}