package hu.bme.iit.projlab.bmekings.Program;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.temporal.TemporalField;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import hu.bme.iit.projlab.bmekings.Entities.Entity;
import hu.bme.iit.projlab.bmekings.Entities.Fungal.FungalBody;
import hu.bme.iit.projlab.bmekings.Entities.Fungal.Hyphal;
import hu.bme.iit.projlab.bmekings.Entities.Fungal.TypeCharacteristics;
import hu.bme.iit.projlab.bmekings.Entities.Insect.Insect;
import hu.bme.iit.projlab.bmekings.Entities.Spore.DuplicateSpore;
import hu.bme.iit.projlab.bmekings.Entities.Spore.HungerSpore;
import hu.bme.iit.projlab.bmekings.Entities.Spore.HyphalProtectorSpore;
import hu.bme.iit.projlab.bmekings.Entities.Spore.NormalSpore;
import hu.bme.iit.projlab.bmekings.Entities.Spore.SlowSpore;
import hu.bme.iit.projlab.bmekings.Entities.Spore.SpeedSpore;
import hu.bme.iit.projlab.bmekings.Entities.Spore.Spore;
import hu.bme.iit.projlab.bmekings.Entities.Spore.StunSpore;
import hu.bme.iit.projlab.bmekings.Interface.SporeInterface.SporeInterface;
import hu.bme.iit.projlab.bmekings.Logic.GameLogic.GameLogic;
import hu.bme.iit.projlab.bmekings.Map.Map;
import hu.bme.iit.projlab.bmekings.Map.Tecton.HyphalPreserverTecton;
import hu.bme.iit.projlab.bmekings.Map.Tecton.NoFungusTecton;
import hu.bme.iit.projlab.bmekings.Map.Tecton.Tecton;
import hu.bme.iit.projlab.bmekings.Map.Tecton.ToxicTecton;
import hu.bme.iit.projlab.bmekings.Map.Tecton.WeakTecton;
import hu.bme.iit.projlab.bmekings.Player.Entomologist.Entomologist;
import hu.bme.iit.projlab.bmekings.Player.Mycologist.Mycologist;

public class Program {
    // jatek inicializalasa
    private static ArrayList<Entomologist> entomologistPlayers = new ArrayList<>();
    private static ArrayList<Mycologist> mycologistPlayers = new ArrayList<>();
    public static GameLogic gameLogic= new GameLogic(1000, 2); //teszthez publik
    private static Params params = new Params();
    // tesztek inicializalasa
    private static FungalBody fungus = new FungalBody();
    private static SporeInterface spore = new SlowSpore();

    public static void main(String[] args) {
            // Eredeti main metódus tartalma
            boolean running = true;
            Scanner scanner = new Scanner(System.in);
            String input;

            initBasePlayers();
            gameLogic.startGame(); // ticker start 1 sec 2 player, see initialization
            while (running) {
                input = scanner.nextLine();
                consoleActions(input);

                if (input.equals("exit")) {
                    running = false;
                    gameLogic.stopGame();
                }
            }
            scanner.close();
    }

    public static void initBasePlayers() {
        GameLogic.resetPlayers();
        addEntomologist();
        addMycologist();
    }

    public static void consoleActions(String input) {
        String[] splitInput = input.split(" ");

        switch (splitInput[0]) {
            case "/addMycologist":
                addMycologist();
                break;
            case "/addEntomologist":
                addEntomologist();
                break;
            case "/generateMap":
                generateMap();
                break;
            case "/addFungalBody":
                addFungalBody(splitInput);
                break;
            case "/addHyphal":
                addHyphal(splitInput);
                break;
            case "/addInsect":
                addInsect(splitInput);
                break;
            case "/addSpore":
                addSpore(splitInput);
                break;
            case "/addTecton":
                addTecton(splitInput);
                break;
            case "/setFungalBody":
                setFungalBody(splitInput);
                break;
            case "/setHyphal":
                setHyphal(splitInput);
                break;
            case "/setInsect":
                setInsect(splitInput);
                break;
            case "/setSpore":
                setSpore(splitInput);
                break;
            case "/setTecton":
                setTecton(splitInput);
                break;
            case "/setNeighbor":
                setNeighbor(splitInput);
                break;
            case "/help":
                help();
                break;
            case "/save":
                save(splitInput);
                break;
            case "/load":
                load(splitInput);
                break;
            case "/list":
                list(splitInput);
                break;
            case "/checkout":
                checkout(splitInput);
                break;
            case "/shootsp":
                shootSpore(splitInput);
                break;
            case "/levelup":
                levelUp(splitInput);
                break;
            case "/growfb":
                growFb(splitInput);
                break;
            case "/growh":
                growH(splitInput);
                break;
            case "/move":
                move(splitInput);
                break;
            case "/eatsp":
                eatSp(splitInput);
                break;
            case "/eatinsect":
                eatInsect(splitInput);
                break;
            case "/speedupdev":
                speedUpDev(splitInput);
                break;
            case "/cuthyphal":
                cutHyphal(splitInput);
                break;
            case "/splittecton":
                splitTecton(splitInput);
                break;
            case "/deleteSpore":
                deleteSpore(splitInput);
                break;
            case "/tick":
                tick();
                break;
            case "exit":
                System.out.println("Program leallitasa...");
                break;
            default:
                System.out.println("Rossz input!");
        }
    }
    
    // ● [string]: torlendo Spore id-je
    private static void deleteSpore(String[] splitInput){
        if (splitInput.length < 2) return;
  
        for (Tecton tc : gameLogic.map.getAllTectons()) {
            for (SporeInterface s : tc.getSpores())
            if (s.getId().equals(splitInput[1])) {
                s.destroySpore();
            }
        }
    }
    
    // ● [string]: az Entomológus id-je
    private static void addEntomologist() {
        Entomologist player = new Entomologist();
        entomologistPlayers.add(player);
        GameLogic.addEntomologist(player);
    }

    // ● [string]: az Mycologist id-je
    private static void addMycologist() {
        Mycologist player = new Mycologist();
        player.setTypeCharacteristics(new TypeCharacteristics(1, 1, 1, 1));
        mycologistPlayers.add(player);
        GameLogic.addMycologist(player);
    }

    private static void generateMap() {
        gameLogic.map.generateMap();
    }

