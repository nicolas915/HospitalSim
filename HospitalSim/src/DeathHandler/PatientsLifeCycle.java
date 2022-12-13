package DeathHandler;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import People.IPatient;
import People.ListOfPeople;
import People.Patient;
import People.PatientGenerator;

public class PatientsLifeCycle implements Runnable {

	private ListOfPeople listOfPeople;
	private OrgansLifeCycle organsLifeCycle;

	private boolean keepGoing = true;

	public PatientsLifeCycle() {
		listOfPeople = ListOfPeople.getInstance();
		organsLifeCycle = new OrgansLifeCycle();
	}

	public void stop() {
		keepGoing = false;
	}

	public void createPatient() {
		PatientGenerator pg = new PatientGenerator();
		Random rand = new Random();
		
		try {
			if (listOfPeople.getSemaphore().tryAcquire(2, TimeUnit.MINUTES)) {
				Patient buffer = (Patient) pg.createPatient("Patient n°" + rand.nextInt(10000000));
				listOfPeople.addPatient(buffer);
				listOfPeople.getSemaphore().release();
				buffer.run();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void run() {
		while (keepGoing) {
			try {
				if (listOfPeople.getSemaphore().tryAcquire(2, TimeUnit.MINUTES)) {
					for (int i = 0; i < listOfPeople.getSize(); i++) {
						IPatient buffer = listOfPeople.getPatient(i);
						if (buffer.getVitalSign() == Patient.DEAD) {
							listOfPeople.removePatient(buffer);
							organsLifeCycle.createOrgan();
						}
					}
					listOfPeople.getSemaphore().release();
					Thread.sleep(50);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}