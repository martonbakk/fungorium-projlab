package hu.bme.iit.projlab.bmekings.Effects.SpeedEffect;

import hu.bme.iit.projlab.bmekings.Effects.Effect.Effect;
import hu.bme.iit.projlab.bmekings.Entities.Insect.Insect;


public class SpeedEffect extends Effect {
    private int speed;

    public SpeedEffect(int speed) {
        super("Speed");
        this.speed=speed;
    }

    @Override
    public void apply(Insect target) {
        target.applyEffect(this);
        
    }
}