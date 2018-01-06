package contactBook.interfaces;

import contactBook.objects.Contact;

public interface Editable {

    void add(Contact contact);

    void remove(Contact contact);

    void edit(Contact contact);

}
