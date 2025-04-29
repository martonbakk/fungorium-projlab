package hu.bme.iit.projlab.bmekings.Player.Mycologist;

import java.util.ArrayList;
import java.util.List;

import hu.bme.iit.projlab.bmekings.Entities.Fungal.FungalBody;
import hu.bme.iit.projlab.bmekings.Entities.Fungal.Hyphal;
import hu.bme.iit.projlab.bmekings.Entities.Fungal.TypeCharacteristics;
import hu.bme.iit.projlab.bmekings.Logger.Loggable;
import hu.bme.iit.projlab.bmekings.Logic.IDGenerator.IDGenerator;
import hu.bme.iit.projlab.bmekings.Map.Tecton.Tecton;
import hu.bme.iit.projlab.bmekings.Player.Player;
import hu.bme.iit.projlab.bmekings.Program.Params;

@Loggable("Mycologist")
public class Mycologist extends Player{
    private ArrayList<FungalBody> controlledFunguses;
    private ArrayList<Hyphal> hyphalList;
    private FungalBody selectedFungus;
    private Hyphal selectedHyphal;
    private TypeCharacteristics typeCharacteristics;
    
    // Fungus type lekezelese hianyzik

    public Mycologist() {
        super(IDGenerator.generateID("M"));
        this.controlledFunguses = new ArrayList<>();
        this.hyphalList = new ArrayList<>();
        this.typeCharacteristics = new TypeCharacteristics();
        System.out.println("Új objektum [" + getPlayerID() + "] hozzáadva!");
    }

    public Mycologist(int shootingRange, int sporeProductionIntensity, int startingHyphalNum, int sporeCapacity) {
        super(IDGenerator.generateID("M"));
        this.controlledFunguses = new ArrayList<>();
        this.hyphalList = new ArrayList<>();        
        this.typeCharacteristics = new TypeCharacteristics(shootingRange,sporeProductionIntensity,startingHyphalNum,sporeCapacity);
    }

    @Loggable
    public ArrayList<FungalBody> getControlledFunguses() { return controlledFunguses; }

    @Loggable
    public ArrayList<Hyphal> getHyphalList() { return hyphalList; }

    @Loggable
    public TypeCharacteristics getTypeCharacteristics(){ return this.typeCharacteristics; }

    @Loggable
    public void setTypeCharacteristics(TypeCharacteristics characteristics) { typeCharacteristics = characteristics; }

    @Loggable
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
                growFungalBody(params.selectedTecton);
                break;
            case 4:
                growFungalBodyFromSpore(params.selectedTecton);
                break;
            case 5:
                growHyphalAction(params.selectedTecton);
                break;
            case 6:
                destroyFungus(params.selectedFungus);
                break;
            case 7:
                shootSpore(params.selectedTectons);
                break;
            case 8:
                speedUpDevelopment(params.selectedHyphal);
                break;
            case 9:
                growHyphalFromHyphalAciton(params.selectedTecton);
                break;
            default:
                System.out.println("Invalid action type");
        }
    }

    @Loggable
    private boolean checkControlledFunguses(){
        boolean checkControlledFungus= this.controlledFunguses.isEmpty();
        if (checkControlledFungus) {
            System.out.println("Nem növesztettél még gombatestet...");   
        }
        return checkControlledFungus;
    }

    @Loggable
    private boolean checkControlledFungus(){
        boolean checkControlledFungus= this.selectedFungus == null;
        if (checkControlledFungus) {
            System.out.println("Nem választottál ki gombatestet...");
        }
        return checkControlledFungus;
    }

    @Loggable
    private boolean checkControlledFungusHyphal(){
        boolean checkControlledFungus= hyphalList.isEmpty();
        if (checkControlledFungus) {
            System.out.println("Nincs a gombatestnek fonala...");
        }
        return checkControlledFungus;
    }

    @Loggable
    private boolean checkSelectedHyhpal(){
        boolean checkControlledFungus= this.selectedHyphal == null;
        if (checkControlledFungus) {
            System.out.println("Nem választottál ki fonalat...");
        }
        return checkControlledFungus;
    }

    @Loggable
    private void speedUpDevelopment(Hyphal selectedHyphal){
        if (checkSelectedHyhpal()){
            return;
        }
        this.selectedHyphal.speedUpDevelopment();
        this.selectedHyphal=null;
    }

    @Loggable
    private void shootSpore(List<Tecton> selectedTectons){
        if(checkControlledFungus()){
            return;
        }
        for (Tecton tecton : selectedTectons) {
            this.selectedFungus.shootSpore(tecton);
        }
        this.selectedFungus=null;
    }
    
    /*
    public void growFungalBody(FungalBody fungus){
        if (fungus.getBase().isOccupiedByFungus()){
            System.out.println("Tecton already has a fungus...");
            return;
        }else{
            fungus.getBase().setOccupiedByFungus(true);
            this.controlledFunguses.add(fungus);
        }
    }*/

    @Loggable
    public void growFungalBody(Tecton tecton){
        tecton.createFungalBody(this);
    }

    @Loggable
    public void growFungalBodyFromSpore(Tecton tecton){
        tecton.createFungalBodyFromSpore(this);
    }

    @Loggable
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

    @Loggable
    public void selectFungus(FungalBody fungus){
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

    @Loggable
    public void growHyphalAction(Tecton tecton){
        if (checkControlledFungus()){
            return;
        }
        this.selectedFungus.growHyphal(tecton);
        this.selectedFungus=null;
    }

    @Loggable
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

    @Loggable
    public void removeHyphal(Hyphal hyphal){
        hyphalList.remove(hyphal);
    }

    @Loggable
    public void addHyphal(Hyphal hyphal){
        hyphalList.add(hyphal);
    }

    @Loggable
    public void addFungus(FungalBody fungalToAdd){
        controlledFunguses.add(fungalToAdd);
    }

    @Loggable
    public void growHyphalFromHyphalAciton(Tecton targetTecton){
        selectedHyphal.growHyphalFromHyphal(targetTecton);
    }

}