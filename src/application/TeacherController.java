package application;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


public class TeacherController {
    @FXML
    private Button add_teacher;

    @FXML
    private TableView<Teacher> all_teachers;

    @FXML
    private TextField t_department;

    @FXML
    private TextField t_name;

    @FXML
    private TextField t_status;

    @FXML
    private TableColumn<Integer, Teacher> teacher_id_col = new TableColumn<> ("id");
    
    @FXML
    private TableColumn<String, Teacher> teacher_department_col = new TableColumn<> ("department");

    @FXML
    private TableColumn<String, Teacher> teacher_name_col = new TableColumn<> ("name");;

    @FXML
    private TableColumn<String, Teacher> teacher_status_col = new TableColumn<> ("status");;
	
	String name, department, status;
	
	public void initialize() {
		teacher_id_col.setCellValueFactory(new PropertyValueFactory<>("id"));
	    teacher_name_col.setCellValueFactory(new PropertyValueFactory<>("name"));
	    teacher_department_col.setCellValueFactory(new PropertyValueFactory<>("department"));
	    teacher_status_col.setCellValueFactory(new PropertyValueFactory<>("status"));
	}

	
	public void add_teacher(ActionEvent e) {
		name = t_name.getText();
		department = t_department.getText();
		status = t_status.getText();
		Teacher t = new Teacher(name, department, status);
		Teacher.addTeacher(t);
		Teacher.printAll();
		all_teachers.setItems(FXCollections.observableArrayList(Teacher.teacherList()));
		
	}
		
	

}
