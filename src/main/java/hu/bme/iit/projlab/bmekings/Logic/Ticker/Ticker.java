package hu.bme.iit.projlab.bmekings.Logic.Ticker;

import java.util.ArrayList;
import java.util.List;

import hu.bme.iit.projlab.bmekings.Interface.Listener.Listener;

public class Ticker {
    private final List<Listener> gameObList=new ArrayList<>();
    private final long intervalMillis;
    private Thread tickerThread = new Thread();
    private volatile boolean running;

    public Ticker(long intervalMillis) {
        this.intervalMillis = intervalMillis;
        this.running = false;
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
                    }
                } catch (InterruptedException e) {
                    running = false;
                    break;
                }
            }
        });
        tickerThread.start();
    }

    public void stop() {
        running = false;
        if (tickerThread != null) {
            tickerThread.interrupt();
        }
    }
}