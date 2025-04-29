package hu.bme.iit.projlab.bmekings.Entities.Spore;

import hu.bme.iit.projlab.bmekings.Entities.Insect.Insect;
import hu.bme.iit.projlab.bmekings.Map.Tecton.Tecton;

/**
 * A DuplicateSpore osztály egy specifikus spóra típust valósít meg, amely az őt elfogyasztó rovart osztódásra készteti.
 * A Spore absztrakt osztályból származik, és implementálja a Listener interfészt az Entity ősosztályon keresztül.
 * */
public class DuplicateSpore extends Spore {

    public DuplicateSpore() {
        super();
    }
    
    public DuplicateSpore(Tecton baseLocation) {
        super(baseLocation);
    }

    public DuplicateSpore(String id, Tecton baseLocation) {
        super(id, baseLocation);
    }
    
    @Override
    public void activateEffect(Insect targetInsect) {
        Insect newInsect=new Insect(targetInsect);
        newInsect.createInsect();
    }

    @Override
    public void update() {

    }


}