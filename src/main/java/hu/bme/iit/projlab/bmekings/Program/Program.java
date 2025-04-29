package hu.bme.iit.projlab.bmekings.Program;

import java.util.ArrayList;
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
import hu.bme.iit.projlab.bmekings.Entities.Spore.StunSpore;
import hu.bme.iit.projlab.bmekings.Interface.SporeInterface.SporeInterface;
import hu.bme.iit.projlab.bmekings.Logic.GameLogic.GameLogic;
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

        // TODO

        player.SelectAction(4, params);
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
        Hyphal hyphal = new Hyphal(); // flageknek megfelelően be kell majd álltani
        params.selectedHyphal = hyphal;       //TODO: Milyen tipusu gombat akarunk hozzaadni?
        player.SelectAction(7, params); // TODO: melyik mycologistnak adjuk hozzá?
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
    }
    
    // ● [string]: a játékos id-je
    // ● [string]: a rovar id-je
    // ● [string]: a tekton id-je
    private static void move(String[] splitInput) {
    }
    
    // ● [string]: a játékos id-je
    // ● [string]: a rovar id-je
    // ● [string]: a spóra id-je
    private static void eatSp(String[] splitInput) {
    }
    
    // ● [string]: a játékos id-je
    // ● [string]: a fonal id-je
    // ● [string]: a rovar id-je
    private static void eatInsect(String[] splitInput) {
    }
    
    // ● [string]: a játékos id-je
    // ● [string]: a rovar id-je
    // ● [string]: a fonal id-je
    private static void cutHyphal(String[] splitInput) {
    }
    
    // TODO
    private static void splitTecton(String[] splitInput) {
    }

    private static void tick() {
        gameLogic.tick();
    }

    // --------------ROVARÁSZ----------------

    // ● [string]: az Entitás id-je
    // ● [string]: az Entitás tektonja
    // ● -ms [szám]: a Rovar mozgási sebessége
    // ● -mcd [szám]: a Rovar mozgásának cooldown-ja
    // ● -sl [szám]: a Rovar telítettségének maximális értéke
    // ● -ccd [szám]: a fonalvágás cooldown-ja
    private static void addInsect(String[] splitInput) {
    }

    // ● [string]: megadja mely Rovar attribútumát változtatja meg
    // ● -ms [szám]: megváltoztatja a Rovar mozgási sebességét
    // ● -mcd [szám]: megváltoztatja a Rovar mozgásának cooldown-ját
    // ● -sl [szám]: megváltoztatja a Rovar telítettségének maximális értékét
    // ● -ccd [szám]: megváltoztatja a fonalvágás cooldown-ját
    // TODO LEMARADT VALAMI
    private static void setInsect(String[] splitInput) {
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
    // inkabb folder legyen
    private static void save(String[] splitInput) {
        // TODO -- szerializálás
        // public ArrayList<Tecton> getTectons(ArrayList<String> tectonIds)   
        // Game Logicban van a map
        
        // ez kell a 
        //ArrayList<Tecton> saveTecton =  gameLogic.map.getAllTectons();
        

        // játékosok, rovarok, gombák, spórák, fonalak, 
    }

    // ● [string]: a bemenetet tároló fájl neve
    private static void load(String[] splitInput) {
        // TODO -- szerializálás
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
                            System.out.println("BaseLocation: " + sp.getBaseLocation());
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