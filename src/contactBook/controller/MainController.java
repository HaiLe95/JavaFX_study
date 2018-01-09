package contactBook.controller;

import contactBook.interfaces.impls.CollectionAddressBook;
import contactBook.objects.Contact;
import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.CustomTextField;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private Button mainAddButton;
    @FXML
    private Button mainChangeButton;
    @FXML
    private Button mainDeleteButton;
    @FXML
    private Button mainFindButton;
    @FXML
    private CustomTextField txtSearch;
    @FXML
    private TableView mainCallView;
    @FXML
    private TableColumn<Contact, String> columnName;
    @FXML
    private TableColumn<Contact,String> columnTel;
    @FXML
    private TableColumn<Contact,String> columnComment;
    @FXML
    private Label mainUsersAmount;


    private CollectionAddressBook addressBook = new CollectionAddressBook();

    private Stage mainStage;
    private Stage editDialogStage;
    private Stage deleteDialog;
    private Parent fxmlEdit;
    private Parent fxmlDelete;
    private FXMLLoader fxmlLoader = new FXMLLoader();
    private FXMLLoader fxmlLoaderD = new FXMLLoader();
    private DeleteController deleteController;
    private EditController editController;
    private ResourceBundle resourceBundle;
    private ObservableList<Contact> backList;

    public void setMainStage(Stage mainStage) { this.mainStage = mainStage; }


    private void updateUserAmounts() {
        mainUsersAmount.setText(resourceBundle.getString("key.label.usersAmount")+ addressBook.size());
    }

    public void actionButtonPressed(ActionEvent event) {

        Object source = event.getSource();

        if (!(source instanceof Button)) {
            return;
        }

        Button clickedButton = (Button) source;


        switch (clickedButton.getId()) {

            case "mainAddButton":
                editController.setContact(new Contact());
                showEdit();
                if(editController.getContact().getName().equals("") && editController.getContact().getTelephoneNumber().equals("")) {
                    return;
                }
                addressBook.add(editController.getContact());
                backList.clear();
                backList.addAll(addressBook.getContacts());
                break;

            case "mainChangeButton":
                if (mainCallView.getSelectionModel().getSelectedItem() == null) {
                    break;
                }
                editController.setContact((Contact) mainCallView.getSelectionModel().getSelectedItem());
                showEdit();
                backList.clear();
                backList.addAll(addressBook.getContacts());
                break;

            case "mainDeleteButton":
                if (mainCallView.getSelectionModel().getSelectedItem() == null) {
                    break;
                }
                showDelete();
                if(deleteController.getCondition()) {
                    addressBook.remove((Contact)mainCallView.getSelectionModel().getSelectedItem());
                    return;
                }
                break;

        }
    }

    public void actionButtonSearch (ActionEvent event) {
        addressBook.getContacts().clear();

        for(Contact contact : backList) {
            if (contact.getName().toLowerCase().contains(txtSearch.getText().toLowerCase()) |
                    contact.getTelephoneNumber().contains(txtSearch.getText())) {
                addressBook.getContacts().add(contact);
            }
        }
    }

    private void showDelete() {
        if (deleteDialog == null) {
            deleteDialog = new Stage();
            deleteDialog.setTitle(resourceBundle.getString("key.delete"));
            deleteDialog.setMinWidth(300);
            deleteDialog.setMinHeight(80);
            deleteDialog.setResizable(false);
            deleteDialog.setScene(new Scene(fxmlDelete));
            deleteDialog.initModality(Modality.WINDOW_MODAL);
            deleteDialog.initOwner(mainStage);
        }
        deleteDialog.showAndWait();
    }


    private void showEdit() {
        if(editDialogStage == null) {
            editDialogStage = new Stage();
            editDialogStage.setTitle(resourceBundle.getString("key.update"));
            editDialogStage.setMinHeight(185);
            editDialogStage.setMinWidth(400);
            editDialogStage.setResizable(false);
            editDialogStage.setScene(new Scene(fxmlEdit));
            editDialogStage.initModality(Modality.WINDOW_MODAL);
            editDialogStage.initOwner(mainStage);
        }

        editDialogStage.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resourceBundle = resources;
//    Связывание столбцов по Contact, по его полю name ect
        columnName.setCellValueFactory(new PropertyValueFactory<Contact, String>("name"));
        columnComment.setCellValueFactory(new PropertyValueFactory<Contact, String>("commentary"));
        columnTel.setCellValueFactory(new PropertyValueFactory<Contact, String>("telephoneNumber"));
        setupClearButtonField(txtSearch);
        initListeners();
        fillData();
        fxmlLoad();
    }

    private void setupClearButtonField(CustomTextField customTextField) {
        try {
            Method m = TextFields.class.getDeclaredMethod("setupClearButtonField", TextField.class, ObjectProperty.class);
            m.setAccessible(true);
            m.invoke(null, customTextField, customTextField.rightProperty());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void fxmlLoad() {
        try {
            //Присвоение через fxml ссылки на контроллер для подальших манипуляций
            fxmlLoader.setLocation(getClass().getResource("/contactBook/fxml/edit.fxml"));
            fxmlLoader.setResources(ResourceBundle.getBundle("contactBook/bundles/Locale", new Locale("en")));
            fxmlEdit = fxmlLoader.load();
            editController = fxmlLoader.getController();

            fxmlLoaderD.setLocation(getClass().getResource("/contactBook/fxml/delete.fxml"));
            fxmlLoaderD.setResources(ResourceBundle.getBundle("contactBook/bundles/Locale", new Locale("en")));
            fxmlDelete = fxmlLoaderD.load();
            deleteController = fxmlLoaderD.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void fillData() {
        addressBook.fillAddressBook();
        backList = FXCollections.observableArrayList();
        backList.addAll(addressBook.getContacts());
        mainCallView.setItems(addressBook.getContacts());
    }
    private void initListeners() {

        //Когда происходит изменение в коллекции addressBook то выполяется действие updateUserAmounts
        addressBook.getContacts().addListener(new ListChangeListener<Contact>() {
            @Override
            public void onChanged(Change<? extends Contact> c) {
                updateUserAmounts();
            }
        });

        mainCallView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            //Если мы клацаем на таблицу callView 2 раза, открывается редактирование
            @Override
            public void handle(MouseEvent event) {
                if(event.getClickCount() == 2) {
                    editController.setContact((Contact)mainCallView.getSelectionModel().getSelectedItem());
                    showEdit();
                }
            }
        });
    }

}
