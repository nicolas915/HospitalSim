package People;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

import Organ.OrganDispatcher;

public class ListOfPeople {
	private static ListOfPeople instance;
	
	private ArrayList<IPatient> allPeople;
	
	private OrganDispatcher master;
	
	private Semaphore semaphore;
	
	private ListOfPeople() {
		semaphore = new Semaphore(1);
		allPeople = new ArrayList<>();
		master = OrganDispatcher.getInstance();
	}
	
	public static ListOfPeople getInstance() {
		if(instance == null) {
			instance = new ListOfPeople();
		}
		return instance;
	}
	
	public void addPatient(IPatient patient) {
		allPeople.add(patient);
		master.newPatient(patient);
	}
	
	public void removePatient(IPatient patient) {
		allPeople.remove(patient);
		master.removePatient(patient);
	}
	
	public int getSize() {
		return allPeople.size();
	}
	
	public IPatient getPatient(int i) {
		return allPeople.get(i);
	}
	
	public Semaphore getSemaphore() {
		return semaphore;
	}
}
