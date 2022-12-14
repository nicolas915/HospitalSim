package Organ;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Random;
public class OrganGenerator{

    private static final String[] organ = new String[]{"liver", "heart", "pancreas", "kidney", "lung"};
    private OrganDispatcher organDispatcher;

    private Random rand = new Random();

    public OrganGenerator(OrganDispatcher organDispatcher){
        this.organDispatcher = organDispatcher;
    }

    public IOrgan createOrgan(){
        IOrgan o = new Organ(organ[rand.nextInt(organ.length)], rand.nextInt(0,3), organDispatcher);
        return o;
    }
}
