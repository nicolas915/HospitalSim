import java.util.ArrayList;
import java.util.Random;

import Organ.Organ;
import Organ.OrganDispatcher;
import Organ.OrganGenerator;
import People.Patient;
import People.PatientGenerator;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");
        Random rand = new Random();
        
        PatientGenerator pg = new PatientGenerator();
        OrganGenerator og = new OrganGenerator();
        
        OrganDispatcher od = new OrganDispatcher();
        
        ArrayList<Patient> listPatient = new ArrayList<>();
        ArrayList<Organ> listOrgan = new ArrayList<>();
        
        while(true) {
        	if(listPatient.size() < 5) {
        		Patient buffer = (Patient)pg.createPatient("euuuuh" + rand.nextInt(100));
        		listPatient.add(buffer);
        		buffer.run();
        	}
        	for(int i = 0; i < listPatient.size(); i++) {
        		if(listPatient.get(i).getVitalSign() == Patient.DEAD) {
        			listPatient.remove(i--);
        			
        			Organ buffer = (Organ) og.createOrgan();
        			listOrgan.add(buffer);
        			buffer.run();
        		}
        	}
        	
        	
        	
        	for(int i = 0; i < listOrgan.size(); i++) {
        		if(listOrgan.get(i).getState() == Organ.ROTTEN) {
        			listOrgan.remove(i--);
        		}
        	}
        }
    }
}