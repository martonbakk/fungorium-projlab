<<<<<<< HEAD
abstract public class Entity{

=======

public abstract class Entity implements Listener {
    protected String id;
    protected Tecton baseLocation;

    public Entity(String id, Tecton location) { } 

    public String getId() { return new String(""); }

    public Tecton getBaseLocation() { return new Tecton(); }
>>>>>>> bd6aab10b476eadc4e0c50d5a12195c56de566bb
}