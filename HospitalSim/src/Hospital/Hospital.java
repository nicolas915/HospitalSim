package Hospital;

import People.IPatient;

import java.util.ArrayList;

public class Hospital implements IHospital{
    private String name;
    private String city;
    private ArrayList<IPatient> listOfPeople;

    public Hospital(String name, String city){
        this.name = name;
        this.city = city;
        listOfPeople = new ArrayList<IPatient>();
    }

    public String getName(){return this.name;}
    public String getCity(){return this.city;}
    public ArrayList<IPatient> getListOfPeople(){return this.listOfPeople;}

    public void newPatient(IPatient patient){
        listOfPeople.add(patient);
    }
}
