package hu.bme.iit.projlab.bmekings.Entities.Insect;

import hu.bme.iit.projlab.bmekings.Entities.Entity;
import hu.bme.iit.projlab.bmekings.Entities.Fungal.Hyphal;
import hu.bme.iit.projlab.bmekings.Interface.SporeInterface.SporeInterface;
import hu.bme.iit.projlab.bmekings.Map.Tecton.Tecton;

/**
 * Az Insect osztály a rovarok kezelésére szolgál a játékban.
 * A rovarokat a rovarászok irányítják, és fő feladatuk a spórák elfogyasztása, valamint a gombafonalak elvágása.
 * A rovarok mozgásához gombafonalakra van szükségük, és különböző akciókat végezhetnek a játék során.
 * Az Entity absztrakt osztályból származik, így örökli annak attribútumait és metódusait.
 */
public class Insect extends Entity {

    private int movingSpeed;
    private int movingCD;
    private int stomachLimit;
    private int currStomachFullness;
    private int cutCooldown;
    
    public Insect() {
        super();

        this.movingSpeed = 0;
        this.movingCD = 0;
        this.stomachLimit = 0;
        this.currStomachFullness = 0;
        this.cutCooldown = 0;
    }

    public Insect(int movingSpeed, int movingCD, int stomachLimit, int currStomachFullness, int cutCooldown, String id, Tecton baseLocation){
        super(id, baseLocation);

        this.movingSpeed = movingSpeed;
        this.movingCD = movingCD;
        this.stomachLimit = stomachLimit;
        this.currStomachFullness = currStomachFullness;
        this.cutCooldown = cutCooldown;
    }

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
    }
}