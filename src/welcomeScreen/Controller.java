package welcomeScreen;

import fxapp.MainFXApplication;

import javafx.fxml.FXML;

import javafx.scene.control.Alert;

public class Controller {
    /** reference back to mainApplication if needed */
    private MainFXApplication mainApplication;

    /**
     * Setup the main application link so we can call methods there
     *
     * @param mainFXApplication  a reference (link) to our main class
     */
    public void setMainApp(MainFXApplication mainFXApplication) {

        mainApplication = mainFXApplication;

    }

    /**
     * Button handler for login
     * Currently not implemented
     */
    @FXML
    public void userLogin() {
        boolean okClicked = mainApplication.showStudentAddDialog(tempStudent);
        if (okClicked) {
            if (!Model.getInstance().addStudent(tempStudent)) {
                //if the add fails, notify the user
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(mainApplication.getMainScreen());
                alert.setTitle("User Not Added");
                alert.setHeaderText("Bad User Add");
                alert.setContentText("User was not added, check that they are not already in class!");

                alert.showAndWait();
            }
        }

    }
}
