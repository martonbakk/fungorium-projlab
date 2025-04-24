package hu.bme.iit.projlab.bmekings.Program;

import java.util.Scanner;
import java.util.ArrayList;

import hu.bme.iit.projlab.bmekings.Entities.Entity;
import hu.bme.iit.projlab.bmekings.Entities.Entity.*;
import hu.bme.iit.projlab.bmekings.Entities.Fungal.FungalBody;
import hu.bme.iit.projlab.bmekings.Entities.Fungal.Hyphal;
import hu.bme.iit.projlab.bmekings.Entities.Insect.Insect;
import hu.bme.iit.projlab.bmekings.Entities.Spore.SlowSpore;
import hu.bme.iit.projlab.bmekings.Entities.Spore.Spore;
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
    private static GameLogic gameLogic = new GameLogic(1000, 2);
    private static ArrayList<Entomologist> entomologistPlayers = new ArrayList<>();
    private static ArrayList<Mycologist> mycologistPlayers = new ArrayList<>();
    private static Params params = new Params();
    // tesztek inicializalasa
    private static FungalBody fungus= new FungalBody();
    private static SporeInterface spore= new SlowSpore();
    private static Map map = new Map();

     public static void main(String[] args) {
        boolean running = true;
        Scanner scanner = new Scanner(System.in);
        String input;

        gameLogic.startGame(); //ticker start 1 sec 2 player, see initialization
        initBasePlayers();
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

    private static void initBasePlayers(){
        String[] args1={"/addEntomologist", "e1"};
        String[] args2={"/addMycologist","m1"};
        addEntomologist(args1);
        addMycologist(args2);
    }

    private static void consoleActions(String input) {
        String[] splitInput = input.split(" ");

        switch (splitInput[0]) {
        case "/addMycologist":
            addMycologist(splitInput);
            break;
        case "/addEntomologist":
            addEntomologist(splitInput);
            break;
        case "/addfungus":
            addFungus(splitInput);
            break;
        case "/addinsect":
            addInsect(splitInput);
            break;
        case "/addspore":
            addSpore(splitInput);
            break;
        case "/addTecton":
            addTecton(splitInput);
            break;
        case "/setfungus":
            setFungus(splitInput);
            break;
        case "/setinsect":
            setInsect(splitInput);
            break;
        case "/setspore":
            setSpore(splitInput);
            break;
        case "/settecton":
            setTecton(splitInput);
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
        case "exit":
            System.out.println("Program leállítása...");
            break;
        default:
            System.out.println("Rossz input!");
        }
    }

    // ● [string]: az Entomológus id-je
    private static void addEntomologist(String[] splitInput) {
        entomologistPlayers.add(new Entomologist(splitInput[1]));
        System.out.println("Entomológus hozzáadva: " + splitInput[1]);
    }

    // ● [string]: az Mycologist id-je
    private static void addMycologist(String[] splitInput) {
        mycologistPlayers.add(new Mycologist(splitInput[1]));
        System.out.println("Mycologist hozzáadva: " + splitInput[1]);
    }

    // ● [string]: az Entitás id-je
    // ● [string]: az Entitás tektonja
    // ● -fb: gomba testet adunk hozzá
    //  ○ -l [szám]: a Gombatest szintje
    //  ○ -s [szám]: a kilőhető Spórák száma
    // ● -h: fonalat adunk hozzá
    //  ○ -t [tecton]: az a Tecton, amivel össze van kötve
    //  ○ -d [bool]: flag, amely jelzi, hogy ki van e nőve vagy nem
    //  ○ -dt [szám]: az az idő, ami a kinövéshez kell
    //  ○ -lt [szám]: az élettartama
    //  ○ -ct [szám]: az az idő, ami után meghal, ha elvágták
    private static void addFungus(String[] splitInput) {
       FungalBody fungus = new FungalBody(); // flageknek megfelelően be kell majd álltani

        // A base-t a mapból szedjük ki!!!!!!!!!!!!!!!!!!!!!!!!!!
    }

    // ● [string]: megadja, mely gombatest attribútumát változtatja
    // ● -fb
    // ○ -l [szám]: a gombatest szintjét változtatja meg
    // ○ -s [szám]: a kilőhető Spórák számát változtatja meg
    // ● -h
    // ○ -t [string]: megváltoztatja azt a Tectont, amivel össze van kötve
    // ○ -d [bool]: megváltoztatja a flaget, amely jelzi, hogy ki van e
    // nőve vagy nem
    // ○ -dt [szám]: megváltoztatja azt az időt, ami a kinövéshez kell
    // ○ -lt [szám]: megváltoztatja az élettartama
    // ○ -ct [szám]: megváltoztatja azt az időt, ami után meghal, ha
    // elvágták
    private static void setFungus(String[] splitInput) {
        fungus = new FungalBody(); // flagek!!!!!
    }


    // ● [string]: az Entitás id-je
    // ● -n [szám]: a Spóra tápértéke
    // ● -t [string]: a Spóra típusa, a string értéke lehet:
    // ○ normal
    // ○ slow
    // ○ hunger
    // ○ dupe
    // ○ speed
    // ○ stun
    // ○ hyphalp
    private static void addSpore(String[] splitInput) {
    }

    // ● -id [string]: megadja mely Spóra attribútumát változtatja meg
    // ● -n [szám]: megváltoztatja a Spóra tápértékét
    private static void setSpore(String[] splitInput) {
        spore = new SlowSpore(); // flageknek megfelelően be kell majd álltani
    }

    // -------------------- JÁTÉKBELI ESEMÉNYEK: ---------------------

    // ● [string]: a gombatest id-je
    // ● [string]: a tektonok id-jei
    private static void shootSpore(String[] splitInput) {
        int playerIndexInMyCologistPlayers=0; // TODO: melyik mycologistnak adjuk hozzá? Beállítottam tesztre, hogy az első elsőt vegye mind a kettő csapatból
        params.selectedTectons=null;// TODO: melyik tektonokat adjuk hozzá?
        mycologistPlayers.get(playerIndexInMyCologistPlayers).SelectAction(6, params); // TODO: melyik mycologistnak adjuk hozzá?
    }

    // ● [string]: a fonál id-je
    // Grow fungalBody
    private static void growFb(String[] splitInput) {
        int playerIndexInMyCologistPlayers=0; // TODO: melyik mycologistnak adjuk hozzá? Beállítottam tesztre, hogy az első elsőt vegye mind a kettő csapatból
        FungalBody fungus = new FungalBody(); // flageknek megfelelően be kell majd álltani
        // A base-t a mapból szedjük ki!!!!!!!!!!!!!!!!!!!!!!!!!!
        params.selectedFungus = fungus;       //TODO: Milyen tipusu gombat akarunk hozzaadni?
        mycologistPlayers.get(playerIndexInMyCologistPlayers).SelectAction(3, params); // TODO: melyik mycologistnak adjuk hozzá?
    }

    // ● [string]: a gombatest id-je
    // ● [string]: a tekton id-je
    private static void growH(String[] splitInput) {
        int playerIndexInMyCologistPlayers=0; // TODO: melyik mycologistnak adjuk hozzá? Beállítottam tesztre, hogy az első elsőt vegye mind a kettő csapatból
        Tecton tecton = new Tecton(); // flageknek megfelelően be kell majd álltani
        // A hova akarjuk azt a mapból szedjük ki!!!!!!!!!!!!!!!!!!!!!!!!!!
        params.selectedTecton = tecton;       //TODO: Milyen tipusu gombat akarunk hozzaadni?
        mycologistPlayers.get(playerIndexInMyCologistPlayers).SelectAction(4,params); // TODO: melyik mycologistnak adjuk hozzá?
    }
    
    // ● [string]: a fonál id-je
    private static void speedUpDev(String[] splitInput) {
        int playerIndexInMyCologistPlayers=0; // TODO: melyik mycologistnak adjuk hozzá? Beállítottam tesztre, hogy az első elsőt vegye mind a kettő csapatból
        Hyphal hyphal = new Hyphal(); // flageknek megfelelően be kell majd álltani
        params.selectedHyphal = hyphal;       //TODO: Milyen tipusu gombat akarunk hozzaadni?
        mycologistPlayers.get(playerIndexInMyCologistPlayers).SelectAction(7, params); // TODO: melyik mycologistnak adjuk hozzá?
    }

    // ● [string]: a gombatest id-je
    private static void levelUp(String[] splitInput) {
    
    }
        
    // ● [string]: a rovar id-je
    // ● [string]: a tekton id-je
    private static void move(String[] splitInput) {
            
    }
    
    // ● [string]: a rovar id-je
    // ● [string]: a spóra id-je
    private static void eatSp(String[] splitInput) {
            
    }
    
    // ● [string]: a fonal id-je
    // ● [string]: a rovar id-je
    private static void eatInsect(String[] splitInput) {
            
    }
    
    // ● [string]: a rovar id-je
    // ● [string]: a fonal id-je
    private static void cutHyphal(String[] splitInput) {
            
    }
    
    // TODO
    private static void splitTecton(String[] splitInput) {
            
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
    // ● -bl [string]: megváltoztatja az Entitás tektonját
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
    // /addTecton T-01 normal -sc 10
    private static void addTecton(String[] splitInput) {
        if (splitInput.length < 3) {
            return;
        }
        String id = splitInput[1];
        double sc = 0;
        double hdt = 6;
        if (splitInput.length > 4) {
            sc = Integer.parseInt(splitInput[4]);
        }
        switch (splitInput[2]) {
            case "normal":
                Tecton nTecton = new Tecton(id, sc, false, false);
                gameLogic.map.addTecton(nTecton);
                System.out.println("Új objektum Tekton [" + id + "] létrejött");
                break;
            case "toxic":
                ToxicTecton tTecton = new ToxicTecton(hdt, id, sc, false, false);
                gameLogic.map.addTecton(tTecton);
                System.out.println("Új objektum ToxicTekton [" + id + "] létrejött");
                break;
            case "weak":
                WeakTecton wTecton = new WeakTecton(false, id, sc, false, false);
                gameLogic.map.addTecton(wTecton);
                System.out.println("Új objektum WeakTekton [" + id + "] létrejött");
                break;
            case "nofungus":
                NoFungusTecton nfTecton = new NoFungusTecton(id, sc, false, false);
                gameLogic.map.addTecton(nfTecton);
                System.out.println("Új objektum NoFungusTekton [" + id + "] létrejött");
                break;
            case "hyphalpres":
                HyphalPreserverTecton hpTecton = new HyphalPreserverTecton(id, sc, false, false);
                gameLogic.map.addTecton(hpTecton);
                System.out.println("Új objektum HyphalPreserverTekton [" + id + "] létrejött");
                break;
        }
    }

    // ● [string]: megadja mely Tecton attribútumát változtatja meg
    // ● -sc [double]: megváltoztatja a kettétörés esélyét
    // [0]/setTecton [1]T-01 [2]-sc [3]100
    private static void setTecton(String[] splitInput) {
        double oldSc = -1;
        for (Tecton t : gameLogic.map.getAllTectons()) {
            if (t.getId().equals(splitInput[1]))
                oldSc = t.getSplitChance();
        }

        if (oldSc == -1) {
            System.out.println("Nincs ilyen objektum!");
            return;
        }

        if(splitInput.length > 3) {
            System.out.println(splitInput[1] + " [splitChance] megváltozott:");
            System.out.println(oldSc + " -> " + splitInput[3]);
        }
        else {
            System.out.println("Nincs elég paraméter megadva!");
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
                        }
                        break;
                    case "s":
                        for (Tecton t : gameLogic.map.getAllTectons()) {
                            for (SporeInterface sp : t.getSpores()) {
                                // TODO
                                // queue-n kéne végigmenni de az a baj, hogy mivel ezek Spore Interface-k
                                // mindig kasztolni kéne, de nem tudjuk milyen típusú spórára
                                System.out.println("ID: " );
                                System.out.println("BaseLocation: ");
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
                        for (FungalBody fb : mycologistPlayers.get(0).getControlledFunguses()) {
                            System.out.println("ID: " + fb.getId());
                            System.out.println("BaseLocation: " + fb.getBase().getId());
                            System.out.println("CurrentLevel: " + fb.getCurrLvl());
                            System.out.println("ShotSporesNum: " + fb.getShotSporesNum());
                            System.out.println("ShootingRange: " + fb.getCharacteristics().getShootingRange());
                            System.out.println("SporeCapacity: " + fb.getCharacteristics().getSporeCapacity());
                            System.out.println("SporeProductionIntensity: " + fb.getCharacteristics().getSporeProductionIntensity());
                            System.out.println("StartingHyphalNum: " + fb.getCharacteristics().getStartingHyphalNum());
                        }
                        break;
                    case "h":
                        for (Hyphal h : mycologistPlayers.get(0).getHyphalList()) {
                            System.out.println("ID: " + h.getId());
                            System.out.println("BaseLocation: " + h.getBase().getId());
                            System.out.println("ConnectedTecton: " + h.getConnectedTecton().getId());
                            String msg = h.getDeveloped() ? "true" : "false";
                            System.out.println("Developed: " + msg);
                            System.out.println("DevelopTime: " + h.getDevelopTime());
                            System.out.println("LifeTime: " + h.getLifeTime());
                            System.out.println("CutTime: " + h.getCutTime());
                        }
                        break;
                    default:
                        System.out.println("Nincs ilyen paraméter!");
                        break;
                }   
            }
            else {
                System.out.println("Nincs ilyen paraméter!");
            }
        }
        else {
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
                            // TODO
                            // queue-n kéne végigmenni de az a baj, hogy mivel ezek Spore Interface-k
                            // mindig kasztolni kéne, de nem tudjuk milyen típusú spórára
                            System.out.println("ID: " );
                            System.out.println("BaseLocation: ");
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
                    for (FungalBody fb : mycologistPlayers.get(0).getControlledFunguses()) {
                        System.out.println("ID: " + fb.getId());
                        System.out.println("BaseLocation: " + fb.getBase().getId());
                    }
                    break;
                case "h":
                    for (Hyphal h : mycologistPlayers.get(0).getHyphalList()) {
                        System.out.println("ID: " + h.getId());
                        System.out.println("BaseLocation: " + h.getBase().getId());
                    }
                    break;
                default:
                    System.out.println("Nincs ilyen paraméter!");
                    break;
            }
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