package hu.bme.iit.projlab.bmekings.Entities.Insect;

import java.util.HashMap;

import hu.bme.iit.projlab.bmekings.Effects.Effect.Effect;
import hu.bme.iit.projlab.bmekings.Entities.Entity;
import hu.bme.iit.projlab.bmekings.Entities.Fungal.Hyphal;
import hu.bme.iit.projlab.bmekings.Interface.SporeInterface.SporeInterface;
import hu.bme.iit.projlab.bmekings.Logger.Loggable;
import hu.bme.iit.projlab.bmekings.Logic.GameLogic.GameLogic;
import hu.bme.iit.projlab.bmekings.Logic.IDGenerator.IDGenerator;
import hu.bme.iit.projlab.bmekings.Map.Tecton.Tecton;
import hu.bme.iit.projlab.bmekings.Player.Entomologist.Entomologist;


/**
 * Az Insect osztály a rovarok kezelésére szolgál a játékban.
 * A rovarokat a rovarászok irányítják, és fő feladatuk a spórák elfogyasztása, valamint a gombafonalak elvágása.
 * A rovarok mozgásához gombafonalakra van szükségük, és különböző akciókat végezhetnek a játék során.
 * Az Entity absztrakt osztályból származik, így örökli annak attribútumait és metódusait.
 */

@Loggable("Insect")
public class Insect extends Entity{

    private int movingSpeed;
    private int movingCD;
    private int stomachLimit;
    private int currStomachFullness;
    private int cutCooldown;
    private int stunTime;
    private Entomologist owner=null;
    private HashMap<Effect, Integer> activeEffects=new HashMap<>();

    @Loggable
    public Entomologist getOwner() {
        return owner;
    }

    @Loggable
    public void increaseMovingSpeed(int speed){
        System.out.println("[" + this.getId() + "] sped up");
        this.movingSpeed+=speed;    
    }

    @Loggable
    public void decreaseMovingSpeed(int speed){
        System.out.println("[" + this.getId() + "] slowed");
        this.movingSpeed-=speed;
        if (movingSpeed<0)
            movingSpeed=0;
    }

    @Loggable
    public void inreaseHyphalCoolDown(int cd){
        System.out.println("[" + this.getId() + "] can't cut hyphal for: "+ cd +"");
        this.cutCooldown+=cd;
    }


    public void stunEffect(int cd){
    System.out.println("[" + this.getId() + "] stunned for:" + cd);
        
        this.stunTime=cd;
        this.movingCD = cd;
    }

    public Insect() {
        super();

        this.movingSpeed = 0;
        this.movingCD = 0;
        this.stunTime=0;
        this.stomachLimit = 0;
        this.currStomachFullness = 0;
        this.cutCooldown = 0;
        this.activeEffects = new HashMap<>();
    }

    //rovarasz felvetel konstruktorba!!!
    public Insect(int movingSpeed, int movingCD, int stomachLimit, int currStomachFullness, int cutCooldown, Tecton baseLocation, Entomologist owner){
        super(IDGenerator.generateID("I"), baseLocation);

        //this.id=IDGenerator.generateID("I");
        this.movingSpeed = movingSpeed;
        this.movingCD = movingCD;
        this.stomachLimit = stomachLimit;
        this.stunTime=0;
        this.currStomachFullness = currStomachFullness;
        this.cutCooldown = cutCooldown;
        this.owner=owner;
        this.owner.addInsect(this);
    }

    public Insect(Insect parentInsect) {
        super(IDGenerator.generateID("I"), parentInsect.getBase());

        //this.id=IDGenerator.generateID("I");
        this.movingSpeed = parentInsect.getMovingSpeed();
        this.movingCD = parentInsect.getMovingCD();
        this.stomachLimit = parentInsect.getStomachLimit();
        this.currStomachFullness = parentInsect.getCurrStomachFullness();
        this.cutCooldown = parentInsect.getCutCooldown();
        this.owner=parentInsect.owner;
    }


    @Loggable
    public int getStunTime() { return stunTime; }

    @Loggable
    public int getMovingSpeed() { return movingSpeed; }
    
    @Loggable
    public int getMovingCD() { return movingCD; }
    
    @Loggable
    public int getStomachLimit() { return stomachLimit; }
    
