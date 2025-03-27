package main.java.hu.bme.iit.projlab.bmekings.Entities.Spore;
import main.java.hu.bme.iit.projlab.bmekings.Map.Tecton.Tecton;
/**
 * A HungerSpore osztály egy specifikus spóra típust valósít meg, amely éheztető hatást fejt ki a rovarokra.
 * Ha egy rovar elfogyasztja, csökkenti a rovar telítettségi szintjét, így a rovar több spórát tud fogyasztani.
 * A Spore absztrakt osztályból származik, és implementálja a Listener interfészt az Entity ősosztályon keresztül.
 */
public class HungerSpore extends Spore {

    public HungerSpore() {
        super();
    }

    public HungerSpore(int n, String id, Tecton baseLocation) {
        super(n,id,baseLocation);
    }

    public void activateEffect() {
        
    }


    public void spawnSpore() {
        
    }


    @Override
    public void update() {

    }
}