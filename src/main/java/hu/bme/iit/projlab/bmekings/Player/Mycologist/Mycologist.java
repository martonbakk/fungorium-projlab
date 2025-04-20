package hu.bme.iit.projlab.bmekings.Player.Mycologist;

import java.util.List;

import hu.bme.iit.projlab.bmekings.Entities.Fungal.FungalBody;
import hu.bme.iit.projlab.bmekings.Entities.Fungal.Hyphal;
import hu.bme.iit.projlab.bmekings.Map.Tecton.Tecton;
import hu.bme.iit.projlab.bmekings.Player.Player;

public class Mycologist extends Player{
    List<FungalBody> controlledFunguses;
    Object fungus; // Fungus type lekezelese hianyzik
    // Fungus type lekezelese hianyzik

    public Mycologist(String playerId) {
        super(playerId);
    }

    @Override
    public void SelectAction(int actionType, Object object) {
        switch (actionType) {
            case 1:
                // selectFungus
                break;
            case 2:
                // selectHyphal
                break;
            case 3:
                growFungus((FungalBody) object);
                break;
            case 4:
                // growHyphal
                break;
            case 5:
                // selectTectons
                break;
            case 6:
                // destroyFungus
                break;
            default:
                System.out.println("Invalid action type");
        }
        
    }

    private void selectFungus(FungalBody fungus){}

    private void selectHyphal(Hyphal hyphal){}

    private void growFungus(FungalBody fungus){
        controlledFunguses.add(fungus);
    }

    private void growHyhal(Tecton tecton){}

    private void selectTectons(List<Tecton> tectons){}

    private void destroyFungus(FungalBody fungalToDestroy){}

    // getTypeCharacteristics(){}
}