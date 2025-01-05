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
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class DashboardController {

    @FXML
    private Button homeBtn;

    @FXML
    private Button logoutBtn;
    
    @FXML
    private Button workloadBtn;
    
    @FXML
    private Button addEmployeeBtn;
    
    @FXML
    private AnchorPane add_employee_form;
    
    @FXML
    private AnchorPane home_form;
    
    @FXML
    private AnchorPane workload_form;


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
    
    public void switchForm(ActionEvent event) {
    	if(event.getSource() == homeBtn) {
    		home_form.setVisible(true);
    		workload_form.setVisible(false);
    		add_employee_form.setVisible(false);
    		homeBtn.setStyle("-fx-background-color: #00cdac;");
    		workloadBtn.setStyle("-fx-background-color: transparent;");
    		addEmployeeBtn.setStyle("-fx-background-color: transparent;");    		
    	}
    	else if(event.getSource() == workloadBtn) {
    		home_form.setVisible(false);
    		workload_form.setVisible(true);
    		add_employee_form.setVisible(false);
    		homeBtn.setStyle("-fx-background-color: transparent;");
    		workloadBtn.setStyle("-fx-background-color: #00cdac;");
    		addEmployeeBtn.setStyle("-fx-background-color: transparent;");
    	}
    	else if(event.getSource() == addEmployeeBtn) {
    		home_form.setVisible(false);
    		workload_form.setVisible(false);
    		add_employee_form.setVisible(true);
    		homeBtn.setStyle("-fx-background-color: transparent;");
    		workloadBtn.setStyle("-fx-background-color: transparent;");
    		addEmployeeBtn.setStyle("-fx-background-color: #00cdac;");
    	}
    }
}
