package application;

import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class DashboardController {

    @FXML
    private Button homeBtn;

    @FXML
    private Button logoutBtn;

    @FXML
    void logout(ActionEvent event) {
    	Alert alert;
		alert = new Alert (AlertType.CONFIRMATION);
		alert.setTitle("Log out");
		alert.setContentText("Are you sure to Log out?");
		Optional <ButtonType> option = 	alert.showAndWait();
		try {
			if (option.get().equals(ButtonType.OK)) {
				logoutBtn.getScene().getWindow().hide();
				Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
				Scene scene = new Scene(root);
				Stage primaryStage = new Stage();
				primaryStage.setScene(scene);
				primaryStage.show();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}


    }

}
