package DeathHandler;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import Organ.IOrgan;
import Organ.ListOfOrgans;
import Organ.Organ;
import Organ.OrganGenerator;

public class OrgansLifeCycle implements Runnable {

	private ListOfOrgans listOfOrgans;
	private boolean keepGoing = true;

	public OrgansLifeCycle() {
		listOfOrgans = ListOfOrgans.getInstance();
	}

	public void stop() {
		keepGoing = false;
	}

	public void createOrgan() {
		OrganGenerator og = new OrganGenerator();
		Random rand = new Random();

		try {
			if (listOfOrgans.getSemaphore().tryAcquire(2, TimeUnit.MINUTES)) {
				Organ buffer = (Organ) og.createOrgan();
				listOfOrgans.addOrgan(buffer);
				listOfOrgans.getSemaphore().release();
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
				if (listOfOrgans.getSemaphore().tryAcquire(2, TimeUnit.MINUTES)) {
					for (int i = 0; i < listOfOrgans.getSize(); i++) {
						IOrgan buffer = listOfOrgans.getOrgan(i);
						if (buffer.getState() == Organ.ROTTEN) {
							listOfOrgans.removeOrgan(buffer);
						}
					}
					listOfOrgans.getSemaphore().release();
					Thread.sleep(50);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
