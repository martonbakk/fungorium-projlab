package hu.bme.iit.projlab.bmekings.Entities.Spore;

import hu.bme.iit.projlab.bmekings.Map.Tecton.Tecton;

/**
 * A HyphalProtectorSpore osztály egy specifikus spóra típust valósít meg, amely védő hatást fejt ki a gombafonalakra.
 * Ha egy rovar elfogyasztja, bizonyos ideig nem lesz képes gombafonalat vágni.
 * A Spore absztrakt osztályból származik, és implementálja a Listener interfészt az Entity ősosztályon keresztül.
 */
public class HyphalProtectorSpore extends Spore {

    public HyphalProtectorSpore() {
        super();
    }

    public HyphalProtectorSpore(int n, String id, Tecton baseLocation) {
        super(n, id, baseLocation);
    }

    public void activateEffect() {

    }

    public void spawnSpore() {

    }


    @Override
    public void update() {
        
    }
}
