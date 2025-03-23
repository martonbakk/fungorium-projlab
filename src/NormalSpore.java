public class NormalSpore extends Spore {
    public NormalSpore() {
        super();
        System.out.println("new NormalSpore Created");
    }

    public void activateEffect(){
        System.out.println("Normal effect activated");
    }
    
    public void spawnSpore(){
        System.out.println("NormalSpore is spawned");
    }

    public void update() {
        System.out.println("NormalSpore is updated");
    }
}