    // ● [string]: a Mycologist id-je
    // ● [string]: az Entitás tektonja
    // ● -l [szám]: a Gombatest szintje
    // ● -s [szám]: a kilőhető Spórák száma
    private static void addFungalBody(String[] splitInput) {
        if (splitInput.length < 3) return;

        Mycologist player = null;
        for (Mycologist m : mycologistPlayers) {
            if (m.getPlayerID().equals(splitInput[1])) {
                player = m;
            }
        }

        if (player == null) {
            System.out.println("Nincs ilyen jatekos!");
            return;
        }

        Tecton baseTecton = null;

        for (Tecton t : gameLogic.map.getAllTectons()) {
            if (t.getId().equals(splitInput[2]))
                baseTecton = t;
        }

        if (baseTecton == null) {
            System.out.println("Nincs ilyen Tekton!");
            return;
        }
        
        baseTecton.createFungalBody(player);

        if (splitInput.length == 3) return;

        if (splitInput.length == 5 && splitInput[3].equals("-l")) {
            baseTecton.getFungalBody().setLvlNoWrite(Integer.parseInt(splitInput[4]));
        } 

        if (splitInput.length == 5 && splitInput[3].equals("-s")) {
            baseTecton.getFungalBody().setShotSporesNumNoWrite(Integer.parseInt(splitInput[4]));
        }

        if (splitInput.length == 7 && splitInput[3].equals("-l") && splitInput[5].equals("-s")) {
            baseTecton.getFungalBody().setLvlNoWrite(Integer.parseInt(splitInput[4]));
            baseTecton.getFungalBody().setShotSporesNumNoWrite(Integer.parseInt(splitInput[6]));

        }
        else if (splitInput.length == 7 && splitInput[3].equals("-s") && splitInput[5].equals("-l")) {
            baseTecton.getFungalBody().setLvlNoWrite(Integer.parseInt(splitInput[6]));
            baseTecton.getFungalBody().setShotSporesNumNoWrite(Integer.parseInt(splitInput[4]));
        }
    }

    // ● [string]: a Mycologist id-je
    // ● [string]: az Entitás tektonja
    // ● [tecton]: az a Tecton, amivel össze van kötve
    //  ○ -d [bool]: flag, amely jelzi, hogy ki van e nőve vagy nem
    //  ○ -dt [szám]: az az idő, ami a kinövéshez kell
    //  ○ -lt [szám]: az élettartama
    //  ○ -ct [szám]: az az idő, ami után meghal, ha elvágták

    // fix sorrend a flageknek:
    // /addHyphal M-01 FB-01 T-01 T-02 -d true -lt [szám] -ct [szám]
    // fix sorrend a flageknek: 
    // /addHyphal M-01 FB-01 T-01 T-02 -d false -dt [szám] -lt [szám] -ct [szám]
    private static void addHyphal(String[] splitInput) {
        if (splitInput.length < 5) return;

        Mycologist player = null;
        for (Mycologist m : mycologistPlayers) {
            if (m.getPlayerID().equals(splitInput[1])) {
                player = m;
            }
        }

        if (player == null) {
            System.out.println("Nincs ilyen jatekos!");
            return;
        }

        Tecton baseTecton = null;
        Tecton connectTecton = null;

        for (Tecton t : gameLogic.map.getAllTectons()) {
            if (t.getId().equals(splitInput[3])) {
                baseTecton = t;
            }
            if (t.getId().equals(splitInput[4])) {
                connectTecton = t;
            }
        }

        if (baseTecton == null || connectTecton == null) {
            System.out.println("Nincs ilyen Tekton!");
            return;
        }

        FungalBody fungalBody = null;

        for (FungalBody fb : player.getControlledFunguses()) {
            if (fb.getId().equals(splitInput[2])) {
                fungalBody = fb;
            }
        }

        if (fungalBody == null) {
            System.out.println("Nincs ilyen gombatest!");
            return;
        }
        
        fungalBody.growHyphal(connectTecton);

        if (splitInput.length < 11) return;

        Hyphal hyphal = player.getHyphalList().get(player.getHyphalList().size());

        if (splitInput.length == 11) {
            if (splitInput[5].equals("-d") && splitInput[7].equals("-lt") && splitInput[9].equals("-ct")) {
                if (splitInput[6].equals("false")) {
                    System.out.println("Ilyen formatumban a -d flag csak true lehet!");
                    return;
                }
                hyphal.setDeveloped(true);
                hyphal.setLifeTime(Integer.parseInt(splitInput[8]));
                hyphal.setCutTime(Integer.parseInt(splitInput[10]));
                
            }
            else {
                System.out.println("Nincs ilyen parameter!");
            }
        }
        else if (splitInput.length == 13) {
            if (splitInput[5].equals("-d") && splitInput[7].equals("-dt") && splitInput[9].equals("-lt") && splitInput[11].equals("-ct")) {
                if (splitInput[6].equals("true")) {
                    System.out.println("Ilyen formatumban a -d flag csak false lehet!");
                    return;
                }
                hyphal.setDeveloped(false);
                hyphal.setDevelopTime(Integer.parseInt(splitInput[8]));
                hyphal.setLifeTime(Integer.parseInt(splitInput[10]));
                hyphal.setCutTime(Integer.parseInt(splitInput[12]));
            }
            else {
                System.out.println("Nincs ilyen parameter!");
            }
        }
        else {
            System.out.println("Rossz parameterezes!");
            return;
        }
    }

    // ● [string]: megadja a játékos ID-t
    // ● [string]: megadja a gombaetst ID-t
    // ○ -l [szám]: a gombatest szintjét változtatja meg
    // ○ -s [szám]: a kilőhető Spórák számát változtatja meg
    private static void setFungalBody(String[] splitInput) {
        if (splitInput.length < 5) return;

        Mycologist player = null;
        for (Mycologist m : mycologistPlayers) {
            if (m.getPlayerID().equals(splitInput[1])) {
                player = m;
            }
        }

        if (player == null) {
            System.out.println("Nincs ilyen jatekos!");
            return;
        }

        FungalBody fungalBody = null;
        for (FungalBody fb : player.getControlledFunguses()) {
            if (fb.getId().equals(splitInput[2]))
                fungalBody = fb;
        }

        if (fungalBody == null) {
            System.out.println("Nincs ilyen gombatest!");
            return;
        }

        if (splitInput.length == 5) {
            if (splitInput[3].equals("-l")) {
                fungalBody.setLevel(Integer.parseInt(splitInput[4]));
            }
            else if (splitInput[3].equals("-s")) {
                fungalBody.setShotSporesNum(Integer.parseInt(splitInput[4]));
            }
        }
        else if (splitInput.length == 7) {
            if (splitInput[3].equals("-l") && splitInput[5].equals("-s")) {
                fungalBody.setLevel(Integer.parseInt(splitInput[4]));
                fungalBody.setShotSporesNum(Integer.parseInt(splitInput[6]));
            }
            else if (splitInput[3].equals("-s") && splitInput[5].equals("-l")) {
                fungalBody.setShotSporesNum(Integer.parseInt(splitInput[4]));
                fungalBody.setLevel(Integer.parseInt(splitInput[6]));
            }
            else {
                System.out.println("Rossz parameterezes!");
            }
        }
        else {
            System.out.println("Rossz parameterezes!");
        }
    }

