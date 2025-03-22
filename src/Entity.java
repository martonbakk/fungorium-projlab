
public abstract class Entity implements Listener {
    protected String id;
    protected Tecton baseLocation;

    public Entity(String id, Tecton location) { } 

    public String getId() { return new String(""); }

    public Tecton getBaseLocation() { return new Tecton(); }
}