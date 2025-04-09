package hu.bme.iit.projlab.bmekings.Effects.Effect;

import hu.bme.iit.projlab.bmekings.Entities.Insect.Insect;

public abstract class Effect {
    protected String name;

    public Effect(String name) {
        this.name = name;
    }

    public abstract void apply(Insect target);
}