    // ● [string]: megadja a játékos ID-jét
    // ● [string]: megadja a fonál ID-jét
    // ○ -d [bool]: megváltoztatja a flaget, amely jelzi, hogy ki van e nőve vagy nem
    // ○ -dt [szám]: megváltoztatja azt az időt, ami a kinövéshez kell
    // ○ -lt [szám]: megváltoztatja az élettartama
    // ○ -ct [szám]: megváltoztatja azt az időt, ami után meghal, ha elvágták
    private static void setHyphal(String[] splitInput) {
        if (splitInput.length < 5) return;

        Mycologist player = null;
        for (Mycologist m : mycologistPlayers) {
            if (m.getPlayerID().equals(splitInput[1])) {
                player = m;
            }
        }

        if (player == null) {
            System.out.println("Nincs ilyen jatekos!");
            return;
        }

        Hyphal hyphal = null;
        for (Hyphal h : player.getHyphalList()) {
            if (h.getId().equals(splitInput[2]))
                hyphal = h;
        }

        if (hyphal == null) {
            System.out.println("Nincs ilyen fonal!");
            return;
        }

        if (splitInput.length == 5) {
            switch (splitInput[3]) {
                case "-d":
                    boolean set = splitInput[4].equals("true") ? true : false;
                    hyphal.setDeveloped(set);
                    break;
                case "-dt":
                    hyphal.setDevelopTime(Integer.parseInt(splitInput[4]));
                    break;
                case "-lt":
                    hyphal.setLifeTime(Integer.parseInt(splitInput[4]));
                    break;
                case "-ct":
                    hyphal.setCutTime(Integer.parseInt(splitInput[4]));
                    break;
                default:
                    System.out.println("Nincs ilyen parameter!");
                    break;
            }
        }
        else {
            System.out.print("Rossz parameterezes!");
        }
    }

    // ● [string]: a Tekton ID-je
    // ● [string]: a Spóra típusa, a string értéke lehet:
    // ○ normal
    // ○ slow
    // ○ hunger
    // ○ dupe
    // ○ speed
    // ○ stun
    // ○ hyphalp
    // /addSpore T-01 normal
    private static void addSpore(String[] splitInput) {
        if (splitInput.length < 3) return;

        Tecton baseLocation = null;
        for (Tecton t : gameLogic.map.getAllTectons()) {
            if (t.getId().equals(splitInput[1]))
                baseLocation = t;
        }

        if (baseLocation == null) {
            System.out.println("Nincs ilyen Tekton!");
            return;
        }

        switch (splitInput[2]) {
            case "normal":
                baseLocation.addSpore(new NormalSpore(baseLocation));
                break;
            case "slow":
                baseLocation.addSpore(new SlowSpore(baseLocation));
                break;
            case "hunger":
                baseLocation.addSpore(new HungerSpore(baseLocation));
                break;
            case "dupe":
                baseLocation.addSpore(new DuplicateSpore(baseLocation));
                break;
            case "speed":
                baseLocation.addSpore(new SpeedSpore(baseLocation));
                break;
            case "stun":
                baseLocation.addSpore(new StunSpore(baseLocation));
                break;
            case "hyphalp":
                baseLocation.addSpore(new HyphalProtectorSpore(baseLocation));
                break;
            default:
                System.out.println("Nincs ilyen tipus!");
                break;
        }
    }

    // ● [string]: megadja mely Spóra attribútumát változtatja meg
    // ● [szám]: megváltoztatja a Spóra tápértékét
    private static void setSpore(String[] splitInput) {
        if (splitInput.length < 2) return;
    
        SporeInterface spore = null;

        for (Tecton t : gameLogic.map.getAllTectons()) {
            for (SporeInterface sp : t.getSpores()) {
                if (sp.getId().equals(splitInput[1])) {
                    spore = sp;
                }
            }
        }

        if (spore == null) {
            System.out.println("Nincs ilyen spora!");
            return;
        }

        spore.setNutritionalValue(Integer.parseInt(splitInput[2]));
    }

    // -------------------- JÁTÉKBELI ESEMÉNYEK: ---------------------

    // ● [string]: a játékos id-je
    // ● [string]: a gombatest id-je
    // ● [string]: az egyik tekton id-je
    // ● [string]: a másik tekton id-je
    private static void shootSpore(String[] splitInput) {
        if (splitInput.length < 5) {
            System.out.println("Tul keves parameter!");
            return;
        } 
        Mycologist player = null;

        for (Mycologist m : mycologistPlayers) {
            if (m.getPlayerID().equals(splitInput[1]))
                player = m;
        }

        if (player == null) {
            System.out.println("Nincs ilyen jatekos!");
            return;
        }

        FungalBody fungalBody = null;

        for (FungalBody fb : player.getControlledFunguses()) {
            if (fb.getId().equals(splitInput[2])) {
                fungalBody = fb;
            }
        }

        if (fungalBody == null) {
            System.out.println("Nincs ilyen gombaetest!");
            return;
        }

        Tecton tecton1 = null;
        Tecton tecton2 = null;

        for (Tecton t : gameLogic.map.getAllTectons()) {
            if (t.getId().equals(splitInput[3])) {
                tecton1 = t;
            }
            else if (t.getId().equals(splitInput[4])) {
                tecton2 = t;
            }
        }

        if (tecton1 == null || tecton2 == null) {
            System.out.println("Nincs ilyen Tekton!");
            return;
        }

        ArrayList<Tecton> tectons = new ArrayList<>();
        tectons.add(tecton1);
        tectons.add(tecton2);

        player.selectFungus(fungalBody);
        params.selectedTectons = tectons;
        player.SelectAction(7, params);
    }

    // ● [string]: a játékos id-je
    // ● [string]: a fonál id-je
    // Grow fungalBody
    private static void growFb(String[] splitInput) {
        Mycologist player = null;

        for (Mycologist m : mycologistPlayers) {
            if (m.getPlayerID().equals(splitInput[1]))
                player = m;
        }

        if (player == null) {
            System.out.println("Nincs ilyen jatekos!");
            return;
        }

        // TODO

        player.SelectAction(3, params);
    }

