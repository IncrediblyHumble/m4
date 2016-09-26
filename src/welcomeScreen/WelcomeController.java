package welcomeScreen;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomeController {
    public void login(ActionEvent event)throws IOException{
        Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("loginScreen/loginScreen.fxml"));
        primaryStage.setScene(new Scene(root, 700, 500));
    }
}
