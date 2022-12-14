package Hospital;

import People.IPatient;

import java.util.ArrayList;

public interface IHospital {
    String getName();
    String getCity();
    ArrayList<IPatient> getListOfPeople();
    void newPatient(IPatient patient);
}
