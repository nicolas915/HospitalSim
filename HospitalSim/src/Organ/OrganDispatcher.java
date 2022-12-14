package Organ;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Ref;
import java.util.ArrayList;

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

	public void refresh() {
		for (int i = 0; i < listOfOrgans.getSize(); i++) {
			System.out.println(listOfOrgans.getOrgan(i));
		}
		System.out.println("\n");
		for (int i = 0; i < listOfPeople.getSize(); i++) {
			System.out.println(listOfPeople.getPatient(i));
		}

		System.out.println("\n\n\n");
	}

	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().substring(0, 3) == "add") {
			if (evt.getPropertyName().substring(3) == "o") {
				listOfOrgans.addOrgan((IOrgan) evt.getNewValue());
			}
			if (evt.getPropertyName().substring(3) == "p") {
				listOfPeople.addPatient((IPatient) evt.getNewValue());
			}
		} else if (evt.getPropertyName().substring(0, 6) == "remove") {
			if (evt.getPropertyName().substring(6) == "o") {
				listOfOrgans.removeOrgan((IOrgan) evt.getNewValue());
			}
			if (evt.getPropertyName().substring(6) == "p") {
				listOfPeople.removePatient((IPatient) evt.getNewValue());
			}
		}
	}

	public void dispatch() {
		for (IPatient patient : listOfPeople.getAllPeople()) {
			for (IOrgan organ : listOfOrgans.getAllOrgans()) {
				if (patient.getWaitedOrgan().equals(organ.getName())) {
					patient.setVitalSign(Patient.HEALED);
					listOfOrgans.removeOrgan(organ);
					listOfPeople.removePatient(patient);
				}
			}
		}
	}

	public void update() {
		refresh();
		dispatch();
	}

}
