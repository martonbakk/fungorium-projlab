package hu.bme.iit.projlab.bmekings.Entities.Fungal;

import java.io.Serializable;

import hu.bme.iit.projlab.bmekings.Logger.Loggable;

@Loggable("TypeCharacteristics")
public class TypeCharacteristics implements Serializable{
    private static final long serialVersionUID = 1L;

    
    public TypeCharacteristics(){
        this.shootingRange = 1;
        this.sporeProductionIntensity = 1;
        this.startingHyphalNum = 1;
        this.sporeCapacity = 10;    
    }


    public TypeCharacteristics(int shootingRange, int sporeProductionIntensity, int startingHyphalNum, int sporeCapacity) {
        this.shootingRange = shootingRange;
        this.sporeProductionIntensity = sporeProductionIntensity;
        this.startingHyphalNum = startingHyphalNum;
        this.sporeCapacity = sporeCapacity;
    }

    public int shootingRange;
    public int sporeProductionIntensity;
    public int startingHyphalNum;
    public int sporeCapacity;
    
    @Loggable
    public int getShootingRange() { return shootingRange; }
    
    @Loggable
    public int getSporeProductionIntensity() { return sporeProductionIntensity; }
   
    @Loggable
    public int getStartingHyphalNum() { return startingHyphalNum; }
   
    @Loggable
    public int getSporeCapacity() { return sporeCapacity; }

    
}
