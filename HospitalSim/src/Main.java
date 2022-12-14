import Organ.OrganDispatcher;
import Organ.OrganGenerator;
import People.Patient;
import People.PatientGenerator;

public class Main {
	public static void main(String[] args) {
		System.out.println("Hello world!");
		
		OrganDispatcher od = OrganDispatcher.getInstance();
		OrganGenerator og = new OrganGenerator(od);
		PatientGenerator pg = new PatientGenerator(od);
		
		pg.createPatient("henry");
		pg.createPatient("henry1");
		pg.createPatient("henry2");
		pg.createPatient("henry3");
		pg.createPatient("henry4");
		
		og.createOrgan();
		og.createOrgan();
		og.createOrgan();
		og.createOrgan();
	}
}