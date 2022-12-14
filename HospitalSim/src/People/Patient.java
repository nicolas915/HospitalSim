package People;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import Organ.OrganDispatcher;

public class Patient implements IPatient, Runnable {
	private static OrganDispatcher organDispatcher = OrganDispatcher.getInstance();
	private PropertyChangeSupport support;
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

	public Patient(String name, String waitedOrgan, int vitalSign, PropertyChangeListener pcl) {
		support = new PropertyChangeSupport(this);
		support.addPropertyChangeListener(pcl);
		this.name = name;
		this.waitedOrgan = waitedOrgan;
		this.vitalSign = vitalSign;
		support.firePropertyChange("addp", null, this);
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

	public void addPropertyChangeListener(PropertyChangeListener pcl){
		support.addPropertyChangeListener(pcl);
	}

	public void removePropertyChangeListener(PropertyChangeListener pcl){
		support.removePropertyChangeListener(pcl);
	}

	public void lifeCyclePatient() {
		if (this.vitalSign == HIGHILL) {
			if (rand.nextInt(10) > 7) {
				this.setVitalSign(DEAD);
				support.firePropertyChange("removep", null, this);
				//organDispatcher.update();
			}
		}
		if (this.vitalSign == MEDIUMILL) {
			if (rand.nextInt(10) > 7) {
				this.setVitalSign(HIGHILL);
				//organDispatcher.update();
			}
		}
		if (this.vitalSign == LIGHTILL) {
			if (rand.nextInt(10) > 7) {
				this.setVitalSign(MEDIUMILL);
				//organDispatcher.update();
			}
		}
	}

	@Override
	public void run() {
		organDispatcher.update();
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				lifeCyclePatient();
				//System.out.println(getName() + "\nEtat de santé : " + getVitalSign() + "\n\n");
				if (getVitalSign() == Patient.DEAD) {
					timer.cancel();
					organDispatcher.update();
				}
			}
		}, 0, 2000);
	}

	@Override
	public String toString() {
		return "Patient [name=" + name + ", waitedOrgan=" + waitedOrgan + ", vitalSign=" + vitalSign + "]";
	}
}
