package hu.bme.iit.projlab.bmekings.Entities;
import hu.bme.iit.projlab.bmekings.Interface.Listener.Listener;
import hu.bme.iit.projlab.bmekings.Map.Tecton.Tecton;

/**
 * Absztrakt osztály, amely az entitások és figurák közös alapadatait tárolja.
 * Az entitások a játékban szereplő egyedeket reprezentálják, például gombatesteket, spórákat vagy rovarokat.
 * Implementálja a Listener interfészt, így minden leszármazottjának biztosítania kell az update() metódus implementációját.
 */
public abstract class Entity implements Listener {

    protected String id;
    protected Tecton baseLocation;

    public Entity() {
        this.id="";
        this.baseLocation=null;
    }

    public Entity(String id, Tecton baseLocation) {
        this.id = id;
        this.baseLocation = baseLocation;
        System.out.println("Uj objektum [" + id + "] letrejott!");
    }

    public Tecton getBase(){
        return baseLocation;
    }

    public String getId() {
        return id;
    }

}