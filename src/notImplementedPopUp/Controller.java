package notImplementedPopUp;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Window;


    public class Controller {;

        /**
         * notImplemented takes in a screen and displays error message alerting that
         * this feature hasn't been implemented yet
         * @param owner screen that will display the error
         */
        @FXML
        public void notImplemented(Window owner) {
            // Show the error message for unimplemented methods
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(owner);
            alert.setTitle("Unimplemented");
            alert.setHeaderText("Unimplemented Feature");
            alert.setContentText("Sorry, this feature has not been implemented yet!");

            alert.showAndWait();

        }
    }
}
