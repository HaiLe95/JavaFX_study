package contactBook.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class DeleteController {

    @FXML
    private Button btnYes;
    @FXML
    private  Button btnCancel;

    private boolean condition = false;


    public boolean getCondition() {
        return condition;
    }

    public void actionYes (ActionEvent event) {
        condition = true;
        actionClose(event);
    }

    public void actionClose(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }

    public void actionCancel(ActionEvent event) {
        condition = false;
        actionClose(event);
    }


}
