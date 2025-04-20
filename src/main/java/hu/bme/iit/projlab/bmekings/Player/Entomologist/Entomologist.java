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
    public void SelectAction(int actionType, Object object ){
        switch (actionType) {
            case 1:
                // selectInsect
                break;
            case 2:
                // selectTecton
                break;
            case 3:
                MoveInsect((Tecton) object);
                break;
            case 4:
                EatSporeInsect((List<Spore>) object, 0);
                break;
            case 5:
                CutHyphalInsect((Hyphal) object);
                break;
            default:
                System.out.println("Invalid action type");
        }
    }

    public void MoveInsect(Tecton tectonToStepOn){}

    public void EatSporeInsect(List<Spore> spore, int sporeNum){}

    public void CutHyphalInsect(Hyphal hyphalToCut){}
}