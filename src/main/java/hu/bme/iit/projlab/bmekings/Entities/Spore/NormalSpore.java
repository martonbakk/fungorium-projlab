package main.java.hu.bme.iit.projlab.bmekings.Entities.Spore;

/**
 * A NormalSpore osztály egy alapvető spóra típust valósít meg, amely hatással van az azt elfogyasztó rovarra.
 * A spóra elfogyasztása növeli a rovar telítettségi szintjét.
 * A Spore absztrakt osztályból származik, és implementálja a Listener interfészt az Entity ősosztályon keresztül.
 */
public class NormalSpore extends Spore {

    public NormalSpore() {
        super();
    }

    public void activateEffect() {
    }

    public void spawnSpore() {
    }

    @Override
    public void update() {
    }
}