package contactBook.gui.start;

import contactBook.controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/contactBook/fxml/main.fxml"));
        fxmlLoader.setResources(ResourceBundle.getBundle("contactBook/bundles/Locale", new Locale("en")));
        Parent fxmlMain = fxmlLoader.load();
        MainController mainController = fxmlLoader.getController();
        mainController.setMainStage(primaryStage);

        primaryStage.setTitle(fxmlLoader.getResources().getString("key.address.book"));
        primaryStage.setMinHeight(400);
        primaryStage.setMinWidth(370);
        primaryStage.setScene(new Scene(fxmlMain, 370, 400));
        primaryStage.show();
    }


    public static void main(String[] args) { launch(args); }
}
