package hu.bme.iit.projlab.bmekings.Player.Entomologist;

import java.util.List;

import hu.bme.iit.projlab.bmekings.Entities.Fungal.Hyphal;
import hu.bme.iit.projlab.bmekings.Entities.Insect.Insect;
import hu.bme.iit.projlab.bmekings.Entities.Spore.Spore;
import hu.bme.iit.projlab.bmekings.Map.Tecton.Tecton;
import hu.bme.iit.projlab.bmekings.Player.Player;

public class Entomologist extends Player{
    private List<Insect> controlledInsects;
    // Rovar tipusat is kezzeljuk le valahogy

    public Entomologist(String playerId) {
        super(playerId);
    }

    @Override
    public void SelectAction(){}

    public void MoveInsect(Tecton tectonToStepOn){}

    public void EatSporeInsect(List<Spore> spore, int sporeNum){}

    public void CutHyphalInsect(Hyphal hyphalToCut){}
}