package com.dariodev;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Employee {
    private final StringProperty id;
    private final StringProperty firstname;
    private final StringProperty lastname;
    private final StringProperty city;
    private final StringProperty phone;
    private final StringProperty salary;

    public Employee() {
        id = new SimpleStringProperty("id");
        firstname = new SimpleStringProperty("firstname");
        lastname = new SimpleStringProperty("lastname");
        city = new SimpleStringProperty("city");
        phone = new SimpleStringProperty("phone");
        salary = new SimpleStringProperty("salary");
    }

    public StringProperty idProperty() { return id; }
    public String getId() { return id.get(); }
    public void setId(String newId) { id.set(newId); }

    public StringProperty firstnameProperty() { return firstname; }
    public String getFirstName() { return firstname.get(); }
    public void setFirstName(String newFirstName) { firstname.set(newFirstName); }

    public StringProperty lastnameProperty() { return lastname; }
    public String getLastName() { return lastname.get(); }
    public void setLastName(String newLastName) { lastname.set(newLastName); }

    public StringProperty cityProperty() { return city; }
    public String getCity() { return city.get(); }
    public void setCity(String newCity) { city.set(newCity); }

    public StringProperty phoneProperty() { return phone; }
    public String getPhone() { return phone.get(); }
    public void setPhone(String newPhone) { phone.set(newPhone); }

    public StringProperty SalaryProperty() { return salary; }
    public String getSalary() { return salary.get(); }
    public void setSalary(String newSalary) { salary.set(newSalary); }
}
