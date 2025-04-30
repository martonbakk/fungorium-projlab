package hu.bme.iit.projlab.bmekings.Effects.HungerEffect;

import hu.bme.iit.projlab.bmekings.Effects.Effect.Effect;
import hu.bme.iit.projlab.bmekings.Entities.Insect.Insect;


public class HungerEffect extends Effect {

    public HungerEffect() {
        super("Hunger");
    }
    
    @Override
    public void apply(Insect target) {
        target.applyEffect(this);
        target.hungerEffectActivate(10);
    }
}