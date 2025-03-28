package main.java.hu.bme.iit.projlab.bmekings.Entities;

import main.java.hu.bme.iit.projlab.bmekings.Interface.Listener.Listener;
import main.java.hu.bme.iit.projlab.bmekings.Map.Tecton.Tecton;

/**
 * Absztrakt osztály, amely az entitások és figurák közös alapadatait tárolja.
 * Az entitások a játékban szereplő egyedeket reprezentálják, például gombatesteket, spórákat vagy rovarokat.
 * Implementálja a Listener interfészt, így minden leszármazottjának biztosítania kell az update() metódus implementációját.
 */
public abstract class Entity implements Listener {

    private String id;
    private Tecton baseLocation;

    public Entity() {
        this.id="";
        this.baseLocation=null;
    }

    public Entity(String id, Tecton baseLocation) {
        this.id = id;
        this.baseLocation = baseLocation;
    }
}