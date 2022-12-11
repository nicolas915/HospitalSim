package Organ;

import java.util.ArrayList;

import People.Patient;

public class OrganDispatcher {
    
    public OrganDispatcher() {
	}
    
    public int DispatchOrgan(Organ organ, ArrayList<Patient> listOfPatient) {
    	for(int i = 0; i < listOfPatient.size(); i++) {
    		Patient buffer = listOfPatient.get(i);
    		if(buffer.getWaitedOrgan().equals(organ.getName())) {
    			return i;
    		}
    	}
		return -1;
    }
}
