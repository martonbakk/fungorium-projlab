package hu.bme.iit.projlab.bmekings.Entities.Insect;

import hu.bme.iit.projlab.bmekings.Entities.Fungal.Hyphal;
import hu.bme.iit.projlab.bmekings.Map.Tecton.Tecton;
import hu.bme.iit.projlab.bmekings.Entities.Entity;
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
    private boolean cutCooldown;

    public Insect() {
        super();

        this.movingSpeed = 0;
        this.movingCD = 0;
        this.stomachLimit = 0;
        this.currStomachFullness = 0;
        this.cutCooldown = false;
    }

    public Insect(int movingSpeed, int movingCD, int stomachLimit, boolean cutCooldown, String id, Tecton baseLocation){
        super(id, baseLocation);

        this.movingSpeed = movingSpeed;
        this.movingCD = movingCD;
        this.stomachLimit = stomachLimit;
        this.currStomachFullness = stomachLimit;
        this.cutCooldown = cutCooldown;
    }

    public void move() {
        
    }

    public void eatSpore() {

    }

    public void cutHyphal(Hyphal h) {

    }


    @Override
    public void update() {
        
    }
}