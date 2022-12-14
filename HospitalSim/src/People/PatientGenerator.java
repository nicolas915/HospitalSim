package People;


import Organ.OrganDispatcher;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Random;

public class PatientGenerator {
    private OrganDispatcher organDispatcher;
    private static final String[] failingOrgan = new String[]{"liver", "liver", "liver", "liver", "liver",
            "liver", "liver", "liver", "liver", "liver", "liver", "liver", "heart", "heart", "heart", "heart",
            "pancreas", "pancreas", "pancreas", "pancreas", "kidney", "kidney", "kidney", "kidney", "kidney",
            "kidney", "kidney", "kidney", "kidney", "kidney", "kidney", "kidney", "kidney", "kidney", "kidney",
            "kidney", "kidney", "kidney", "kidney", "kidney", "kidney", "kidney", "kidney", "kidney", "kidney",
            "kidney", "kidney", "kidney", "kidney", "kidney", "kidney", "kidney", "kidney", "kidney", "kidney",
            "kidney", "kidney", "kidney", "kidney", "kidney", "kidney", "kidney", "kidney", "kidney", "kidney",
            "kidney", "kidney", "kidney", "kidney", "kidney", "kidney", "kidney", "kidney", "kidney", "kidney",
            "kidney", "kidney", "kidney", "kidney", "kidney", "kidney", "kidney", "kidney", "kidney", "kidney",
            "kidney", "kidney", "kidney", "kidney", "kidney", "kidney", "kidney", "kidney", "kidney", "kidney",
            "lung", "lung", "lung", "lung", "lung"};

    private Random rand = new Random();

    public PatientGenerator(OrganDispatcher organDispatcher){
        this.organDispatcher = organDispatcher;
    }

    public IPatient createPatient(String name){
        IPatient p = new Patient(name, failingOrgan[rand.nextInt(failingOrgan.length)], rand.nextInt(1,4), organDispatcher);
        return p;
    }


}
