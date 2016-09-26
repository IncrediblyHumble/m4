package loginScreen;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import serverUtil.Login;
import serverUtil.impl.LoginHardcoded;
import serverUtil.impl.TriesExceededException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

/**
 * Created by Derek Henry
 */
public class LoginController {
    @FXML
    private TextField nameField;

    @FXML
    private TextField passField;

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancelPressed(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("welcomeScreen/welcome.fxml"));
        primaryStage.setScene(new Scene(root, 700, 500));
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOKPressed(ActionEvent event) throws IOException {
        //First validate the data to insure it is at least reasonable
        if (isInputValid()) {
            Login newUser = new LoginHardcoded();
            try {
                if(newUser.verify(nameField.getText(), passField.getText())) {
                    Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("homeScreen/home.fxml"));
                    primaryStage.setScene(new Scene(root, 700, 500));
                }  else {
                    Alert loginAlert = new Alert(Alert.AlertType.ERROR);
                    loginAlert.setHeaderText("Please correct invalid fields");
                    loginAlert.setContentText("Invalid username or password");
                    loginAlert.showAndWait();
                }
            } catch (TriesExceededException e) {
                Alert triesExceededAlert = new Alert(Alert.AlertType.ERROR);
                triesExceededAlert.setHeaderText("Invalid Attempt");
                triesExceededAlert.setContentText("Too many attempted logins");
                triesExceededAlert.showAndWait();
            }
        }
    }

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";
        boolean error = false;

        //checks if they typed something
        if (nameField.getText() == null || nameField.getText().length() == 0) {
            error = true;
            errorMessage += "No valid username entered.\n";
        }
        if (passField.getText() == null || passField.getText().length() == 0) {
            error = true;
            errorMessage += "No valid password entered.\n";
        }

        //no error message means success / good input
        if (!error) {
            return true;
        } else {
            // Puts an alert if they didn't do anything
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}
