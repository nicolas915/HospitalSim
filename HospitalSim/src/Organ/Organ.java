package Organ;

import java.util.Random;
public class Organ implements IOrgan{

    private Random rand = new Random();
    private static final int FRESH = 0;
    private static final int DAMAGED = 1;
    private static final int ROTTEN = 2;

    private String name;
    private int state;

    public Organ(String name, int state){
        this.name = name;
        this.state = state;
    }

    public String getName(){return this.name;}
    public int getState(){return this.state;}
    public void setState(int state){
        this.state = state;
    }

    public void lifeCycleOrgan(){
        if(this.state == DAMAGED){
            if(rand.nextInt(10)>7){
                this.setState(ROTTEN);
            }
        }
        if(this.state == FRESH){
            if(rand.nextInt(10)>7){
                this.setState(DAMAGED);
            }
        }
    }

}
