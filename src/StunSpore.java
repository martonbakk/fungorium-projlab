public class StunSpore extends Spore{

    public StunSpore() {
        super();
        System.out.println("new StunSpore Created");
    }

    public void activateEffect(){
        System.out.println("Stun effect activated");
    }
    
    public void spawnSpore(){
        System.out.println("StunSpore is spawned");
    }

    public void update() {
        System.out.println("StunSpore is updated");
    }

}