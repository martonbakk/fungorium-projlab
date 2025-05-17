package hu.bme.iit.projlab.bmekings.Logic.Ticker;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import hu.bme.iit.projlab.bmekings.Interface.Listener.Listener;

public class Ticker implements Serializable {
    private static final long serialVersionUID = 1L;


    private transient final List<Listener> gameObList=new ArrayList<>();
    private final long intervalMillis;
    private transient Thread tickerThread = new Thread();
    private volatile boolean running;
    private long elapsedTicks;

    public Ticker(long intervalMillis) {
        this.intervalMillis = intervalMillis;
        this.running = false;
        this.elapsedTicks = 0;
    }

    public void addListener(Listener listener) {
        synchronized (gameObList) {
            gameObList.add(listener);
        }
    }

    public void removeListener(Listener listener) {
        synchronized (gameObList) {
            gameObList.remove(listener);
        }
    }

    public void start() {
        if (running) return;
        running = true;
        tickerThread = new Thread(() -> {
            while (running) {
                try {
                    Thread.sleep(intervalMillis);
                    synchronized (gameObList) {
                        for (Listener listener : gameObList) {
                            listener.update();
                        }
                        elapsedTicks++;
                    }
                } catch (InterruptedException e) {
                    running = false;
                    break;
                }
            }
        });
        tickerThread.start();
        System.out.println("Started Ticker!");
    }

    public void stop() {
        running = false;
        if (tickerThread != null) {
            tickerThread.interrupt();
            System.out.println("Stopped Ticker!");
        }
    }

    public void incrementElapsedTicks() {
        elapsedTicks++;
    }

    public long getElapsedTicks() {
        return elapsedTicks;
    }

    public final long getIntervalMillis() {
        return intervalMillis;
    }

    public final List<Listener> getGameObList(){
        return gameObList;
    }
    

    // Egyéni szerializáció
    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject(); // Alapértelmezett szerializáció (gameObList, intervalMillis, elapsedTicks)
    }

    // Egyéni deszerializáció
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject(); // Alapértelmezett deszerializáció
        this.tickerThread = null; // Thread inicializálása
        this.running = false; // Alapértelmezett állapot
    }

}