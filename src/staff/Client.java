package staff;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Client {
    private LocalDate birthDate;
    private String childFio;
    private String parentFio;
    private String childPhoneNumber;
    private String parentPhoneNumber;

    private List<Registration> registrationList = new ArrayList<>();

    public Client(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void registerNewActivity(Registration registration) {
        registrationList.add(registration);
    }

    public List<Registration> getRegistrationList() {
        return registrationList;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getChildFio() {
        return childFio;
    }

    public void setChildFio(String childFio) {
        this.childFio = childFio;
    }

    public String getParentFio() {
        return parentFio;
    }

    public void setParentFio(String parentFio) {
        this.parentFio = parentFio;
    }

    public String getChildPhoneNumber() {
        return childPhoneNumber;
    }

    public void setChildPhoneNumber(String childPhoneNumber) {
        this.childPhoneNumber = childPhoneNumber;
    }

    public String getParentPhoneNumber() {
        return parentPhoneNumber;
    }

    public void setParentPhoneNumber(String parentPhoneNumber) {
        this.parentPhoneNumber = parentPhoneNumber;
    }
}
