package hu.bme.iit.projlab.bmekings.Entities.Insect;

import java.util.ArrayList;

import hu.bme.iit.projlab.bmekings.Effects.Effect.Effect;
import hu.bme.iit.projlab.bmekings.Entities.Entity;
import hu.bme.iit.projlab.bmekings.Entities.Fungal.Hyphal;
import hu.bme.iit.projlab.bmekings.Interface.SporeInterface.SporeInterface;
import hu.bme.iit.projlab.bmekings.Map.Tecton.Tecton;
import hu.bme.iit.projlab.bmekings.Player.Entomologist.Entomologist;
import hu.bme.iit.projlab.bmekings.Logic.GameLogic.GameLogic;


/**
 * Az Insect osztály a rovarok kezelésére szolgál a játékban.
 * A rovarokat a rovarászok irányítják, és fő feladatuk a spórák elfogyasztása, valamint a gombafonalak elvágása.
 * A rovarok mozgásához gombafonalakra van szükségük, és különböző akciókat végezhetnek a játék során.
 * Az Entity absztrakt osztályból származik, így örökli annak attribútumait és metódusait.
 */
public class Insect extends Entity{

    private int movingSpeed;
    private int movingCD;
    private int stomachLimit;
    private int currStomachFullness;
    private int cutCooldown;
    private ArrayList<Effect> activeEffects;

    public Insect() {
        super();

        //this.id=IDGenerator.generateID("I");
        this.movingSpeed = 0;
        this.movingCD = 0;
        this.stomachLimit = 0;
        this.currStomachFullness = 0;
        this.cutCooldown = 0;
        this.activeEffects = new ArrayList<>();
    }

    public Insect(int movingSpeed, int movingCD, int stomachLimit, int currStomachFullness, int cutCooldown, String id, Tecton baseLocation){
        super(id, baseLocation);

        //this.id=IDGenerator.generateID("I");
        this.movingSpeed = movingSpeed;
        this.movingCD = movingCD;
        this.stomachLimit = stomachLimit;
        this.currStomachFullness = currStomachFullness;
        this.cutCooldown = cutCooldown;
    }

    public int getMovingSpeed() { return movingSpeed; }
    
    public int getMovingCD() { return movingCD; }
    
    public int getStomachLimit() { return stomachLimit; }
    
    public int getCurrStomachFullness() { return currStomachFullness; }
    
    public int getCutCooldown() { return cutCooldown; }

    public ArrayList<Effect> getActiveEffects() { return activeEffects; }

    public void move(Tecton targetTecton) {
        // movingSpeed ????? TICK / TURN BASED
        if(movingCD==0){
            this.baseLocation=targetTecton;
        }
    }

    public void eatSpore() {
        SporeInterface sporeToEat=this.baseLocation.getNextSporeToEat();
        if(this.currStomachFullness+sporeToEat.getNutritionValue() <this.stomachLimit){
            this.baseLocation.decreaseSpore(1);
            sporeToEat.activateEffect(this);
        }
    }

    public void cutHyphal(Hyphal h) {
        
    }

    @Override
    public void update() {
        movingCD--;     // 0 alá ne menjen
        cutCooldown--;  // 0 alá ne menjen
        
        // Kiszedes egy ido utan meg kell hogy valosuljon
        for(Effect item: activeEffects){
            //remove ha lejar az ideje
        }
    }

    public void applyEffect(Effect effect) {
        activeEffects.add(effect);
    }

    public void hungerEffectActivate(int stomachRateDecrease){
        if (this.currStomachFullness-stomachRateDecrease<0){
            this.currStomachFullness=0;
        }else{
            this.currStomachFullness-=stomachRateDecrease;
        }
    }

    public void HyhalEffectActivate(int coolDown){
        this.cutCooldown=coolDown;
    }

    public void SpeedEffectActivate(int speed, boolean stunned){
        if(stunned){
            this.movingCD=10;
        }else{
            // feltetel, hogy ne legyen minusz a sebesseg
            this.movingSpeed=speed;
        }
    }

    public void DestroyInsect() {
        // 1. Eltávolítás az Entomologist controlledInsects listájából
        // Gamelogic entomolgist listabol
        
        for (Entomologist entomologist : GameLogic.getEntomologists()){
            for (Insect insect : entomologist.getControlledInsects()){
                if (this == insect){
                    entomologist.deleteControlledInsect(this);
                }
            }
        }

        // 2. Eltávolítás a GameLogic entityList-jéből
        GameLogic.getEntityList().remove(this);
    }

}