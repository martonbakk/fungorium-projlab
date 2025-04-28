package hu.bme.iit.projlab.bmekings.Entities.Spore;

import hu.bme.iit.projlab.bmekings.Entities.Insect.Insect;
import hu.bme.iit.projlab.bmekings.Map.Tecton.Tecton;

/**
 * A NormalSpore osztály egy alapvető spóra típust valósít meg, amely hatással van az azt elfogyasztó rovarra.
 * A spóra elfogyasztása növeli a rovar telítettségi szintjét.
 * A Spore absztrakt osztályból származik, és implementálja a Listener interfészt az Entity ősosztályon keresztül.
 */
public class NormalSpore extends Spore {

    public NormalSpore() {
        super();
    }

    public NormalSpore(int n, String id, Tecton baseLocation) {
        super(n, id, baseLocation);
    }

    public NormalSpore(int n, Tecton baseLocation) {
        super(n, baseLocation);
    }

    public void activateEffect(Insect targetInsect) {
    }
    
    @Override
    public void update() {
    }

    @Override
    public int getNutritionValue(){
        return 0;
    }
}