package contactBook.objects.interfaces.impls;

import contactBook.objects.interfaces.Editable;
import contactBook.objects.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class CollectionAddressBook implements Editable {

    private ObservableList<Contact> contacts = FXCollections.observableArrayList();


    @Override
    public void add(Contact contact) {
        contacts.add(contact);
    }

    @Override
    public void remove(Contact contact) {
        contacts.remove(contact);
    }

    @Override
    public void edit(Contact contact) {

    }

    public int size() {
       return contacts.size();
    }


    public void fillAddressBook() {
        contacts.add(new Contact("Артем", "4444"));
        contacts.add(new Contact("Виталий", "5555"));
        contacts.add(new Contact("Кирилл","1111"));
        contacts.add(new Contact("Станислав", "2222"));
        contacts.add(new Contact("Николай", "3333"));

    }

    public ObservableList<Contact> getContacts() {
        return contacts;
    }
}
