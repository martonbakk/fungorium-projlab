package hu.bme.iit.projlab.bmekings.Effects.HyphalEffect;

import hu.bme.iit.projlab.bmekings.Effects.Effect.Effect;
import hu.bme.iit.projlab.bmekings.Entities.Insect.Insect;


public class HyphalEffect extends Effect {

    public HyphalEffect() {
        super("Hyphal");
    }
    
    @Override
    public void apply(Insect target) {
        target.applyEffect(this);
        target.HyhalEffectActivate(10);
    }
}