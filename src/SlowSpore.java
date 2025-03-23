public class SlowSpore extends Spore{

    public SlowSpore() {
        super();
        System.out.println("new SlowSpore Created");
    }

    public void activateEffect(){
        System.out.println("Slow effect activated");
    }
    
    public void spawnSpore(){
        System.out.println("SlowSpore is spawned");
    }

    public void update() {
        System.out.println("SlowSpore is updated");
    }

}
