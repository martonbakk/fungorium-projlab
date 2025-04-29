package hu.bme.iit.projlab.bmekings.Entities.Spore;

import java.util.Random;

import hu.bme.iit.projlab.bmekings.Entities.Entity;
import hu.bme.iit.projlab.bmekings.Entities.Insect.Insect;
import hu.bme.iit.projlab.bmekings.Interface.SporeInterface.SporeInterface;
import hu.bme.iit.projlab.bmekings.Logic.GameLogic.GameLogic;
import hu.bme.iit.projlab.bmekings.Logic.IDGenerator.IDGenerator;
import hu.bme.iit.projlab.bmekings.Map.Tecton.Tecton;


/**
 * A Spore absztrakt osztály a különböző típusú spórák közös tulajdonságait és viselkedését definiálja.
 * A spórák a gombatestek által termelt egységek, amelyek új gombafonalak és gombatestek fejlődését segítik,
 * valamint különböző hatásokat gyakorolhatnak a rovarokra. Az Entity osztályból származik, és implementálja
 * a SporeInterface interfészt.
 */
public abstract class Spore extends Entity implements SporeInterface {

    private int nutritionValue;
    protected Tecton baseLocation;
     private  Random rand = new Random();


    public Spore(){
        super();
        int randomNumber = rand.nextInt(50) + 1;
        this.nutritionValue= randomNumber;
    }

    public Spore(Tecton baseLocation) {
        super(IDGenerator.generateID("SP"), baseLocation);
        int randomNumber = rand.nextInt(50) + 1;
        nutritionValue = randomNumber;
    }

    public Spore(String id, Tecton baseLocation) {
        super(id, baseLocation);
        int randomNumber = rand.nextInt(50) + 1;
        nutritionValue = randomNumber;
    }

    public String getId() { return id; }

    public Tecton getBaseLocation() { return baseLocation; }

    public void setNutritionalValue(int n) { 
        System.out.println("[" + this.getId() + "] [nutritionValue] megvaltozott:");
        System.out.println("[" + nutritionValue + "] -> [" + n + "]"); 
        nutritionValue = n;

    }

    public boolean isBaseLocation(Tecton baseLocation) {
        return this.baseLocation.getId().equals(baseLocation.getId());
    }    

    public void setBaseLocation(Tecton baseLocation) {
        System.out.println("[" + this.getId() + "] [baseLocation] megvaltozott:");
        System.out.println("[" + this.baseLocation + "] -> [" + baseLocation + "]");
        this.baseLocation = baseLocation;

    }

    public void destroySpore(){
        GameLogic.deleteEntity(this);
        baseLocation.removeSpore(this);
        System.out.println("Spore objektum torlodott id:["+ id +"]");
    }

    public void spawnSpore() {
        GameLogic.addEntity(this);
        baseLocation.addSpore(this);        
    }

    public int getNutritionValue(){
        return this.nutritionValue;
    }

    public void activateEffect(Insect targetInsect) {}
}