package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class TeacherController {
    @FXML
    private Button add_teacher;
    
    @FXML
    private Button add_teacher_wl;

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
		all_teachers.setItems(FXCollections.observableArrayList(Teacher.teacherList()));
	}

	
	public void add_teacher(ActionEvent e) {
		name = t_name.getText();
		department = t_department.getSelectionModel().getSelectedItem();;
		status = t_status.getSelectionModel().getSelectedItem();
		Teacher t = new Teacher(name, department, status);
		Teacher.addTeacher(t);
		Teacher.printAll();
		all_teachers.setItems(FXCollections.observableArrayList(Teacher.teacherList()));
		
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
	

}
