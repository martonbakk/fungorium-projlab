package hu.bme.iit.projlab.bmekings.Program;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

import hu.bme.iit.projlab.bmekings.Logic.GameLogic.GameLogic;
import hu.bme.iit.projlab.bmekings.Player.Entomologist.Entomologist;
import hu.bme.iit.projlab.bmekings.Player.Mycologist.Mycologist;
public class Program {
    private static GameLogic gameLogic = new GameLogic(1000, 2);
    private static ArrayList<Entomologist> entomologistPlayers = new ArrayList<>();
    private static ArrayList<Mycologist> mycologistPlayers = new ArrayList<>();
     public static void main(String[] args) {
        boolean running = true;
        Scanner scanner = new Scanner(System.in);
        String input;

        gameLogic.startGame(); //ticker start 1 sec see initialization
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
    }


    // ● [string]: az Mycologist id-je
    private static void addMycologist(String[] splitInput) {
        mycologistPlayers.add(new Mycologist(splitInput[1]));
    }

    // ● [string]: az Entitás id-je
    // ● [string]: az Entitás tektonja
    // ● -fb: gomba testet adunk hozzá
    // ○ -l [szám]: a Gombatest szintje
    // ○ -s [szám]: a kilőhető Spórák száma
    // ● -h: fonalat adunk hozzá
    // ○ -t [tecton]: az a Tecton, amivel össze van kötve
    // ○ -d [bool]: flag, amely jelzi, hogy ki van e nőve vagy nem
    // ○ -dt [szám]: az az idő, ami a kinövéshez kell
    // ○ -lt [szám]: az élettartama
    // ○ -ct [szám]: az az idő, ami után meghal, ha elvágták
    private static void addFungus(String[] splitInput) {
       
    }
    
    // ● [string]: az Entitás id-je
    // ● [string]: az Entitás tektonja
    // ● -ms [szám]: a Rovar mozgási sebessége
    // ● -mcd [szám]: a Rovar mozgásának cooldown-ja
    // ● -sl [szám]: a Rovar telítettségének maximális értéke
    // ● -ccd [szám]: a fonalvágás cooldown-ja
    private static void addInsect(String[] splitInput) {
        
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
        
    }

    // ● [string]: megadja mely Rovar attribútumát változtatja meg
    // ● -ms [szám]: megváltoztatja a Rovar mozgási sebességét
    // ● -mcd [szám]: megváltoztatja a Rovar mozgásának cooldown-ját
    // ● -sl [szám]: megváltoztatja a Rovar telítettségének maximális értékét
    // ● -ccd [szám]: megváltoztatja a fonalvágás cooldown-ját
    // ● -bl [string]: megváltoztatja az Entitás tektonját
    private static void setInsect(String[] splitInput) {
        
    }

    // ● -id [string]: megadja mely Spóra attribútumát változtatja meg
    // ● -n [szám]: megváltoztatja a Spóra tápértékét
    private static void setSpore(String[] splitInput) {
        
    }

    // ● [string]: megadja mely Tecton attribútumát változtatja meg
    // ● -sc [double]: megváltoztatja a kettétörés esélyét
    private static void setTecton(String[] splitInput) {
        
    }

    // Az összes parancs listázása
    private static void help() {
        
    }

    // ● [string]: a kimenetet tároló fájl neve
    private static void save(String[] splitInput) {
        
    }

    // ● [string]: a bemenetet tároló fájl neve
    private static void load(String[] splitInput) {
        
    }

    // ● [string]: a példányok típusa, értéke lehet:
    // ○ e: entitások
    // ○ t: tektonok
    // ○ s: spórák
    // ○ i: rovarok
    // ○ fb: gombatestek
    // ○ h: fonalak
    // ● -d: attribútumokkal együtt listázza ki a példányokat
    private static void list(String[] splitInput) {
        
    }

    // ● [string]: a példány id-je
    private static void checkout(String[] splitInput) {
        
    }

    // ● [string]: a gombatest id-je
    // ● [string]: a tektonok id-jei
    private static void shootSpore(String[] splitInput) {
        
    }

    // ● [string]: a gombatest id-je
    private static void levelUp(String[] splitInput) {
        
    }

    // ● [string]: a fonál id-je
    private static void growFb(String[] splitInput) {
        
    }

    // ● [string]: a gombatest id-je
    // ● [string]: a tekton id-je
    private static void growH(String[] splitInput) {
        
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

    // ● [string]: a fonál id-je
    private static void speedUpDev(String[] splitInput) {
        
    }

    // ● [string]: a rovar id-je
    // ● [string]: a fonal id-je
    private static void cutHyphal(String[] splitInput) {
        
    }

    // TODO
    private static void splitTecton(String[] splitInput) {
        
    }
}