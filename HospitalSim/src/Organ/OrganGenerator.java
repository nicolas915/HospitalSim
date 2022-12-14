package Organ;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Random;
public class OrganGenerator{
    private PropertyChangeSupport support;

    private static final String[] organ = new String[]{"liver", "heart", "pancreas", "kidney", "lung"};

    private Random rand = new Random();

    public OrganGenerator(){
        support = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl){
        support.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl){
        support.removePropertyChangeListener(pcl);
    }

    public IOrgan createOrgan(){
        IOrgan o = new Organ(organ[rand.nextInt(organ.length)], rand.nextInt(0,3));
        support.firePropertyChange("organs", null, o);
        return o;
    }
}
