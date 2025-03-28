package main.java.hu.bme.iit.projlab.bmekings.Entities.Fungal;

import main.java.hu.bme.iit.projlab.bmekings.Entities.Entity;
import main.java.hu.bme.iit.projlab.bmekings.Map.Tecton.Tecton;
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
        
    }

    public void growFungusFromInsect() {
        
    }

    public void speedUpDevelopment() {

    }


    public void aging() {

    }

    public void eatInsect(){
        
    }

    @Override
    public void update() {
        
    }

}