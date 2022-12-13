package Organ;

import java.util.ArrayList;

import People.IPatient;


public class OrganDispatcher{
	private static OrganDispatcher instance;
	
	private ArrayList<IPatient> listOfPeople;
	private ArrayList<IOrgan> listOfOrgans;

    
	private OrganDispatcher() {
    	listOfPeople = new ArrayList<>();
    	listOfOrgans = new ArrayList<>();
	}
	
	public static OrganDispatcher getInstance() {
		if(instance == null) {
			instance = new OrganDispatcher();
		}
		return instance;
	}

    public void newPatient(IPatient patient) {
    	listOfPeople.add(patient);
    	refresh();
    }
    
    public void removePatient(IPatient patient) {
    	listOfPeople.remove(patient);
    	refresh();
    }
    
    public void newOrgan(IOrgan organ) {
		listOfOrgans.add(organ);
		refresh();
	}
    
    public void removeOrgan(IOrgan organ) {
    	listOfOrgans.remove(organ);
    	refresh();
    }
    
    public void refresh() {
    	for(int i = 0; i < listOfOrgans.size(); i++) {
    		System.out.println(listOfOrgans.get(i));
    	}
    	
    	for(int i = 0; i < listOfPeople.size(); i++) {
    		System.out.println(listOfPeople.get(i));
    	}
    	
    	System.out.println("\n\n\n");
    }
	
}
