import DeathHandler.OrgansLifeCycle;
import DeathHandler.PatientsLifeCycle;

public class Main {
	public static void main(String[] args) {

		System.out.println("Hello world!");

		PatientsLifeCycle patientsLifeCycle = new PatientsLifeCycle();
		OrgansLifeCycle organsLifeCycle = new OrgansLifeCycle();


		Thread t1;
        t1 = new Thread(new PatientsLifeCycle());
        t1.start();
        
        Thread t2;
        t2 = new Thread(new OrgansLifeCycle());
        t2.start();

        for(int i=0; i < 10; i++) {
        	System.out.println("created a patient");
        	patientsLifeCycle.createPatient();
        }
        	
	}
}