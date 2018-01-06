package contactBook.objects;

import javafx.beans.property.SimpleStringProperty;

public class Contact {

    private SimpleStringProperty name = new SimpleStringProperty("");
    private SimpleStringProperty telephoneNumber = new SimpleStringProperty("");
    private SimpleStringProperty commentary = new SimpleStringProperty("");

    public Contact(String name, String telephoneNumber) {
        this.name = new SimpleStringProperty(name);
        this.telephoneNumber = new SimpleStringProperty(telephoneNumber);
    }

    public String toString() {
        return "Name: " + name + "\nTelephone: " +
                telephoneNumber + "\nCommentary:" +
                commentary + "\n";
    }

    public Contact() {}


    public boolean equals(Contact contact) {
        if(contact.getName().equals(this.getName()) && contact.getTelephoneNumber().equals(this.getTelephoneNumber())) {
            return true;
        }
        return false;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getTelephoneNumber() {
        return telephoneNumber.get();
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber.set(telephoneNumber);
    }

    public String getCommentary() {
        return commentary.get();
    }

    public void setCommentary(String commentary) {
        this.commentary.set(commentary);
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public SimpleStringProperty telephoneNumberProperty() {
        return telephoneNumber;
    }

    public SimpleStringProperty commentaryProperty() {
        return commentary;
    }
}
