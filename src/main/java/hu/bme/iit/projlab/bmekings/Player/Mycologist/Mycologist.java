package hu.bme.iit.projlab.bmekings.Player.Mycologist;

import java.util.List;

import hu.bme.iit.projlab.bmekings.Entities.Fungal.FungalBody;
import hu.bme.iit.projlab.bmekings.Entities.Fungal.Hyphal;
import hu.bme.iit.projlab.bmekings.Map.Tecton.Tecton;
import hu.bme.iit.projlab.bmekings.Player.Player;

public class Mycologist extends Player{
    List<FungalBody> controlledFunguses;
    // Fungus type lekezelese hianyzik

    public Mycologist(String playerId) {
        super(playerId);
    }

    @Override
    public void SelectAction(){}

    void selectFungus(FungalBody fungus){}

    void selectHyphal(Hyphal hyphal){}

    void growHyhal(Tecton tecton){}

    void selectTectons(List<Tecton> tectons){}

    void destroyFungus(FungalBody fungalToDestroy){}

    // getTypeCharacteristics(){}
}