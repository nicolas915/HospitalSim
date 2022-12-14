package Organ;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Ref;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import People.IPatient;
import People.ListOfPeople;
import People.Patient;

public class OrganDispatcher implements PropertyChangeListener {
	private static OrganDispatcher instance;

	private ListOfOrgans listOfOrgans;
	private ListOfPeople listOfPeople;

	private OrganDispatcher() {
		listOfPeople = ListOfPeople.getInstance();
		listOfOrgans = ListOfOrgans.getInstance();
	}

	public static OrganDispatcher getInstance() {
		if (instance == null) {
			instance = new OrganDispatcher();
		}
		return instance;
	}

	public void refresh() throws InterruptedException {
		if (listOfOrgans.getSemaphore().tryAcquire(2, TimeUnit.MINUTES)) {
			for (int i = 0; i < listOfOrgans.getSize(); i++) {
				System.out.println(listOfOrgans.getOrgan(i));
			}
			listOfOrgans.getSemaphore().release();
		}
		System.out.println("\n");
		if (listOfPeople.getSemaphore().tryAcquire(2, TimeUnit.MINUTES)) {
			for (int i = 0; i < listOfPeople.getSize(); i++) {
				System.out.println(listOfPeople.getPatient(i));
			}
			listOfPeople.getSemaphore().release();
		}

		System.out.println("\n\n\n");
	}

	public void propertyChange(PropertyChangeEvent evt) {
		try {
			if (evt.getPropertyName().substring(0, 3).equals("add")) {
				if (evt.getPropertyName().substring(3).equals("o")) {

					if (listOfOrgans.getSemaphore().tryAcquire(2, TimeUnit.MINUTES)) {
						listOfOrgans.addOrgan((IOrgan) evt.getNewValue());
						listOfOrgans.getSemaphore().release();
					}
				}
				if (evt.getPropertyName().substring(3).equals("p")) {
					if (listOfPeople.getSemaphore().tryAcquire(2, TimeUnit.MINUTES)) {
						listOfPeople.addPatient((IPatient) evt.getNewValue());
						listOfPeople.getSemaphore().release();
					}
				}
			} else if (evt.getPropertyName().substring(0, 6).equals("remove")) {
				if (evt.getPropertyName().substring(6).equals("o")) {
					if (listOfOrgans.getSemaphore().tryAcquire(2, TimeUnit.MINUTES)) {
						listOfOrgans.removeOrgan((IOrgan) evt.getNewValue());
						listOfOrgans.getSemaphore().release();
					}
				}
				if (evt.getPropertyName().substring(6).equals("p")) {
					if (listOfPeople.getSemaphore().tryAcquire(2, TimeUnit.MINUTES)) {
						listOfPeople.removePatient((IPatient) evt.getNewValue());
						listOfPeople.getSemaphore().release();
					}
				}
			}
			update();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void dispatch() throws InterruptedException {
		if (listOfPeople.getSemaphore().tryAcquire(2, TimeUnit.MINUTES)
				&& listOfOrgans.getSemaphore().tryAcquire(2, TimeUnit.MINUTES)) {
			for (int i = 0; i < listOfPeople.getSize(); i++) {
				IPatient patientBuffer = listOfPeople.getPatient(i);
				for (int j = 0; j < listOfOrgans.getSize(); j++) {
					IOrgan organBuffer = listOfOrgans.getOrgan(j);
					if (patientBuffer.getWaitedOrgan().equals(organBuffer.getName())) {
						System.out.println(organBuffer.toString() + "saved : " + patientBuffer.toString());
						listOfOrgans.removeOrgan(organBuffer);
						listOfPeople.removePatient(patientBuffer);
					}
				}
			}
			listOfOrgans.getSemaphore().release();
			listOfPeople.getSemaphore().release();
		}
	}

	public void update() throws InterruptedException {
		refresh();
		dispatch();
	}

}
