package hu.bme.iit.projlab.bmekings.Interface.SporeInterface;


import hu.bme.iit.projlab.bmekings.Entities.Insect.Insect;
import hu.bme.iit.projlab.bmekings.Map.Tecton.Tecton;
/**
 * A ISpore interfész definiálja a spórák alapvető viselkedését a játékban.
 * Azok az osztályok, amelyek implementálják ezt az interfészt, kötelesek megvalósítani
 * a spórák hatásának aktiválására és létrehozására szolgáló metódusokat.
 */
public interface SporeInterface {

    /**
     * Aktiválja a spóra hatását.
     * A metódust minden implementáló osztálynak meg kell valósítania, hogy meghatározza,
     * milyen hatást gyakorol a spóra a rovarra, amely elfogyasztja.
     */
    public void activateEffect(Insect targetInsect);

    /**
     * Létrehozza (spawnolja) a spórát a játékban.
     * A metódust minden implementáló osztálynak meg kell valósítania, hogy szimulálja
     * a spóra megjelenését a játékterepen.
     */
    public void spawnSpore();

    public void destroySpore();

    public int getNutritionValue();

    public boolean isBaseLocation(Tecton baseLocation);

    public void setBaseLocation(Tecton baseLocation);
}