package Organ;

import java.util.Random;
public class OrganGenerator{

    private static final String[] organ = new String[]{"liver", "heart", "pancreas", "kidney", "lung"};

    private Random rand = new Random();

    public IOrgan createPatient(){
        IOrgan o = new Organ(organ[rand.nextInt(organ.length)], rand.nextInt(0,3));
        return o;
    }
}
