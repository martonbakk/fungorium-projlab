package hu.bme.iit.projlab.bmekings.Entities.Spore;

import hu.bme.iit.projlab.bmekings.Map.Tecton.Tecton;

/**
 * A DuplicateSpore osztály egy specifikus spóra típust valósít meg, amely az őt elfogyasztó rovart osztódásra készteti.
 * A Spore absztrakt osztályból származik, és implementálja a Listener interfészt az Entity ősosztályon keresztül.
 * */
public class DuplicateSpore extends Spore {

    public DuplicateSpore() {
        super();
    }

    public DuplicateSpore(int n, String id, Tecton baseLocation) {
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