    // ● [string]: a játékos id-je
    // ● [string]: a gombatest id-je
    // ● [string]: a tekton id-je
    private static void growH(String[] splitInput) {
        Mycologist player = null;

        for (Mycologist m : mycologistPlayers) {
            if (m.getPlayerID().equals(splitInput[1]))
                player = m;
        }

        if (player == null) {
            System.out.println("Nincs ilyen jatekos!");
            return;
        }

        FungalBody fung = null;

        for(FungalBody fungbod: player.getControlledFunguses()) {
            if(splitInput[2].equals(fungbod.getId()))
                fung=fungbod;
        }

        if (fung == null) {
            System.out.println("Nincs ilyen gombatest!");
            return;
        }

        Tecton connectTecton = null;
        for (Tecton t : gameLogic.map.getAllTectons()) {
            if (t.getId().equals(splitInput[3]))
                connectTecton = t;
        }

        if (connectTecton == null) {
            System.out.println("Nincs ilyen Tekton");
            return;
        }
        
        params.selectedTecton = connectTecton;
        player.selectFungus(fung);
        player.SelectAction(5, params);
    }
    
    // ● [string]: a játékos id-je
    // ● [string]: a fonál id-je
    private static void speedUpDev(String[] splitInput) {
        Mycologist player = null;

        for (Mycologist m : mycologistPlayers) {
            if (m.getPlayerID().equals(splitInput[1]))
                player = m;
        }

        if (player == null) {
            System.out.println("Nincs ilyen jatekos!");
            return;
        }
        Hyphal hyphal = new Hyphal();
        params.selectedHyphal = hyphal;
        player.SelectAction(7, params);
    }

    // ● [string]: a játékos id-je
    // ● [string]: a gombatest id-je
    private static void levelUp(String[] splitInput) {
        Mycologist player = null;

        for (Mycologist m : mycologistPlayers) {
            if (m.getPlayerID().equals(splitInput[1]))
                player = m;
        }

        if (player == null) {
            System.out.println("Nincs ilyen jatekos!");
            return;
        }

        FungalBody fungalBody = null;
        for (FungalBody fb : player.getControlledFunguses()) {
            if (fb.getId().equals(splitInput[2]))
                fungalBody = fb;
        }

        if (fungalBody == null) {
            System.out.println("Nincs ilyen gombatest!");
            return;
        }

        player.selectFungus(fungalBody);
        player.SelectAction(11, params);
    }
    

    
    
    // ● [string]: a játékos id-je
    // ● [string]: a rovar id-je
    // ● [string]: a tekton id-je
    private static void move(String[] splitInput) {
        if (splitInput.length < 4) {
            System.out.println("Tul keves parameter!");
            return;
        }

        Entomologist player = null;

        for (Entomologist m : entomologistPlayers) {
            if (m.getPlayerID().equals(splitInput[1]))
                player = m;
        }

        if (player == null) {
            System.out.println("Nincs ilyen jatekos!");
            return;
        }

        Insect insect = null;

        for (Insect i : player.getControlledInsects()) {
            if (i.getId().equals(splitInput[2]))
                insect = i;
        }

        if (insect == null){
            System.out.println("Nincs ilyen rovar!");
            return;
        }

        Tecton baseTecton = null;
        Tecton targetTecton = null;

        baseTecton = insect.getBase();

        for (Tecton t : gameLogic.map.getAllTectons()) {
            if (t.getId().equals(splitInput[3])) 
                targetTecton = t;
        }

        if (targetTecton == null){
            System.out.println("Nincs ilyen tekton!");
            return;
        }

        player.selectInsect(insect);
        params.selectedTecton = targetTecton;           
        player.SelectAction(2, params);
    }

    
    // ● [string]: a játékos id-je
    // ● [string]: a rovar id-je
    private static void eatSp(String[] splitInput) {
        Entomologist player = null;

        for (Entomologist e : entomologistPlayers) {
            if (e.getPlayerID().equals(splitInput[1]))
                player = e;
        }
        if (player == null) {
            System.out.println("Nincs ilyen jatekos!");
            return;
        }

        Insect eaterInsect = null;

        for (Insect insect : player.getControlledInsects()) {
            if(insect.getId().equals(splitInput[2])){
                eaterInsect=insect;
            }
        }

        if (eaterInsect == null) {
            System.out.println("Nincs ilyen rovar!");
            return;
        }

        player.selectInsect(eaterInsect);
        player.SelectAction(3, params);

    }
    
    // ● [string]: a játékos id-je
    // ● [string]: a fonal id-je
    // ● [string]: a rovar id-je
    private static void eatInsect(String[] splitInput) {
        if (splitInput.length < 3||splitInput.length>4) {
            System.out.println("Tul keves vagy sok a parameter!");
            return;
        }

        Mycologist player = null;
    
        // Find the Mycologist player by ID
        for (Mycologist m : mycologistPlayers) {
            if (m.getPlayerID().equals(splitInput[1])) {
                player = m;
            }
        }
        if (player == null) {
            System.out.println("Nincs ilyen jatekos!");
            return;
        }

        Insect insect = null;
    
        // Find the Insect by ID
        for (Entomologist e : entomologistPlayers) {
            for (Insect i : e.getControlledInsects()) {
                if (i.getId().equals(splitInput[3])) {
                    insect = i;
                }
            }
        }
    
        if (insect == null) {
            System.out.println("Nincs ilyen rovar!");
            return;
        }
    
        Hyphal hyphal = null;
        for (Hyphal h : player.getHyphalList()) {
            if (h.getId().equals(splitInput[2])) {
                hyphal = h;
            }
        }
    
        if (hyphal == null) {
            System.out.println("Nincs ilyen fonal!");
            return;
        }

        // // Perform the "eat" action
        // if (hyphal.getBase().equals(insect.getBase())) {
        //     hyphal.eatInsect(insect);
        //     System.out.println("A fonal megette a rovart!");
        // } else {
        //     System.out.println("A fonal es a rovar nem ugyanazon a Tektonon vannak!");
        // }
        
        player.selectHyphal(hyphal);
        params.selectedInsect = insect;
        player.SelectAction(10, params);
    }

