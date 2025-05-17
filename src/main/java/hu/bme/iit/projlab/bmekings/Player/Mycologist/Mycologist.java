package hu.bme.iit.projlab.bmekings.Player.Mycologist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import hu.bme.iit.projlab.bmekings.Entities.Fungal.FungalBody;
import hu.bme.iit.projlab.bmekings.Entities.Fungal.Hyphal;
import hu.bme.iit.projlab.bmekings.Entities.Fungal.TypeCharacteristics;
import hu.bme.iit.projlab.bmekings.Entities.Insect.Insect;
import hu.bme.iit.projlab.bmekings.Logger.Loggable;
import hu.bme.iit.projlab.bmekings.Logic.IDGenerator.IDGenerator;
import hu.bme.iit.projlab.bmekings.Map.Tecton.Tecton;
import hu.bme.iit.projlab.bmekings.Player.Player;
import hu.bme.iit.projlab.bmekings.Program.Params;

@Loggable("Mycologist")
public class Mycologist extends Player {
    private ArrayList<FungalBody> controlledFunguses= new ArrayList<>();
    private ArrayList<Hyphal> hyphalList= new ArrayList<>();
    private FungalBody selectedFungus=null;
    private Hyphal selectedHyphal=null;
    private TypeCharacteristics typeCharacteristics= new TypeCharacteristics();
    
    // Fungus type lekezelese hianyzik

    /*public Mycologist(String id) {
        super(id);
        System.out.println("Uj objektum [" + getPlayerID() + "] hozzaadva!");
    }*/

    public Mycologist(){
        super("M");
        System.out.println("Uj objektum [" + getPlayerID() + "] hozzaadva!");
    }

    public Mycologist(String userName, String type) {
        super(IDGenerator.generateID("M"));
        this.userName = userName;
        this.type = type;
        this.controlledFunguses = new ArrayList<>();
        this.hyphalList = new ArrayList<>();
        this.typeCharacteristics = new TypeCharacteristics();
        System.out.println("Uj objektum [" + getPlayerID() + "] hozzaadva!");
    }

    public Mycologist(String userName) {
        super(IDGenerator.generateID("M"));
        this.userName = userName;
        this.type = null;
        this.controlledFunguses = new ArrayList<>();
        this.hyphalList = new ArrayList<>();
        this.typeCharacteristics = new TypeCharacteristics();
        System.out.println("Uj objektum [" + getPlayerID() + "] hozzaadva!");
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
            // Ez itt szerintem nem kell, a sima growFungalBody-t szerintem csak akkor használjuk, ha fonál megeszik egy rovart
            // case 3:
            //     growFungalBody(params.selectedTecton);
            //     break;
            case 3:
                growFungalBodyFromSpore(params.selectedTecton);
                break;
            case 4:
                growHyphalAction(params.selectedTecton);
                break;
            case 5:
                destroyFungus(params.selectedFungus);
                break;
            case 6:
                shootSpore(params.selectedTecton);
                break;
            case 7:
                speedUpDevelopment(params.selectedHyphal);
                break;
            case 8:
                growHyphalFromHyphalAciton(params.selectedTecton);
                break;
            case 9:
                hyphalEatInsect(params.selectedInsect);
                break;
            case 10:
                levelUpFungalBody(selectedFungus);
                break;
            default:
                System.out.println("Invalid action type");
        }
    }

    @Override
    public List<String> getAvailableActions() {
        return Arrays.asList("Grow Fungal Body", "Speed Up Development", 
            "Shoot Spore", "Eat Insect", "Grow Hyphal", "Grow Hyphal From Hyphal" , "Level Up");
    }

    @Loggable
    private void levelUpFungalBody(FungalBody selectedFungus) {
        if (checkControlledFungus()) {
            return;
        }

        if(selectedFungus==null){
            throw new RuntimeException("selectedfungus null");
        }
        selectedFungus.levelUp();
        selectedFungus = null;
    }

    @Loggable
    private boolean checkControlledFunguses(){
        boolean checkControlledFungus= this.controlledFunguses.isEmpty();
        if (checkControlledFungus) {
            throw new RuntimeException("Nem növesztettél még gombatestet...");   
        }
        return checkControlledFungus;
    }

    @Loggable
    private boolean checkControlledFungus(){
        boolean checkControlledFungus= this.selectedFungus == null;
        if (checkControlledFungus) {
            throw new RuntimeException("Nem választottál ki gombatestet...");
        }

        return checkControlledFungus;
    }

    @Loggable
    private boolean checkControlledFungusHyphal(){
        boolean checkControlledFungus= hyphalList.isEmpty();
        if (checkControlledFungus) {
            throw new RuntimeException("Nincs a gombatestnek fonala...");
        }
        return checkControlledFungus;
    }

    @Loggable
    private boolean checkSelectedHyhpal(){
        boolean checkControlledFungus= this.selectedHyphal == null;
        if (checkControlledFungus) {
           throw new RuntimeException("Nem választottál ki fonalat...");
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
    private void shootSpore(Tecton selectedTecton){
        if(checkControlledFungus()){
            return;
        }
        
        this.selectedFungus.shootSpore(selectedTecton);
        
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
        if (checkControlledFungus()){
            return;
        }
        if (tecton.getFlag().fungalApproved) {
            tecton.createFungalBody(this);
        }
        else {
            throw new RuntimeException("Erre a tektonra nem lehet gombatestet noveszteni!");
        }
    }

    @Loggable
    public void growFungalBodyFromSpore(Tecton tecton){
        if (checkControlledFungus()){
            return;
        }
        if (!tecton.getConnectedNeighbors().containsKey(this.selectedFungus.getBase())){
            throw new RuntimeException("Erre a tektonra nem lehet gombatestet noveszteni, még nincs összekötve!");
        }
        tecton.createFungalBodyFromSpore(this);
    }

    @Loggable
    public void selectHyphal(Hyphal hyphal){
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
    public void growHyphalFromHyphalAciton(Tecton targetTecton) {
        if (checkControlledFungus()){
            return;
        }
        System.out.println("Megvan");
        //if(targetTecton.getFlag().hyphalApproved || (targetTecton.getFlag().oneHyphalApproved&&targetTecton.getConnectedNeighbors().size()==0)){
            selectedHyphal.growHyphalFromHyphal(targetTecton);
            selectedHyphal = null;
            this.selectedFungus = null;
        //}
    }

    public void hyphalEatInsect(Insect targetInsect){
        if (checkControlledFungus()){
            return;
        }
        this.selectedHyphal.eatInsect(targetInsect);
        selectedHyphal = null;
        this.selectedFungus = null;
    }

    private boolean isTectonPart(Tecton searchedTecton){
        if(searchedTecton.getFungalBody().getOwner()==this)
            return true;
        for (Hyphal hyphal : hyphalList) {
            if((hyphal.getBase()==searchedTecton) || hyphal.getConnectedTecton()==searchedTecton)
                return true;
        }
        return false;
    }
}