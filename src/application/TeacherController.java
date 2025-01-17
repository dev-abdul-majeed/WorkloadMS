package application;

import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class TeacherController {
    @FXML
    private Button add_teacher;
    
    @FXML
    private Button add_teacher_wl;
    
    @FXML
    private Button clear_teacher;

    @FXML
    private Button delete_teacher;
    
    @FXML
    private Button update_teacher;

    @FXML
    private TableView<Teacher> all_teachers;

    @FXML
    private TextField t_name;

    @FXML
    private TableColumn<Integer, Teacher> teacher_id_col;
    
    @FXML
    private TableColumn<String, Teacher> teacher_department_col;

    @FXML
    private TableColumn<String, Teacher> teacher_name_col;

    @FXML
    private TableColumn<String, Teacher> teacher_status_col;
    
    @FXML
    private ComboBox<String> t_status;

    @FXML
    private ComboBox<String> t_department;
    
	String name, department, status;
	
	public void initialize() {
		teacher_id_col.setCellValueFactory(new PropertyValueFactory<>("id"));
	    teacher_name_col.setCellValueFactory(new PropertyValueFactory<>("name"));
	    teacher_department_col.setCellValueFactory(new PropertyValueFactory<>("department"));
	    teacher_status_col.setCellValueFactory(new PropertyValueFactory<>("status"));
	    ObservableList<String> statuses = FXCollections.observableArrayList("Part Time", "Full Time");
	    ObservableList<String> departments = FXCollections.observableArrayList("FAST", "AHS", "EAF", "Engineering");
	    t_status.setItems(statuses);
	    t_department.setItems(departments);
	    update_teacher.setDisable(true);
	    delete_teacher.setDisable(true);
	    all_teachers.setItems(FXCollections.observableArrayList(Teacher.teacherList()));
		
	}

	
	public void add_teacher(ActionEvent e) {
		name = t_name.getText();
		department = t_department.getSelectionModel().getSelectedItem();;
		status = t_status.getSelectionModel().getSelectedItem();
		String error = Teacher.validateTeacher(name, department, status);
		if(error.isEmpty()) {
			Teacher t = new Teacher(name, department, status);
			Teacher.addTeacher(t);
			Teacher.printAll();
			all_teachers.setItems(FXCollections.observableArrayList(Teacher.teacherList()));
	        clear_input_fields(e);

		}
		
		else {
			showError(error);
		}
		
		
	}
	
	public void edit_teacher() {
		Teacher t = all_teachers.getSelectionModel().getSelectedItem();
		System.out.print("Selected:" + t.getName());
		t_name.setText(t.getName());
		t_department.getSelectionModel().select(t.getDepartment());
		t_status.getSelectionModel().select(t.getStatus());
		add_teacher.setDisable(true);
		update_teacher.setDisable(false);
	    delete_teacher.setDisable(false);
	}

    @FXML
    void clear_input_fields(ActionEvent event) {
    	t_name.clear();
		t_department.getSelectionModel().clearSelection();
		t_status.getSelectionModel().clearSelection();
		add_teacher.setDisable(false);
		update_teacher.setDisable(true);
		delete_teacher.setDisable(true);
    }

    @FXML
    void delete_selected_teacher(ActionEvent event) {
    	
		Teacher t = all_teachers.getSelectionModel().getSelectedItem();
		Alert alert = new Alert(AlertType.CONFIRMATION);
	    alert.setTitle("Deleting Teacher");
	    alert.setHeaderText("Do you want to delete realted workloads?");
	    
	    Optional<ButtonType> result = alert.showAndWait();
	    if (result.get() == ButtonType.OK){
	        Workload.getWorkloads().removeIf(workload -> workload.getTeacherId() == t.getId());
	    } 
	    
        Teacher.teacherList().removeIf(teacher -> teacher.getId() == t.getId());
		all_teachers.setItems(FXCollections.observableArrayList(Teacher.teacherList()));
        all_teachers.refresh();

        clear_input_fields(event);
    
    }
    
    @FXML
    void update_selected_teacher(ActionEvent event) {
    	
		Teacher t = all_teachers.getSelectionModel().getSelectedItem();
		name = t_name.getText();
		department = t_department.getSelectionModel().getSelectedItem();;
		status = t_status.getSelectionModel().getSelectedItem();
		String error = Teacher.validateTeacher(name, department, status);
		if(error.isEmpty()) {
			for (Teacher teacher : Teacher.teacherList()) {
	            if(teacher.getId() == t.getId()) {
	            	teacher.setName(name);
	        		teacher.setDepartment(department);
	        		teacher.setStatus(status);
	        		System.out.print("Updated:" + t.getName());
	        		break;
	            }
	        }
			
			for (Workload w : Workload.getWorkloads()) {
	            if(w.getTeacherId() == t.getId()) {
	            	w.setTeacherName(name);
	        		System.out.print("Updated Workloads:" + w.getId());
	            }
	        }
	        
			all_teachers.setItems(FXCollections.observableArrayList(Teacher.teacherList()));
			all_teachers.refresh();
			clear_input_fields(event);
		}
		else {
			showError(error);
		}
    }
    
	@FXML
    void switch_to_workload(ActionEvent event) {
		try {
			add_teacher_wl.getScene().getWindow().hide();
    		Parent root = FXMLLoader.load(getClass().getResource("Workload.fxml"));
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
	
	private void showError(String error) {
    	Alert alert = new Alert (AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText("Invalid input");
		alert.setContentText(error);
		alert.showAndWait();
    }
	

}
