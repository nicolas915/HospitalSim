package People;

import java.util.Map;

public interface IPatient {
    String getName();
    int getVitalSign();
    String getWaitedOrgan();
    Map<String, String> getContactList();
    void setVitalSign(int vitalSign);
    void addContact(String contact, String relation);
    void lifeCyclePatient();

}