    // ● [string]: a játékos id-je
    // ● [string]: a rovar id-je
    // ● [string]: a fonal id-je
    private static void cutHyphal(String[] splitInput) {
        if (splitInput.length < 3) {
            System.out.println("Tul keves parameter!");
            return;
        }

        Entomologist player = null;

        for (Entomologist m : entomologistPlayers) {
            if (m.getPlayerID().equals(splitInput[1])) {
                player = m;
            }
        }

        if (player == null) {
            System.out.println("Nincs ilyen jatekos!");
            return;
        }

        Insect insect = null;

        for (Insect i : player.getControlledInsects()) {
            if (i.getId().equals(splitInput[2])) {
                insect = i;
            }
        }

        if (insect == null) {
            System.out.println("Nincs ilyen rovar!");
            return;
        }

        Hyphal hyphal = null;

       for (int i=0; i< gameLogic.map.getAllTectons().size(); i++){
            for (Hyphal h : gameLogic.map.getAllTectons().get(i).getConnectedNeighbors().get(insect.getBase())) {
                if (h.getId().equals(splitInput[2])) {
                    hyphal = h;
                }
            }
        }

        if (hyphal == null) {
            System.out.println("Nincs ilyen fonal!");
            return;
        }
        params.selectedHyphal = hyphal;
        player.SelectAction(4, params);
    }

    
    // ● [string]: a tekton id-je
    private static void splitTecton(String[] splitInput) {
        if (splitInput.length < 1) {
            System.out.println("Tul keves parameter!");
            return;
        }

        Tecton tecton = null;

        for (Tecton t : gameLogic.map.getAllTectons()) {
            if (t.getId().equals(splitInput[1])) {
                tecton = t;
            }
        }

        if (tecton == null) {
            System.out.println("Nincs ilyen Tekton!");
            return;
        }

        gameLogic.map.splitTecton(tecton);
    }

    private static void tick() {
        gameLogic.tick();
    }


    private static void Tick() {
        gameLogic.tick();
    }

    // --------------ROVARÁSZ----------------

    // ● [string]: a játékos id-je
    // ● [string]: az Entitás tektonja
    // ● -ms [szám]: a Rovar mozgási sebessége
    // ● -mcd [szám]: a Rovar mozgásának cooldown-ja
    // ● -sl [szám]: a Rovar telítettségének maximális értéke
    // ● -ccd [szám]: a fonalvágás cooldown-ja

    // /addInsect E-01 T-01 -mcd 0
    private static void addInsect(String[] splitInput) {
        if (splitInput.length < 3) return;

        Entomologist player = null;
        for (Entomologist e : entomologistPlayers) {
            if (e.getPlayerID().equals(splitInput[1]))
                player = e;
        }

        if (player == null) {
            System.out.println("Nincs ilyen jatekos!");
            return;
        }

        Tecton baseLocation = null;
        for (Tecton t : gameLogic.map.getAllTectons()) {
            if (t.getId().equals(splitInput[2])) {
                baseLocation = t;
            }
        }

        if (baseLocation == null) {
            System.out.println("Nincs ilyen Tekton!");
            return;
        }
        
        Insect insect = null;

        if (splitInput.length == 5 && splitInput[3].equals("-mcd")) {
            insect = new Insect(1, Integer.parseInt(splitInput[4]), 100, 1, 1, baseLocation, player);
        }
        else {
            insect = new Insect(1, 0, 100, 1, 1, baseLocation, player);
        }

        insect.createInsect();

    }

    // ● [string]: megadja mely Rovar attribútumát változtatja meg
    // ● -ms [szám]: megváltoztatja a Rovar mozgási sebességét
    // ● -mcd [szám]: megváltoztatja a Rovar mozgásának cooldown-ját
    // ● -sl [szám]: megváltoztatja a Rovar telítettségének maximális értékét
    // ● -ccd [szám]: megváltoztatja a fonalvágás cooldown-ját
    // ● -bl [string]: megváltoztatja az Entitás tektonját
    
    // /setInsect E-01 I-01 -flag [érték]
    private static void setInsect(String[] splitInput) {
        if (splitInput.length < 5) {
            System.out.println("Túl kevés paraméter!");
            return;
        }
    }

    // --------------TECTON----------------

    // ● [string]: a Tecton id-ja
    // ● [string]: a tekton típusa
    // ○ normal
    // ○ toxic
    // ○ weak
    // ○ nofungus
    // ○ hyphalpres
    // ● -sc [double]: a kettétörési esélye
    // /addTecton normal -sc 10
    private static void addTecton(String[] splitInput) {
        if (splitInput.length < 2) {
            return;
        }

        double sc = 0;
        double hdt = 6;

        if (splitInput.length > 3) {
            sc = Integer.parseInt(splitInput[3]);
        }

        switch (splitInput[1]) {
            case "normal":
                Tecton nTecton = new Tecton(sc, false, false);
                gameLogic.map.addTecton(nTecton);
                break;
            case "toxic":
                ToxicTecton tTecton = new ToxicTecton(hdt, sc, false, false);
                gameLogic.map.addTecton(tTecton);
                break;
            case "weak":
                WeakTecton wTecton = new WeakTecton(false, sc, false, false);
                gameLogic.map.addTecton(wTecton);
                break;
            case "nofungus":
                NoFungusTecton nfTecton = new NoFungusTecton(sc, false, false);
                gameLogic.map.addTecton(nfTecton);
                break;
            case "hyphalpres":
                HyphalPreserverTecton hpTecton = new HyphalPreserverTecton(sc, false, false);
                gameLogic.map.addTecton(hpTecton);
                break;
        }
    }

    // ● [string]: a Tecton id-ja
    // ● [string]: a szomszédos Tecton id-ja
    private static void setNeighbor(String[] splitInput) {
        if (splitInput.length < 3) return;

        Tecton chosenTecton = null;
        Tecton neighborTecton = null;

        for (Tecton t : gameLogic.map.getAllTectons()) {
            if (t.getId().equals(splitInput[1])) {
                chosenTecton = t;
            }
            else if (t.getId().equals(splitInput[2])) {
                neighborTecton = t;
            }
        }
        
        if (chosenTecton == null) {
            System.out.println("Nincs ilyen Tekton!");
            return;
        }

        if (neighborTecton == null) {
            System.out.println("Nincs ilyen Tekton, nem adtal hozza szomszedot!");
            return;
        }

        chosenTecton.getNeighbors().add(neighborTecton);
        neighborTecton.getNeighbors().add(chosenTecton);
    }

    // ● [string]: megadja mely Tecton attribútumát változtatja meg
    // ● -sc [double]: megváltoztatja a kettétörés esélyét
    // [0]/setTecton [1]T-01 [2]-sc [3]100
    private static void setTecton(String[] splitInput) {
        Tecton tecton = null;
        double oldSc = -1;

        for (Tecton t : gameLogic.map.getAllTectons()) {
            if (t.getId().equals(splitInput[1])) {
                tecton = t;
                oldSc = t.getSplitChance();
            }
        }

        if (oldSc == -1 || tecton == null) {
            System.out.println("Nincs ilyen Tekton!");
            return;
        }

        if (splitInput.length > 3) {
            tecton.setSplitChance(Double.parseDouble(splitInput[3]));
        }
        else {
            System.out.println("Nincs eleg parameter megadva!");
        }
    }

