package contactBook.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public class DeleteController implements Initializable {

    @FXML
    private Button btnYes;
    @FXML
    private  Button btnCancel;

    private ResourceBundle resourceBundle;
    private boolean condition = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resourceBundle = resources;
    }

    public boolean getCondition() {
        return condition;
    }

    public void actionYes (ActionEvent event) {
        condition = true;
        actionClose(event);
    }

    private void actionClose(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }

    public void actionCancel(ActionEvent event) {
        condition = false;
        actionClose(event);
    }


}
