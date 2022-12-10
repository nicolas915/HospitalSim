package People;


import java.util.Random;

public class PatientGenerator {
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

    public IPatient createPatient(String name){
        IPatient p = new Patient(name, failingOrgan[rand.nextInt(failingOrgan.length)], rand.nextInt(1,4));
        return p;
    }


}
