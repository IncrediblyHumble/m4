package loginScreen;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import serverUtil.Login;
import serverUtil.impl.LoginHardcoded;
import serverUtil.impl.TriesExceededException;

/**
 * Created by Derek Henry
 */
public class LoginController {
    @FXML
    private TextField nameField;

    @FXML
    private TextField passField;

    @FXML
    private boolean _okClicked = false;

    @FXML
    private boolean _cancelClicked = false;

    private Stage _loginStage;

    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage the stage for this dialog
     */
    public void setDialogStage(Stage dialogStage) {
        _loginStage = dialogStage;
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancelPressed() {
        _cancelClicked = true;
        _loginStage.close();
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOKPressed() {
        //First validate the data to insure it is at least reasonable
        if (isInputValid()) {
            Login newUser = new LoginHardcoded();
            boolean success = false;
            try {
                success = newUser.verify(nameField.getText(), passField.getText());
            } catch (TriesExceededException e) {
                Alert triesExceededAlert = new Alert(Alert.AlertType.ERROR);
                triesExceededAlert.initOwner(_loginStage);
                triesExceededAlert.setHeaderText("Invalid Attempt");
                triesExceededAlert.setContentText("Too many attempted logins");
                triesExceededAlert.showAndWait();
            }
            if (success) {
                _okClicked = true;
                _loginStage.close();

            } else {
                Alert loginAlert = new Alert(Alert.AlertType.ERROR);
                loginAlert.initOwner(_loginStage);
                loginAlert.setHeaderText("Please correct invalid fields");
                loginAlert.setContentText("Invalid username or password");
                loginAlert.showAndWait();
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
            alert.initOwner(_loginStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}
