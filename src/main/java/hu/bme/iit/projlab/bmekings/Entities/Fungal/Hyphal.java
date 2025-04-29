package hu.bme.iit.projlab.bmekings.Entities.Fungal;

import hu.bme.iit.projlab.bmekings.Entities.Entity;
import hu.bme.iit.projlab.bmekings.Entities.Insect.Insect;
import hu.bme.iit.projlab.bmekings.Logger.Loggable;
import hu.bme.iit.projlab.bmekings.Logic.GameLogic.GameLogic;
import hu.bme.iit.projlab.bmekings.Logic.IDGenerator.IDGenerator;
import hu.bme.iit.projlab.bmekings.Map.Tecton.Tecton;
import hu.bme.iit.projlab.bmekings.Player.Mycologist.Mycologist;

/**
 * A Hyphal osztály a játékban lévő gombafonalak adatait tárolja, és a gombafonalakkal kapcsolatos fontos műveleteket kezeli.
 * A gombafonalak a gombatestekből nőnek ki, és a tektonok közötti kapcsolódást biztosítják, lehetővé téve a gombák terjeszkedését
 * és a rovarok mozgását. Az Entity absztrakt osztályból származik, így örökli annak attribútumait és metódusait.
 */
@Loggable("Hyphal")
public class Hyphal extends Entity {
    private Tecton connectedTecton=null;
    private boolean developed;
    private int developTime;
    private int lifeTime;
    private int cutTime;
    private Mycologist owner=null;

    //kosntrukktorba az owner felvetele
    public Hyphal() {
        super(IDGenerator.generateID("H"),null);
        this.developed = false;
        this.developTime  = 0;
        this.lifeTime = 0;
        this.cutTime = 0;
    }

    public Hyphal(Tecton connectedTecton, boolean developed, int developTime, int lifeTime, int cutTime, Tecton baseLocation, Mycologist owner){
        super(IDGenerator.generateID("H"), baseLocation);
        this.connectedTecton = connectedTecton;
        this.developed = developed;
        this.developTime = developTime;
        this.lifeTime = lifeTime;
        this.cutTime = cutTime;
        this.owner=owner;
    }

    @Loggable
    public Tecton getConnectedTecton() { return connectedTecton; }

    @Loggable
    public boolean getDeveloped() { return developed; }

    @Loggable
    public int getDevelopTime() { return developTime; }

    @Loggable
    public int getLifeTime() { return lifeTime; }

    @Loggable
    public int getCutTime() { return cutTime; }

    @Loggable
    public void setDeveloped(boolean b) { 
    System.out.println("[" + this.getId() + "] [developed] megvaltozott:");
    System.out.println("[" + developed + "] -> [" + b + "]");
    developed = b; 
    }

    @Loggable
    public void setDevelopTime(int newDevelopTime) { 
    System.out.println("[" + this.getId() + "] [developTime] megvaltozott:");
    System.out.println("[" + developTime + "] -> [" + newDevelopTime + "]");    
    developTime = newDevelopTime; }

    @Loggable
    public void setLifeTime(int newLifeTime) { 
    System.out.println("[" + this.getId() + "] [lifeTime] megvaltozott:");
    System.out.println("[" + lifeTime + "] -> [" + newLifeTime + "]");  
    lifeTime=newLifeTime; }

    @Loggable
    public void setCutTime(int newCutTime) { 
    System.out.println("[" + this.getId() + "] [cutTime] megvaltozott:");
    System.out.println("[" + cutTime + "] -> [" + newCutTime + "]");
    cutTime = newCutTime; }

    @Loggable
    public void growFungus(Tecton tecton) {
        tecton.createFungalBody(owner);
    }

    @Loggable
    public void growFungusFromSpore(Tecton tecton) {
        tecton.createFungalBodyFromSpore(owner);
    }


    @Loggable
    public void speedUpDevelopment() {
        if(baseLocation.decreaseSpore(2) != null){
            developTime-=2;
            if(developTime<=0){
                developed=true;
            }
        }
    }

    @Loggable
    public void aging() {
        dying();
        developTime--;
        if(developTime<=0){
            developed=true;
        }
    }

    @Loggable
    public void dying() {
        int szam = lifeTime-1 ;
        System.out.println("[" + this.getId() + "] [life] megvaltozott:");
        System.out.println("[" + lifeTime + "] -> [" + szam + "]");
        lifeTime--;
    }

    @Loggable
    public void eatInsect(Insect stunnedInsect){
        if(!((stunnedInsect.getBase()==this.baseLocation) || (stunnedInsect.getBase()==this.connectedTecton)))
            return;

        if (stunnedInsect.getStunTime()==0) {
            return;
        }
        Tecton currLoc = stunnedInsect.getBase();

        stunnedInsect.DestroyInsect();

        /// itt jon a growFungusFromInsect()
        growFungus(currLoc);
    }

    @Loggable
    @Override
    public void update() {
        aging();
    }

    @Loggable
    public void destroyHyphal(){
        // entity
        GameLogic.deleteEntity(this);
        
        // connectedtecton
        this.baseLocation.disconnectTecton(this.connectedTecton, this);
         
        System.out.println("Hyphal torlodott id:["+ id +"]");

        owner.removeHyphal(this);
        }

    @Loggable
    public Mycologist getOwner(){
        Mycologist player = null;
        for (Mycologist mycologist : GameLogic.getMycologists()){
            for (Hyphal hyphal : mycologist.getHyphalList()){
                if (this == hyphal){
                    player = mycologist;
                }
            }
        }
        return player;
    }

    @Loggable
    public void hyphalHeal(){
        lifeTime++;
    }

    @Loggable
    public void growHyphalFromHyphal(Tecton targetTecton){
        baseLocation.connectTecton(targetTecton, owner);
    }

        
}