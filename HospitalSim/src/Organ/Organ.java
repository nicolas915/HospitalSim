package Organ;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Organ implements IOrgan, Runnable {
	private static OrganDispatcher organDispatcher = OrganDispatcher.getInstance();

	private PropertyChangeSupport support;
	
	private Random rand = new Random();
	public static final int FRESH = 0;
	public static final int DAMAGED = 1;
	public static final int ROTTEN = 2;
	
	private String name;
	private int state;

	public Organ(String name, int state, PropertyChangeListener pcl) {
		support = new PropertyChangeSupport(this);
		support.addPropertyChangeListener(pcl);
		this.name = name;
		this.state = state;
		support.firePropertyChange("addo", null, this);
	}

	public String getName() {
		return this.name;
	}

	public int getState() {
		return this.state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public void addPropertyChangeListener(PropertyChangeListener pcl){
		support.addPropertyChangeListener(pcl);
	}

	public void removePropertyChangeListener(PropertyChangeListener pcl){
		support.removePropertyChangeListener(pcl);
	}

	public void lifeCycleOrgan() {
		if (this.state == DAMAGED) {
			if (rand.nextInt(10) > 7) {
				this.setState(ROTTEN);
				support.firePropertyChange("removeo", null, this);
				//organDispatcher.update();
			}
		}
		if (this.state == FRESH) {
			if (rand.nextInt(10) > 7) {
				this.setState(DAMAGED);
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
				lifeCycleOrgan();
				//System.out.println(getName() + " est dans l'état : " + getState() + "\n\n");
				if (getState() == Organ.ROTTEN) {
					timer.cancel();
					organDispatcher.update();
				}
			}
		}, 0, 1000);
	}

	@Override
	public String toString() {
		return "Organ [name=" + String.format("%8s", name) + "\tstate=" + state + "]";
	}
	
	
}
