package hu.bme.iit.projlab.bmekings.Program;

import java.util.ArrayList;

import hu.bme.iit.projlab.bmekings.Entities.Fungal.FungalBody;
import hu.bme.iit.projlab.bmekings.Entities.Fungal.Hyphal;
import hu.bme.iit.projlab.bmekings.Interface.SporeInterface.SporeInterface;
import hu.bme.iit.projlab.bmekings.Map.Tecton.Tecton;

public class Params {
    public String fungalId;
    public SporeInterface sporeToAdd;
    public FungalBody selectedFungus;
    public ArrayList<Tecton> selectedTectons;
    public Hyphal selectedHyphal;
    public Tecton selectedTecton;
    public ArrayList<SporeInterface> selectedSpores;
    public int sporeNum;
}
