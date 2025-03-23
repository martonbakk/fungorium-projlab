 public class HungerSpore extends Spore{
 
     public HungerSpore() {
         super();
         System.out.println("new HungerSpore Created");
     }

     public void activateEffect(){
        System.out.println("Hunger effect activated");
    }
    
    public void spawnSpore(){
        System.out.println("HungerSpore is spawned");
    }

    public void update() {
        System.out.println("HungerSpore is updated");
    }

}
