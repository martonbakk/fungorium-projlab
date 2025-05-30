package hu.bme.iit.projlab.bmekings.Logic.IDGenerator;

import java.util.HashMap;

public class IDGenerator {
    private static HashMap<String, Integer> idCounters = new HashMap<>();

    public static String generateID(String type){
        Integer counter = idCounters.computeIfAbsent(type, x -> new Integer(1));
        idCounters.put(type, idCounters.get(type) + 1);
        return String.format("%s-%02d", type, counter);
    }
    
    public static void reset() {
        idCounters.clear();
        idCounters = new HashMap<>();
    }

    public static void resetIDGenerator() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
