package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DashboardController{
	
	

    @FXML
    private Button addEmployeeBtn;

    @FXML
    private AnchorPane add_employee_form;

    @FXML
    private Label home_employees_full_time;

    @FXML
    private Label home_employees_part_time;

    @FXML
    private Label home_employees_total;

    @FXML
    private AnchorPane home_form;

    @FXML
    private Button logoutBtn;

    @FXML
    private Button workloadBtn;
    
    
    public void initialize() {
    	home_employees_total.setText(""+Teacher.teacherList().size());
    	home_employees_part_time.setText(""+Teacher.partTimeCount());
    	home_employees_full_time.setText(""+Teacher.fullTimeCount());
    }

    @FXML
    void logout(ActionEvent event) {
    	switchScene("Login");

    }

    @FXML
    void switchAddTeacher(ActionEvent event) {
    	switchScene("Teacher");
    }

    @FXML
    void switchAddWorkload(ActionEvent event) {
    	switchScene("Workload");
    }
    
    void switchScene(String screenName) {
    	try {
    		logoutBtn.getScene().getWindow().hide();
    		Parent root = FXMLLoader.load(getClass().getResource(screenName+".fxml"));
			Scene scene = new Scene(root);
			Stage primaryStage = new Stage();
			primaryStage.setScene(scene);
			primaryStage.show();
			
		}
		catch(Exception e){
			e.printStackTrace();

		}
    }

}
