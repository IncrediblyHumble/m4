package loginScreen;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert;

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
        _loginStage.close();
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOKPressed() {
        //First validate the data to insure it is at least reasonable
        if (isInputValid()) {
            //if the data is reasonable, then remember the the student data in the window

            //signal success and close this dialog window.
            _okClicked = true;
            _loginStage.close();
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

        //for now just check they actually typed something
        if (nameField.getText() == null || nameField.getText().length() == 0) {
            errorMessage += "No valid student name!\n";
        }
        if (passField.getText() == null || passField.getText().length() == 0) {
            errorMessage += "No valid major entered!\n";
        }


        //no error message means success / good input
        if (!error) {
            return true;
        } else {
            // Show the error message if bad data
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
