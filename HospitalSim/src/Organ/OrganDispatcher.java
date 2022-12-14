package Organ;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Ref;
import java.util.ArrayList;

import People.IPatient;
import People.ListOfPeople;


public class OrganDispatcher implements PropertyChangeListener {
	private static OrganDispatcher instance;
	
	private ListOfOrgans listOfOrgans;
	private	ListOfPeople listOfPeople;
    
	private OrganDispatcher() {
    	listOfPeople = ListOfPeople.getInstance();
    	listOfOrgans = ListOfOrgans.getInstance();
	}
	
	public static OrganDispatcher getInstance() {
		if(instance == null) {
			instance = new OrganDispatcher();
		}
		return instance;
	}
    
    public void refresh() {
    	for(int i = 0; i < listOfOrgans.getSize(); i++) {
    		System.out.println(listOfOrgans.getOrgan(i));
    	}
    	System.out.println("\n");
    	for(int i = 0; i < listOfPeople.getSize(); i++) {
    		System.out.println(listOfPeople.getPatient(i));
    	}
    	
    	System.out.println("\n\n\n");
    }

	public void propertyChange(PropertyChangeEvent evt) {
		listOfOrgans.addOrgan((IOrgan) evt.getNewValue());
	}
    
    public void dispatch() {
    	
    }

	public void update() {
		refresh();
		dispatch();
	}
	
}
