package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private Button loginBtn;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    void login(ActionEvent event) {
    	String uName = username.getText();
    	String pWord = password.getText();
    	int resp = validator(uName, pWord);
    	System.out.println(resp);
    	Alert alert;
    	if(resp == 0) {
    		alert = new Alert (AlertType.ERROR);
    		alert.setTitle("Error");
    		alert.setHeaderText("Empty Input Field");
    		alert.setContentText("Please enter both username and password");
    		alert.showAndWait();
    	}
    	else if (resp == -1) {
    		alert = new Alert (AlertType.ERROR);
    		alert.setTitle("Error");
    		alert.setHeaderText("Invalid Credentials");
    		alert.setContentText("Incorrect username or password");
    		alert.showAndWait();
    	}
    	else {
    		try {
	    		loginBtn.getScene().getWindow().hide();
	    		Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				Stage primaryStage = new Stage();
				primaryStage.setScene(scene);
				primaryStage.show();
    		}
    		catch(Exception e){
    			e.printStackTrace();

    		}
    	}

    }
    
    int validator(String uName, String pwd) {
    	System.out.println(uName + "  " + pwd);
    	if (uName.isEmpty() || pwd.isEmpty()) {
    		return 0;
    	}
    	else if (uName.equals("admin") && pwd.equals("admin")) {
    		return 1;
    	}
    	else {
    		return -1;
    	}
    }

}
