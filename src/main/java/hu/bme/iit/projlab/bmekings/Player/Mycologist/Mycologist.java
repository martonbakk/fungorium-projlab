package hu.bme.iit.projlab.bmekings.Player.Mycologist;

import java.util.ArrayList;
import java.util.List;

import hu.bme.iit.projlab.bmekings.Entities.Fungal.FungalBody;
import hu.bme.iit.projlab.bmekings.Entities.Fungal.Hyphal;
import hu.bme.iit.projlab.bmekings.Entities.Fungal.TypeCharacteristics;
import hu.bme.iit.projlab.bmekings.Map.Tecton.Tecton;
import hu.bme.iit.projlab.bmekings.Player.Player;
import hu.bme.iit.projlab.bmekings.Program.Params;

public class Mycologist extends Player{
    private ArrayList<FungalBody> controlledFunguses;
    private ArrayList<Hyphal> hyphalList;
    private FungalBody selectedFungus;
    private Hyphal selectedHyphal;
    private TypeCharacteristics typeCharacteristics;
    
    // Fungus type lekezelese hianyzik

    public Mycologist(String playerId) {
        super(playerId);
        //this.playerId=IDGenerator.generateID("M");
        this.controlledFunguses = new ArrayList<>();
        this.hyphalList = new ArrayList<>();
        
        this.typeCharacteristics = new TypeCharacteristics();
    }

    public Mycologist(String playerId, int shootingRange, int sporeProductionIntensity, int startingHyphalNum, int sporeCapacity) {
        super(playerId);
        //this.playerId=IDGenerator.generateID("M");
        this.controlledFunguses = new ArrayList<>();
        this.hyphalList = new ArrayList<>();
        
        this.typeCharacteristics = new TypeCharacteristics(shootingRange,sporeProductionIntensity,startingHyphalNum,sporeCapacity);
    }

    public ArrayList<FungalBody> getControlledFunguses() { return controlledFunguses; }

    public ArrayList<Hyphal> getHyphalList() { return hyphalList; }

    public TypeCharacteristics getTypeCharacteristics(){ return this.typeCharacteristics; }

    public void setTypeCharacteristics(TypeCharacteristics characteristics) { typeCharacteristics = characteristics; }

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
                growFungalBody(params.selectedFungus);
                break;
            case 4:
                growHyphalAction(params.selectedTecton);
                break;
            case 5:
                destroyFungus(params.selectedFungus);
                break;
            case 6:
                shootSpore(params.selectedTectons);
                break;
            case 7:
                speedUpDevelopment(params.selectedHyphal);
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
        boolean checkControlledFungus= hyphalList.isEmpty();
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

    private void speedUpDevelopment(Hyphal selectedHyphal){
        if (checkSelectedHyhpal()){
            return;
        }
        this.selectedHyphal.speedUpDevelopment();
        this.selectedHyphal=null;
    }

    private void shootSpore(List<Tecton> selectedTectons){
        if(checkControlledFungus()){
            return;
        }
        for (Tecton tecton : selectedTectons) {
            this.selectedFungus.shootSpore(tecton);
        }
        this.selectedFungus=null;
    }

    public void growFungalBody(FungalBody fungus){
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

        for (Hyphal controlledHyphal : hyphalList) {
            if (controlledHyphal.getId().equals(hyphal.getId())) {
                this.selectedHyphal = controlledHyphal;
                break;  // egyszerre egyhez tudjuk hozzaadni ne menjen tovabb a loop
            }
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

    private void growHyphalAction(Tecton tecton){
        if (checkControlledFungus()){
            return;
        }
        this.selectedFungus.growHyphal(tecton);
        this.selectedFungus=null;
    }

    public void destroyFungus(FungalBody fungalToDestroy){
        if (checkControlledFungus()){
            return;
        }
        // Check, hogy tényleg az a tektone az
        if (fungalToDestroy.getBase().isOccupiedByFungus()){
            fungalToDestroy.getBase().setOccupiedByFungus(false);
            this.controlledFunguses.remove(fungalToDestroy);
        }
    }

    public void removeHyphal(Hyphal hyphal){
        hyphalList.remove(hyphal);
    }

    public void addHyphal(Hyphal hyphal){
        hyphalList.add(hyphal);
    }

    public void addFungus(FungalBody fungalToDestroy){
        controlledFunguses.add(fungalToDestroy);
    }

}