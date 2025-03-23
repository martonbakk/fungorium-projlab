public class HyphalProtectorSpore extends Spore{

    public HyphalProtectorSpore() {
        super();
        System.out.println("new HyphalProtectorSpore Created");
    }

    public void activateEffect(){
        System.out.println("HyphalProtector effect activated");
    }
    
    public void spawnSpore(){
        System.out.println("HyphalProtectorSpore is spawned");
    }

    public void update() {
        System.out.println("HyphalProtectorSpore is updated");
    }

}
