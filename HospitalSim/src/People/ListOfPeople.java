package People;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class ListOfPeople {
	private static ListOfPeople instance;

	private ArrayList<IPatient> allPeople;

	private Semaphore semaphore;

	private ListOfPeople() {
		semaphore = new Semaphore(1);
		allPeople = new ArrayList<>();
	}

	public static synchronized ListOfPeople getInstance() {
		if (instance == null) {
			instance = new ListOfPeople();
		}
		return instance;
	}

	public void addPatient(IPatient patient) {
		allPeople.add(patient);
	}

	public void removePatient(IPatient patient) {
		allPeople.remove(patient);
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
