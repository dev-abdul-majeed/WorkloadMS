package application;

import java.io.IOException;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class WorkloadController {
    @FXML
    private ComboBox<String> wlf_activity;

    @FXML
    private Button wlf_add;
    
    @FXML
    private Button wlf_clear;
    
    @FXML
    private Button wlf_delete;
    
    @FXML
    private Button wlf_update;

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
    private TableView<Workload> wlf_table;
    
    @FXML
    private TableColumn<Integer, Workload> wlf_col_activity;

    @FXML
    private TableColumn<Double, Workload> wlf_col_atsr;

    @FXML
    private TableColumn<String, Workload> wlf_col_description;

    @FXML
    private TableColumn<Double, Workload> wlf_col_duration;

    @FXML
    private TableColumn<Double, Workload> wlf_col_instances;

    @FXML
    private TableColumn<Double, Workload> wlf_col_other;

    @FXML
    private TableColumn<Double, Workload> wlf_col_sa;

    @FXML
    private TableColumn<Integer, Workload> wlf_col_teacher;
    
    @FXML
    private TableColumn<String, Workload> wlf_col_teacher_name;

    @FXML
    private TableColumn<Double, Workload> wlf_col_tlr;

    @FXML
    private TableColumn<Double, Workload> wlf_col_ts;

    @FXML
    private TableColumn<String, Workload> wlf_col_type;

    @FXML
    private TableColumn<String, Workload> wlf_col_year;
        
	String teacher, type, activity, description, year, duration, instances;
	
	 public void initialize() {
		    ObservableList<String> teachers = FXCollections.observableArrayList(Teacher.teacherNameList());
		    ObservableList<String> types = FXCollections.observableArrayList(Workload.getTypes());
		    ObservableList<String> activities = FXCollections.observableArrayList(Workload.getActivities());
		    ObservableList<String> years = FXCollections.observableArrayList(Workload.getYears());
		    
	    	wlf_teacher.setItems(teachers);
	    	wlf_type.setItems(types);
	    	wlf_activity.setItems(activities);
	    	wlf_year.setItems(years);
	    	
	    	wlf_add.setDisable(false);
	    	wlf_update.setDisable(true);
	    	wlf_delete.setDisable(true);
	    	
	    	wlf_col_activity.setCellValueFactory(new PropertyValueFactory<>("activity"));
	    	wlf_col_atsr.setCellValueFactory(new PropertyValueFactory<>("atsr"));
	    	wlf_col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
	    	wlf_col_duration.setCellValueFactory(new PropertyValueFactory<>("activityDuration"));
	    	wlf_col_instances.setCellValueFactory(new PropertyValueFactory<>("instances"));
	    	wlf_col_other.setCellValueFactory(new PropertyValueFactory<>("other"));
	    	wlf_col_sa.setCellValueFactory(new PropertyValueFactory<>("sa"));
	    	wlf_col_teacher.setCellValueFactory(new PropertyValueFactory<>("teacherId"));
	    	wlf_col_teacher_name.setCellValueFactory(new PropertyValueFactory<>("teacherName"));
	    	wlf_col_tlr.setCellValueFactory(new PropertyValueFactory<>("tlr"));
	    	wlf_col_ts.setCellValueFactory(new PropertyValueFactory<>("ts"));
	    	wlf_col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
	    	wlf_col_year.setCellValueFactory(new PropertyValueFactory<>("year"));

	    	try{
		    	Workload.importWorkloadFromCSV("");
		    	}
		    catch(IOException e ) {
		    	System.out.println(e);
		    };

			wlf_table.setItems(FXCollections.observableArrayList(Workload.getWorkloads()));
			wlf_table.refresh();

	    }

    @FXML
    void add_workload(ActionEvent event) {
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
    		double dur = Math.ceil(Double.parseDouble(duration));
    		double ins = Math.ceil(Double.parseDouble(instances));
    		new Workload(id, type, activity, description, year, dur, ins);
    		showWorkloadConfirmation("Added workload for: " + teacher + 
				                     "\nType: " + type + 
				                     "\nActivity: " + activity + 
				                     "\nDescription: " + description + 
				                     "\nYear: " + year + 
				                     "\nActivity Duration: " + activity + 
				                     "\nInstances: " + instances);
    		wlf_table.setItems(FXCollections.observableArrayList(Workload.getWorkloads()));
    		
    		persistData();
    	}
    	
    	else {
    		showError(error);
    	}
    }
    
   
    @FXML
    void clear_workload(ActionEvent event) {
    	wlf_teacher.getSelectionModel().clearSelection();
    	wlf_type.getSelectionModel().clearSelection();	
    	wlf_activity.getSelectionModel().clearSelection();
        wlf_description.clear();
    	wlf_year.getSelectionModel().clearSelection();
    	wlf_duration.clear();
    	wlf_instances.clear();

    	wlf_add.setDisable(false);
    	wlf_update.setDisable(true);
    	wlf_delete.setDisable(true);

    }
    

    @FXML
    void delete_workload(ActionEvent event) {
    	
    	Workload w = wlf_table.getSelectionModel().getSelectedItem();

        Workload.getWorkloads().removeIf(workload -> workload.getId() == w.getId());
        
        persistData();
        
		wlf_table.setItems(FXCollections.observableArrayList(Workload.getWorkloads()));
        wlf_table.refresh();
        
    	clear_workload(event);
    }

    @FXML
    void edit_workload() {
    	Workload w = wlf_table.getSelectionModel().getSelectedItem();
		System.out.print("Selected:" + w.getTeacherName());
		wlf_teacher.getSelectionModel().select(w.getTeacherId() + ": " + w.getTeacherName());
		wlf_type.getSelectionModel().select(w.getType());
		wlf_activity.getSelectionModel().select(w.getActivity());
		wlf_description.setText(w.getDescription());
		wlf_year.getSelectionModel().select(w.getYear());
		wlf_duration.setText("" + w.getActivityDuration());
		wlf_instances.setText("" + w.getInstances());

    	wlf_add.setDisable(true);
    	wlf_update.setDisable(false);
    	wlf_delete.setDisable(false);
    }
    
    @FXML
    void update_workload(ActionEvent event) {
    	Workload w = wlf_table.getSelectionModel().getSelectedItem();
    	teacher = wlf_teacher.getSelectionModel().getSelectedItem();
    	type = wlf_type.getSelectionModel().getSelectedItem();	
    	activity = wlf_activity.getSelectionModel().getSelectedItem();
    	description = wlf_description.getText();
    	year = wlf_year.getSelectionModel().getSelectedItem();
    	duration = wlf_duration.getText();
    	instances = wlf_instances.getText();
    	
    	String error = Workload.validateWorkload(teacher, type, activity, description, year, duration, instances);
    	System.out.println("Update Error: "+error);
    	if (error == "") {
	        for (Workload workload : Workload.getWorkloads()) {
	            if(workload.getId() == w.getId()) {
	            	int id = Integer.parseInt(teacher.split(":")[0]);
	            	String t_name = teacher.split(":")[1];
	
	        		double dur = Math.ceil(Double.parseDouble(duration));
	        		double ins = Math.ceil(Double.parseDouble(instances));
	        		
	        		workload.setTeacherId(id);
	        		workload.setTeacherName(t_name);
	        		workload.setType(type);
	        		workload.setActivity(activity);
	        		workload.setDescription(description);
	        		workload.setYear(year);
	        		workload.setActivityDuration(dur);
	        		workload.setInstances(ins);
	        		workload.setHours(type, dur, ins);
	
	        		System.out.print("Updated:" + w.getId());
	        		break;
	            }
	        }
	        persistData();
	        
			wlf_table.setItems(FXCollections.observableArrayList(Workload.getWorkloads()));
			wlf_table.refresh();
	
	    	clear_workload(event);
    	}
    	else {
    		showError(error);
    	}
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
    
    private void persistData() {
		try {
        	Workload.exportWorkloadToCSV("");
        }
        catch(IOException err) {
    		System.out.print("Error while exporting: " + err);
        }
	}

}