    // --------------EGYÉB----------------

    // Az összes parancs listázása
    private static void help() {
        String[] commands = {
        "/addMycologist",
        "/addEntomologist",
        "/addfungus",
        "/addinsect",
        "/addspore",
        "/addTecton",
        "/setfungus",
        "/setinsect",
        "/setspore",
        "/settecton",
        "/help",
        "/save",
        "/load",
        "/list",
        "/checkout",
        "/shootsp",
        "/levelup",
        "/growfb",
        "/growh",
        "/move",
        "/eatsp",
        "/eatinsect",
        "/speedupdev",
        "/cuthyphal",
        "/splittecton",
        "exit"
        };

        System.out.println("Elerheto parancsok:");
        for (String command : commands) {
            System.out.println(command);
        }
    }

    // ● [string]: a kimenetet tároló fájl neve
    private static void load(String[] splitInput) {
        if (splitInput.length < 2) {
        System.out.println("Túl kevés paraméter! Használat: /load [fájlnév]");
        return;
    }

    String fileName = splitInput[1];
    try {
        // Jelenlegi állapot törlése
        entomologistPlayers.clear();
        mycologistPlayers.clear();
        if (gameLogic != null) {
            gameLogic = null; // Kihagyjuk az inicializálást a StackOverflowError elkerülése érdekében
        }

        // Ideiglenes tárolók
        HashMap<String, Tecton> tectonMap = new HashMap<>();
        HashMap<String, FungalBody> fungalBodyMap = new HashMap<>();
        HashMap<String, Entomologist> entomologistMap = new HashMap<>();
        HashMap<String, Mycologist> mycologistMap = new HashMap<>();

        // Fájl beolvasása
        String content = Files.readString(Paths.get(fileName));
        String[] lines = content.split("\n");

        // Objektumok létrehozása
        for (String line : lines) {
            if (line.trim().isEmpty()) continue;
            String[] parts = line.split(":");
            if (parts.length < 2) continue;

            switch (parts[0]) {
                case "Entomologist":
                    Entomologist e = new Entomologist(parts[1]);
                    entomologistPlayers.add(e);
                    entomologistMap.put(parts[1], e);
                    break;

                case "Insect":
                    Entomologist entomologist = entomologistMap.get(parts[2]);
                    if (entomologist != null) {
                        Insect insect = new Insect(Integer.parseInt(parts[3]), Integer.parseInt(parts[4]),Integer.parseInt(parts[5]), Integer.parseInt(parts[6]),0 ,tectonMap.get(parts[2]), entomologist);
                        entomologist.addInsect(insect);
                    }
                    break;

                case "Mycologist":
                    Mycologist m = new Mycologist(parts[1]);
                    mycologistPlayers.add(m);
                    mycologistMap.put(parts[1], m);
                    break;

                case "FungalBody":
                    Mycologist mycologist = mycologistMap.get(parts[2]);
                    if (mycologist != null) {
                        TypeCharacteristics characteristics = new TypeCharacteristics(
                            Integer.parseInt(parts[6]), // shootingRange
                            Integer.parseInt(parts[7]), // sporeProductionIntensity
                            Integer.parseInt(parts[8]), // startingHyphalNum
                            Integer.parseInt(parts[9])  // sporeCapacity
                        );
                        FungalBody fb = new FungalBody(
                            Integer.parseInt(parts[4]), // currLevel
                            Integer.parseInt(parts[5]), // shotSporesNum
                            characteristics,
                            tectonMap.getOrDefault(parts[3], null),
                            mycologist
                        );
                        mycologist.addFungus(fb);
                        fungalBodyMap.put(parts[1], fb);
                    }
                    break;

                case "Hyphal":
                    Mycologist hyphalOwner = mycologistMap.get(parts[2]);
                    if (hyphalOwner != null) {
                        Hyphal h = new Hyphal(
                            tectonMap.getOrDefault(parts[4], null), // connectedTecton
                            Boolean.parseBoolean(parts[5]), // developed
                            Integer.parseInt(parts[6]), // developTime
                            Integer.parseInt(parts[7]), // lifeTime
                            Integer.parseInt(parts[8]), // cutTime
                            tectonMap.getOrDefault(parts[3], null), // baseLocation
                            hyphalOwner
                        );
                        hyphalOwner.addHyphal(h);
                        GameLogic.addEntity(h);
                    }
                    break;

                case "Spore":
                    Mycologist sporeMycologist = mycologistMap.get(parts[2]);
                    if (sporeMycologist != null) {
                        SporeInterface s = createSpore(parts[5], parts[1], tectonMap.getOrDefault(parts[3], null));
                        ((Spore)s).setNutritionalValue(Integer.parseInt(parts[4]));
                        if (s.getBaseLocation() != null) {
                            s.getBaseLocation().addSpore(s);
                        }
                    }
                    break;

                case "Tecton":
                    Tecton t = new Tecton();
                    t.setSplitChance(Double.parseDouble(parts[2]));
                    t.setOccupiedByFungus(Boolean.parseBoolean(parts[4]));
                    t.getFlag().fungalApproved = Boolean.parseBoolean(parts[5]);
                    t.getFlag().hyphalApproved = Boolean.parseBoolean(parts[6]);
                    t.getFlag().oneHyphalApproved = Boolean.parseBoolean(parts[7]);
                    tectonMap.put(parts[1], t);
                    if (gameLogic != null && gameLogic.map != null) {
                        gameLogic.map.addTecton(t);
                    }
                    break;

                case "TestFungalBody":
                    TypeCharacteristics testCharacteristics = new TypeCharacteristics(
                        Integer.parseInt(parts[5]), // shootingRange
                        Integer.parseInt(parts[6]), // sporeProductionIntensity
                        Integer.parseInt(parts[7]), // startingHyphalNum
                        Integer.parseInt(parts[8])  // sporeCapacity
                    );
                    fungus = new FungalBody(
                        Integer.parseInt(parts[3]), // currLevel
                        Integer.parseInt(parts[4]), // shotSporesNum
                        testCharacteristics,
                        tectonMap.getOrDefault(parts[2], null),
                        null // Nincs owner a teszt objektumnak
                    );
                    break;

                case "TestSpore":
                    spore = createSpore(parts[4], parts[1], tectonMap.getOrDefault(parts[2], null));
                    ((Spore)spore).setNutritionalValue(Integer.parseInt(parts[3]));
                    break;
            }
        }

        System.out.println("Játékállapot betöltve: " + fileName);
    } catch (IOException e) {
        System.out.println("Hiba a betöltés során: " + e.getMessage());
    } catch (Exception e) {
        System.out.println("Érvénytelen fájlformátum: " + e.getMessage());
    }
    }
    private static SporeInterface createSpore(String type, String id, Tecton baseLocation) {
        SporeInterface spore;
        switch (type) {
            case "SlowSpore":
                spore = new SlowSpore(baseLocation);
                break;
            case "NormalSpore":
                spore = new NormalSpore(baseLocation);
                break;
            case "DuplicateSpore":
                spore = new DuplicateSpore(baseLocation);
                break;
            case "HungerSpore":
                spore = new HungerSpore(baseLocation);
                break;
            case "SpeedSpore":
                spore = new SpeedSpore(baseLocation);
                break;
            case "StunSpore":
                spore = new StunSpore(baseLocation);
                break;
            default:
                spore = new NormalSpore(baseLocation); // Alapértelmezett típus
                break;
        }
        return spore;
    }

