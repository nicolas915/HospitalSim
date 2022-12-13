package People;

import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Patient implements IPatient, Runnable {

	private Random rand = new Random();
	public static final int HEALED = 0;
	public static final int LIGHTILL = 1;
	public static final int MEDIUMILL = 2;
	public static final int HIGHILL = 3;
	public static final int DEAD = 4;
	
	private String name;
	private String waitedOrgan;
	private int vitalSign;
	private Map<String, String> contactList;

	private boolean exit = false;

	public Patient(String name, String waitedOrgan, int vitalSign) {
		this.name = name;
		this.waitedOrgan = waitedOrgan;
		this.vitalSign = vitalSign;
	}

	public String getName() {
		return name;
	}

	public int getVitalSign() {
		return vitalSign;
	}

	public String getWaitedOrgan() {
		return waitedOrgan;
	}

	public Map<String, String> getContactList() {
		return contactList;
	}

	public void setVitalSign(int vitalSign) {
		this.vitalSign = vitalSign;
	}

	public void addContact(String contact, String relation) {
		this.contactList.put(relation, contact);
	}

	public void lifeCyclePatient() {
		if (this.vitalSign == HIGHILL) {
			if (rand.nextInt(10) > 7) {
				this.setVitalSign(DEAD);
			}
		}
		if (this.vitalSign == MEDIUMILL) {
			if (rand.nextInt(10) > 7) {
				this.setVitalSign(HIGHILL);
			}
		}
		if (this.vitalSign == LIGHTILL) {
			if (rand.nextInt(10) > 7) {
				this.setVitalSign(MEDIUMILL);
			}
		}
	}

	@Override
	public void run() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				lifeCyclePatient();
				//System.out.println(getName() + "\nEtat de santé : " + getVitalSign() + "\n\n");
				if (getVitalSign() == Patient.DEAD) {
					timer.cancel();
				}
			}
		}, 0, 2000);
	}

	@Override
	public String toString() {
		return "Patient [\tname=" + String.format("%12s", name) + "\twaitedOrgan=" + waitedOrgan + "]";
	}
	
	
}
