package hu.bme.iit.projlab.bmekings.Entities.Fungal;

import hu.bme.iit.projlab.bmekings.Entities.Entity;
import hu.bme.iit.projlab.bmekings.Map.Tecton.Tecton;

/**
 * A Hyphal osztály a játékban lévő gombafonalak adatait tárolja, és a gombafonalakkal kapcsolatos fontos műveleteket kezeli.
 * A gombafonalak a gombatestekből nőnek ki, és a tektonok közötti kapcsolódást biztosítják, lehetővé téve a gombák terjeszkedését
 * és a rovarok mozgását. Az Entity absztrakt osztályból származik, így örökli annak attribútumait és metódusait.
 */
public class Hyphal extends Entity {
    private Tecton connectedTecton;
    private boolean developed;
    private int developTime;
    private int lifeTime;
    private int cutTime;

    public Hyphal() {
        super();

        this.connectedTecton = new Tecton(); 
        this.developed = false;
        this.developTime  = 0;
        this.lifeTime = 0;
        this.cutTime = 0;
    }

    public Hyphal(Tecton connectedTecton, boolean developed, int developTime, int lifeTime, int cutTime, String id, Tecton baseLocation){
        super(id, baseLocation);
        
        this.connectedTecton = connectedTecton;
        this.developed = developed;
        this.developTime = developTime;
        this.lifeTime = lifeTime;
        this.cutTime = cutTime;
    }

    public void growFungus() {
        if(baseLocation.decreaseSpore(5, 2) != null) {
            baseLocation.setOccupiedByFungus(true);
        }
    }

    public void growFungusFromInsect() {
        baseLocation.setOccupiedByFungus(true);
    }

    public void speedUpDevelopment() {
        if(baseLocation.decreaseSpore(2, 2) != null){
            developTime--;
        }
    }

    public void aging() {
        lifeTime--;
    }

    public void eatInsect(){
        // tldr;
    }
    
    Tecton getConnectedTecton(){return connectedTecton;}

    @Override
    public void update() {
        aging();
    }

    void setLifeTime(int newLifeTime){
        lifeTime=newLifeTime;
    }
}