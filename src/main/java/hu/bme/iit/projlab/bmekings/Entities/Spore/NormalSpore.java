package main.java.hu.bme.iit.projlab.bmekings.Entities.Spore;
import main.java.hu.bme.iit.projlab.bmekings.Map.Tecton.Tecton;
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

    public void activateEffect() {
    }

    public void spawnSpore() {
    }

    @Override
    public void update() {
    }
}