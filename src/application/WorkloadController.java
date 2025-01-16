package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class WorkloadController {
    @FXML
    private ComboBox<String> wlf_activity;

    @FXML
    private Button wlf_add;

    @FXML
    private Button wlf_teachers;

    @FXML
    private TextField wlf_description;

    @FXML
    private TextField wlf_duration;

    @FXML
    private TextField wlf_instances;

    @FXML
    private ComboBox<String> wlf_teacher;

    @FXML
    private ComboBox<String> wlf_type;

    @FXML
    private ComboBox<String> wlf_year;

    @FXML
    void add_workload(ActionEvent event) {
    	String teacher, type, activity, description, year, duration, instances;
    	teacher = wlf_teacher.getSelectionModel().getSelectedItem();
    	type = wlf_type.getSelectionModel().getSelectedItem();	
    	activity = wlf_activity.getSelectionModel().getSelectedItem();
    	description = wlf_description.getText();
    	year = wlf_year.getSelectionModel().getSelectedItem();
    	duration = wlf_duration.getText();
    	instances = wlf_instances.getText();
    	
    	String error = Workload.validateWorkload(teacher, type, activity, description, year, duration, instances);
    	System.out.println("Error: "+error);
    	if (error == "") {
    		int id = Integer.parseInt(teacher.split(":")[0]);
    		double dur = Double.parseDouble(duration);
    		double ins = Double.parseDouble(instances);
    		new Workload(id, type, activity, description, year, dur, ins);
    		showWorkloadConfirmation("Added workload for: " + teacher + 
				                     "\nType: " + type + 
				                     "\nActivity: " + activity + 
				                     "\nDescription: " + description + 
				                     "\nYear: " + year + 
				                     "\nActivity Duration: " + activity + 
				                     "\nInstances: " + instances);
    	}
    	else {
    		showError(error);
    	}
    }
    
    public void initialize() {
	    ObservableList<String> teachers = FXCollections.observableArrayList(Teacher.teacherNameList());
	    ObservableList<String> types = FXCollections.observableArrayList(Workload.getTypes());
	    ObservableList<String> activities = FXCollections.observableArrayList(Workload.getActivities());
	    ObservableList<String> years = FXCollections.observableArrayList(Workload.getYears());

    	wlf_teacher.setItems(teachers);
    	wlf_type.setItems(types);
    	wlf_activity.setItems(activities);
    	wlf_year.setItems(years);


    }
    @FXML
    void switch_to_teachers(ActionEvent event) {
    	try {
    		wlf_teachers.getScene().getWindow().hide();
    		Parent root = FXMLLoader.load(getClass().getResource("Teacher.fxml"));
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
    
    private void showWorkloadConfirmation(String info) {
    	Alert alert = new Alert (AlertType.CONFIRMATION);
		alert.setTitle("Success!");
		alert.setHeaderText("Workload Added: ");
		alert.setContentText(info);
		alert.showAndWait();
    }
    
    private void showError(String error) {
    	Alert alert = new Alert (AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText("Invalid input");
		alert.setContentText(error);
		alert.showAndWait();
    }

}