    private static Entomologist findEntomologist(String id) {
    for (Entomologist e : entomologistPlayers) {
            if (e.getPlayerID().equals(id)) {
                return e;
            }
        }
        return null;
    }

    private static Mycologist findMycologist(String id) {
        for (Mycologist m : mycologistPlayers) {
            if (m.getPlayerID().equals(id)) {
                return m;
            }
        }
        return null;
    }

    // ● [string]: a bemenetet tároló fájl neve
    private static void save(String[] splitInput) {
        if (splitInput.length < 2) {
        System.out.println("Túl kevés paraméter! Használat: /save [fájlnév]");
        return;
    }

    String fileName = splitInput[1];
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            // Entomológusok és rovarok mentése
            for (Entomologist e : entomologistPlayers) {
                writer.println("Entomologist:" + e.getPlayerID());
                for (Insect i : e.getControlledInsects()) {
                    writer.println("Insect:" + i.getId() + ":" + 
                            (i.getBase() != null ? i.getBase().getId() : "null") + ":" + 
                            i.getMovingSpeed() + ":" + i.getMovingCD() + ":" + 
                            i.getStomachLimit() + ":" + i.getCutCooldown());
                }
            }

            // Mikológusok, gombák, fonalak, spórák mentése
            for (Mycologist m : mycologistPlayers) {
                writer.println("Mycologist:" + m.getPlayerID());
                for (FungalBody fb : m.getControlledFunguses()) {
                    writer.println("FungalBody:" + fb.getId() + ":" + m.getPlayerID() + ":" +
                            (fb.getBase() != null ? fb.getBase().getId() : "null") + ":" +
                            fb.getCurrLvl() + ":" + fb.getShotSporesNum() + ":" +
                            fb.getCharacteristics().shootingRange + ":" +
                            fb.getCharacteristics().sporeProductionIntensity + ":" +
                            fb.getCharacteristics().startingHyphalNum + ":" +
                            fb.getCharacteristics().sporeCapacity);
                    for (Hyphal h : m.getHyphalList()) { // Mycologist.getHyphalList() használata
                        writer.println("Hyphal:" + h.getId() + ":" + 
                                (fb.getBase() != null ? fb.getId() : "null") + ":" +
                                (h.getBase() != null ? h.getBase().getId() : "null") + ":" +
                                (h.getConnectedTecton() != null ? h.getConnectedTecton().getId() : "null") + ":" +
                                h.getDeveloped() + ":" + h.getDevelopTime() + ":" +
                                h.getLifeTime() + ":" + h.getCutTime());
                    }
                }
                for(int index=0;index<m.getControlledFunguses().size();index++){
                    for (SporeInterface s : m.getControlledFunguses().get(index).getSpores()) {
                    String sporeType = getSporeType(s);
                    writer.println("Spore:" + s.getId() + ":" + m.getPlayerID() + ":" +
                            (s.getBaseLocation() != null ? s.getBaseLocation().getId() : "null") + ":" +
                            ((Spore)s).getNutritionValue() + ":" + sporeType);
                    }
                }

            }

            // Tektonok mentése
            if (gameLogic != null && gameLogic.map != null) {
                for (Tecton t : gameLogic.map.getAllTectons()) {
                    writer.println("Tecton:" + t.getId() + ":" + t.getSplitChance() + ":" +
                            t.isOccupiedByInsect() + ":" + t.isOccupiedByFungus() + ":" +
                            t.getFlag().fungalApproved + ":" + t.getFlag().hyphalApproved + ":" +
                            t.getFlag().oneHyphalApproved);
                }
            }

            // Teszt objektumok mentése
            writer.println("TestFungalBody:" + fungus.getId() + ":" +
                    (fungus.getBase() != null ? fungus.getBase().getId() : "null") + ":" +
                    fungus.getCurrLvl() + ":" + fungus.getShotSporesNum() + ":" +
                    fungus.getCharacteristics().shootingRange + ":" +
                    fungus.getCharacteristics().sporeProductionIntensity + ":" +
                    fungus.getCharacteristics().startingHyphalNum + ":" +
                    fungus.getCharacteristics().sporeCapacity);
            writer.println("TestSpore:" + spore.getId() + ":" +
                    (spore.getBaseLocation() != null ? spore.getBaseLocation().getId() : "null") + ":" +
                    ((Spore)spore).getNutritionValue() + ":" + getSporeType(spore));

            System.out.println("Jatekallapot mentve: " + fileName);
        } catch (IOException e) {
            System.out.println("Hiba a mentes soran: " + e.getMessage());
        }
    }

    private static String getSporeType(SporeInterface spore) {
        if (spore instanceof SlowSpore) return "SlowSpore";
        if (spore instanceof NormalSpore) return "NormalSpore";
        if (spore instanceof DuplicateSpore) return "DuplicateSpore";
        if (spore instanceof HungerSpore) return "HungerSpore";
        if (spore instanceof SpeedSpore) return "SpeedSpore";
        if (spore instanceof StunSpore) return "StunSpore";
    return "UnknownSpore";
}

    // ● [string]: a példányok típusa, értéke lehet:
    // ○ e: entitások
    // ○ t: tektonok
    // ○ s: spórák
    // ○ i: rovarok
    // ○ fb: gombatestek
    // ○ h: fonalak
    // ● -d: attribútumokkal együtt listázza ki a példányokat
    // /list e -d
    private static void list(String[] splitInput) {
        if (splitInput.length > 2 ) {
            if (splitInput[2].equals("-d")) {
                // attribútumokkal
                switch (splitInput[1]) {
                    case "e":
                        for (Entity e : GameLogic.getEntityList()) {
                            System.out.println("ID: " + e.getId());
                            System.out.println("BaseLocation: " + e.getBase().getId());
                        }
                        break;
                    case "t":
                        for (Tecton t : gameLogic.map.getAllTectons()) {
                            System.out.println("ID: " + t.getId());
                            System.out.println("SplitChance: " + t.getSplitChance());

                            String msg = t.isOccupiedByInsect() ? "true" : "false";
                            System.out.println("OccupiedByInsect: " + msg);
                            
                            msg = t.isOccupiedByFungus() ? "true" : "false";

                            System.out.println("OccupiedByFungus: " + msg);

                            System.out.println("Neighbours: ");
                            for(Tecton tct : t.getNeighbors()){
                                System.out.print(tct.getId() + "\t");
                            }
                            System.err.println("");
                        }
                        break;
                    case "s":
                        for (Tecton t : gameLogic.map.getAllTectons()) {
                            for (SporeInterface sp : t.getSpores()) {
                                System.out.println("ID: " + sp.getId());
                                System.out.println("BaseLocation: " + sp.getBaseLocation());
                                System.out.println("NutritionalValue: " + sp.getNutritionValue());
                                System.out.println("Class: " + sp.getClass());
                            }
                        }
                        break;
                    case "i":
                        for (Insect i : entomologistPlayers.get(0).getControlledInsects()) {
                            System.out.println("ID: " + i.getId());
                            System.out.println("BaseLocation: " + i.getBase().getId());
                            System.out.println("MovingSpeed: " + i.getMovingSpeed());
                            System.out.println("MovingCD: " + i.getMovingCD());
                            System.out.println("CurrStomachFullness: " + i.getCurrStomachFullness());
                            System.out.println("StomachLimit: " + i.getStomachLimit());
                            System.out.println("CutCD: " + i.getCutCooldown());
                        }
                        break;
                    case "fb":
                        for (Mycologist m : mycologistPlayers) {
                            for (FungalBody fb : m.getControlledFunguses()) {
                                System.out.println("ID: " + fb.getId());
                                System.out.println("BaseLocation: " + fb.getBase().getId());
                                System.out.println("CurrentLevel: " + fb.getCurrLvl());
                                System.out.println("ShotSporesNum: " + fb.getShotSporesNum());
                                System.out.println("ShootingRange: " + fb.getCharacteristics().getShootingRange());
                                System.out.println("SporeCapacity: " + fb.getCharacteristics().getSporeCapacity());
                                System.out.println("SporeProductionIntensity: " + fb.getCharacteristics().getSporeProductionIntensity());
                                System.out.println("StartingHyphalNum: " + fb.getCharacteristics().getStartingHyphalNum());
                            }
                        }
                        break;
                    case "h":
                        for (Mycologist m : mycologistPlayers) {
                            for (Hyphal h : m.getHyphalList()) {
                                System.out.println("ID: " + h.getId());
                                System.out.println("BaseLocation: " + h.getBase().getId());
                                System.out.println("ConnectedTecton: " + h.getConnectedTecton().getId());
                                String msg = h.getDeveloped() ? "true" : "false";
                                System.out.println("Developed: " + msg);
                                System.out.println("DevelopTime: " + h.getDevelopTime());
                                System.out.println("LifeTime: " + h.getLifeTime());
                                System.out.println("CutTime: " + h.getCutTime());
                            }
                        }
                        break;
                    default:
                        System.out.println("Nincs ilyen parameter!");
                        break;
                }   
            }
            else {
                System.out.println("Nincs ilyen parameter!");
            }
        }
        else if (splitInput.length == 2){
            // attribútumok nélkül
            switch (splitInput[1]) {
                case "e":
                    for (Entity e : GameLogic.getEntityList()) {
                        System.out.println("ID: " + e.getId());
                        System.out.println("BaseLocation: " + e.getBase().getId());
                    }
                    break;
                case "t":
                    for (Tecton t : gameLogic.map.getAllTectons()) {
                        System.out.println("ID: " + t.getId());
                    }
                    break;
                case "s":
                    for (Tecton t : gameLogic.map.getAllTectons()) {
                        for (SporeInterface sp : t.getSpores()) {
                            System.out.println("ID: " + sp.getId());
                            System.out.println("BaseLocation: " + sp.getBaseLocation().getId());
                        }
                    }
                    break;
                case "i":
                    for (Insect i : entomologistPlayers.get(0).getControlledInsects()) {
                        System.out.println("ID: " + i.getId());
                        System.out.println("BaseLocation: " + i.getBase().getId());
                    }
                    break;
                case "fb":
                    for (Mycologist m : mycologistPlayers) {
                        for (FungalBody fb : m.getControlledFunguses()) {
                            System.out.println("ID: " + fb.getId());
                            System.out.println("BaseLocation: " + fb.getBase().getId());
                        }
                    }
                    break;
                case "h":
                    for (Mycologist m : mycologistPlayers) {
                        for (Hyphal h : m.getHyphalList()) {
                            System.out.println("ID: " + h.getId());
                            System.out.println("BaseLocation: " + h.getBase().getId());
                        }
                    }
                    break;
                default:
                    System.out.println("Nincs ilyen parameter!");
                    break;
            }
        }
        else {
            System.out.println("Tul keves parameter!");
        }
    }

    // ● [string]: a példány id-je
    private static void checkout(String[] splitInput) {
        String[] splitID = splitInput[1].split("-");
        switch (splitID[0]) {
            case "T":
                // Tecton
                for (Tecton t : gameLogic.map.getAllTectons()) {
                    if (t.getId().equals(splitInput[1])) {
                        System.out.println("ID: " + t.getId());
                        System.out.println("SplitChance: " + t.getSplitChance());
                        if (t.isOccupiedByInsect()) {
                            System.out.println("OccupiedByInsect: true");
                        }
                        else {
                            System.out.println("OccupiedByInsect: false");
                        }

                        if (t.isOccupiedByFungus()) {
                            System.out.println("OccupiedByFungus: true");
                        }
                        else {
                            System.out.println("OccupiedByFungus: false");
                        }
                    }
                }
                break;
            case "FB":
                // FungalBody
                break;
            case "H":
                // Hyphal
                break;
            case "SP":
                // Spore
                break;
            case "I":
                // Insect
                break;
            default:
                break;
        }
    }
}