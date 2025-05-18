package hu.bme.iit.projlab.bmekings.Entities.Fungal;

import java.io.Serializable;

import hu.bme.iit.projlab.bmekings.Logger.Loggable;

@Loggable("TypeCharacteristics")
public class TypeCharacteristics implements Serializable{
    private static final long serialVersionUID = 1L;

    public int shootingRange; // Hány Tecton távolságra tud spórát lőni
    public int sporeProductionIntensity; // Mennyi spórát termel egy kör alatt
    public int startingHyphalNum; // Ez nemtom mi
    public int sporeCapacity; // Azt adja meg, hogy hány spóra kilövése után hal meg a FungalBody
    
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
}
