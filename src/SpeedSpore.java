public class SpeedSpore extends Spore{

    public SpeedSpore() {
        super();
        System.out.println("new SpeedSpore Created");
    }

    public void activateEffect(){
        System.out.println("Speed effect activated");
    }
    
    public void spawnSpore(){
        System.out.println("SpeedSpore is spawned");
    }

    public void update() {
        System.out.println("SpeedSpore is updated");
    }

}
