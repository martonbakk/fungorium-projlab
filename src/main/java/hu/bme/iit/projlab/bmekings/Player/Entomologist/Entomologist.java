package hu.bme.iit.projlab.bmekings.Player.Entomologist;

import java.util.List;

import hu.bme.iit.projlab.bmekings.Entities.Fungal.Hyphal;
import hu.bme.iit.projlab.bmekings.Entities.Insect.Insect;
import hu.bme.iit.projlab.bmekings.Interface.SporeInterface.SporeInterface;
import hu.bme.iit.projlab.bmekings.Logger.ClassLabel;
import hu.bme.iit.projlab.bmekings.Map.Tecton.Tecton;
import hu.bme.iit.projlab.bmekings.Player.Player;
import hu.bme.iit.projlab.bmekings.Program.Params;

@ClassLabel("Entomologist")
public class Entomologist extends Player{
    private List<Insect> controlledInsects;
    // Rovar tipusat is kezzeljuk le valahogy

    public Entomologist(String playerId) {
        super(playerId);
        //this.playerId=IDGenerator.generateID("E");
    }
    public List<Insect> getControlledInsects() { return controlledInsects; }

    @Override
    public void SelectAction(int actionType, Params param ){
        switch (actionType) {
            case 1:
                // selectInsect
                break;
            case 2:
                // selectTecton
                break;
            case 3:
                MoveInsect(param.selectedTecton);
                break;
            case 4:
                EatSporeInsect(param.selectedSpores, param.sporeNum);
                break;
            case 5:
                CutHyphalInsect(param.selectedHyphal);
                break;
            default:
                System.out.println("Invalid action type");
        }
    }

    public void MoveInsect(Tecton tectonToStepOn){}

    public void EatSporeInsect(List<SporeInterface> spore, int sporeNum){}

    public void CutHyphalInsect(Hyphal hyphalToCut){}

    

    public void deleteControlledInsect(Insect controlledInsect){
        controlledInsects.remove(controlledInsect);
    }

    //placeholder !!!
    public void addInsect(Insect insect){
        controlledInsects.add(insect);
    }

}