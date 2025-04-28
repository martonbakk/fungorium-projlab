package hu.bme.iit.projlab.bmekings.Entities.Fungal;

import hu.bme.iit.projlab.bmekings.Entities.Entity;
import hu.bme.iit.projlab.bmekings.Entities.Insect.Insect;
import hu.bme.iit.projlab.bmekings.Logic.GameLogic.GameLogic;
import hu.bme.iit.projlab.bmekings.Logic.IDGenerator.IDGenerator;
import hu.bme.iit.projlab.bmekings.Map.Tecton.Tecton;
import hu.bme.iit.projlab.bmekings.Player.Mycologist.Mycologist;

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
    private static int hyphalIds = 0;
    private Mycologist owner;

    private static void hyphalIdGenerator() {
        hyphalIds++;
    }
//kosntrukktorba az owner felvetele
    public Hyphal() {
        super(IDGenerator.generateID("H"),null);
        this.connectedTecton = new Tecton(); 
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

    public Tecton getConnectedTecton() { return connectedTecton; }

    public boolean getDeveloped() { return developed; }

    public int getDevelopTime() { return developTime; }

    public int getLifeTime() { return lifeTime; }

    public int getCutTime() { return cutTime; }

    public void setLifeTime(int newLifeTime) { lifeTime=newLifeTime; }

    public void growFungus(Tecton tecton, String grownFrom) {
        /*
        Mycologist player=null;
        for (Mycologist mycologist : GameLogic.getMycologists()){
            for (Hyphal hyphal : mycologist.getHyphalList()){
                if (this == hyphal){
                    player = mycologist;
                }
            }
        }
        */
        tecton.createFungalBody(owner, grownFrom);
    }


    public void speedUpDevelopment() {
        if(baseLocation.decreaseSpore(2) != null){
            developTime-=2;
            if(developTime<=0){
                developed=true;
            }
        }
    }

    public void aging() {
        lifeTime--;
        developTime--;
        if(developTime<=0){
            developed=true;
        }
    }

    public void eatInsect(Insect stunnedInsect){
        Tecton currLoc = stunnedInsect.getBase();

        stunnedInsect.DestroyInsect();

        /// itt jon a growFungusFromInsect()
        growFungus(currLoc, "insect");
    }

    @Override
    public void update() {
        aging();
    }

    public void destroyHyphal(){
        // entity
        GameLogic.deleteEntity(this);
        
        // connectedtecton
        this.baseLocation.disconnectTecton(this.connectedTecton, this);
         
        // mycologist
        
        //Mycologist owner=this.getOwner();
        owner.removeHyphal(this);
        }

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

        
}