    @Loggable
    public int getCurrStomachFullness() { return currStomachFullness; }
    
    @Loggable
    public int getCutCooldown() { return cutCooldown; }

    @Loggable
    public Entomologist getEntomologist(){ return  owner;}

    @Loggable
    public HashMap<Effect, Integer> getActiveEffects() { return activeEffects; }

    @Loggable
    public void move(Tecton targetTecton) {
        if (baseLocation.getConnectedNeighbors().containsKey(targetTecton)) {
            for (Hyphal h : baseLocation.getConnectedNeighbors().get(targetTecton)) {
                if (h.getDeveloped()) {
                    if (movingCD == 0) {
                        System.out.println("[" + this.getId() + "] [baseLocation] megváltozott:");
                        System.out.println("[" + baseLocation.getId() + "] -> [" + targetTecton.getId() + "]");
                        
                        this.baseLocation.getInsects().remove(this);
                        this.baseLocation.setOccupiedByInsect(this.baseLocation.getInsects().size() > 0);
                        this.baseLocation=targetTecton;
                        this.baseLocation.addInsect(this);
                        this.baseLocation.setOccupiedByInsect(true);

                        // TODO mennyi legyen?
                        movingCD = 2;
                        return;
                    } else {
                        throw new RuntimeException("A mozgás cooldown-on van!");
                    }
                }
            }
            throw new RuntimeException("A két Tekton közötti fonál még nincs kifejlődve!");
        } else {
            throw new RuntimeException("A Tektonok nincsenek fonallal összekötve!");
        }
    }

    @Loggable
    public void eatSpore() {
        SporeInterface sporeToEat = this.baseLocation.getNextSporeToEat();
        if(this.currStomachFullness+sporeToEat.getNutritionValue() < this.stomachLimit) {
            this.feedInsect(sporeToEat.getNutritionValue());
            sporeToEat.activateEffect(this);
            sporeToEat.destroySpore();
        }
    }

    @Loggable
    public void feedInsect(int nutritionvalue) {
        System.out.println("Insect nutrition changed");
        currStomachFullness += nutritionvalue;
        if (currStomachFullness<0) 
            currStomachFullness=0;
    }

    @Loggable
    public void cutHyphal(Hyphal h) {
        h.destroyHyphal();
    }

    @Loggable
    @Override
    public void update() {
        if(movingCD > 0)
            movingCD--;
        if(stunTime > 0)
            stunTime--;
        if (cutCooldown > 0)
            cutCooldown--;
        
        // 0 alá ne menjen
        if (cutCooldown < 0)
            cutCooldown = 0;
        else if (stunTime < 0)
            stunTime = 0;

        if (currStomachFullness > 0) {
            currStomachFullness -= 5;
            if(currStomachFullness < 0)
                currStomachFullness=0;
        }
        
        // Kiszedes egy ido utan meg kell hogy valosuljon
        for (Effect item: activeEffects.keySet()){
            item.apply(this);
            if(activeEffects.get(item) <= 0){
                activeEffects.remove(item);
            }
        }
    }

    @Loggable
    public void applyEffect(Effect effect) {
        activeEffects.put(effect, 10);
    }

    @Loggable
    public void hungerEffectActivate(int stomachRateDecrease){
        if (this.currStomachFullness-stomachRateDecrease<0){
            this.currStomachFullness=0;
        }else{
            this.currStomachFullness-=stomachRateDecrease;
        }
    }

    @Loggable
    public void HyhalEffectActivate(int coolDown){
        this.cutCooldown = coolDown;
    }

    @Loggable
    public void SpeedEffectActivate(int speed, boolean stunned){
        if (stunned) {
            this.movingCD = 10;
        } else {
            // feltetel, hogy ne legyen minusz a sebesseg
            this.movingSpeed=speed;
        }
    }

    @Loggable
    public void DestroyInsect() {
        owner.deleteControlledInsect(this);
        GameLogic.deleteEntity(this);
        System.out.println("Insect objektum torlodott id:["+ id +"]");
    }

    @Loggable
    public void createInsect() {
        GameLogic.addEntity(this);
        baseLocation.addInsect(this);
        owner.addInsect(this);
    }

    @Override
    public String toString() {
        return this.getId();
    }

}