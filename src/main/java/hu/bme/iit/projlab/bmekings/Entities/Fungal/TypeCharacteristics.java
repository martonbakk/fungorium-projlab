package hu.bme.iit.projlab.bmekings.Entities.Fungal;

public class TypeCharacteristics{
    int shootingRange;
    int sporeProductionIntensity;
    int startingHyphalNum;
    int sporeCapacity;


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

   public int getShootingRange() { return shootingRange; }
   
   public int getSporeProductionIntensity() { return sporeProductionIntensity; }
   
   public int getStartingHyphalNum() { return startingHyphalNum; }
   
   public int getSporeCapacity() { return sporeCapacity; }
}
