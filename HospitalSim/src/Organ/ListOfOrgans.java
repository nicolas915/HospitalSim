package Organ;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class ListOfOrgans {
	private static ListOfOrgans instance;
	
	private OrganDispatcher master;

	private ArrayList<IOrgan> allOrgans;

	private Semaphore semaphore;

	private ListOfOrgans() {
		semaphore = new Semaphore(1);
		allOrgans = new ArrayList<>();
		master = OrganDispatcher.getInstance();
	}
	
	public static ListOfOrgans getInstance() {
		if (instance == null) {
			instance = new ListOfOrgans();
		}
		return instance;
	}
	
	public void addOrgan(IOrgan organ) {
		allOrgans.add(organ);
		master.newOrgan(organ);
	}
	
	public void removeOrgan(IOrgan organ) {
		allOrgans.remove(organ);
		master.removeOrgan(organ);
	}
	
	public int getSize() {
		return allOrgans.size();
	}
	
	public IOrgan getOrgan(int i) {
		return allOrgans.get(i);
	}
	
	public Semaphore getSemaphore() {
		return semaphore;
	}
	
}
