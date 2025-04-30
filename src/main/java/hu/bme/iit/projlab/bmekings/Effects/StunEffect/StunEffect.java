package hu.bme.iit.projlab.bmekings.Effects.StunEffect;

import hu.bme.iit.projlab.bmekings.Effects.Effect.Effect;
import hu.bme.iit.projlab.bmekings.Entities.Insect.Insect;


public class StunEffect extends Effect {

    public StunEffect() {
        super("Stun");
    }

    @Override
    public void apply(Insect target) {
        target.applyEffect(this);
    }
}