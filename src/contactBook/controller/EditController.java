package contactBook.controller;

import contactBook.objects.Contact;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditController {
    @FXML
    private Button modalOkButton;
    @FXML
    private Button modalCancelButton;
    @FXML
    private TextField modalNameTextField;
    @FXML
    private TextField modalNumberTextField;
    @FXML
    private TextField modalCommentaryTextField;

    private Contact contact;

    public void actionClose(ActionEvent event) {
        //Так как у нас всего одно модальное окно, можно его скрыть, но если понадобится закрыть используй метот close()
        Node source = (Node)event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
//        stage.hide();
        stage.close();
    }


    public Contact getContact() {
        return contact;
    }

    public void actionSave(ActionEvent event) {
        contact.setName(modalNameTextField.getText());
        contact.setCommentary(modalCommentaryTextField.getText());
        String s = modalNumberTextField.getText();
        try {
            Long l = Long.parseLong(s);
            contact.setTelephoneNumber(l.toString());
        } catch (Exception e) {
            e.printStackTrace();
            modalNumberTextField.setText("Ошибка ввода номера!");
            return;
        }
        actionClose(event);

    }

    public void setContact(Contact contact) {
        if (contact == null) {
            return;
        }
        this.contact = contact;
        modalNameTextField.setText(contact.getName());
        modalNumberTextField.setText(contact.getTelephoneNumber());
        modalCommentaryTextField.setText(contact.getCommentary());
    }

}
