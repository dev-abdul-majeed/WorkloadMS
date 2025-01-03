package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class TeacherController {
	@FXML
	private TextField t_name;
	@FXML
	private TextField t_email;
	@FXML
	private TextField t_address;
	@FXML
	private TextField t_status;
	@FXML
	private Button add_teacher;
	
	String name, address, email, status;
	
	public void add_teacher(ActionEvent e) {
		name = t_name.getText();
		System.out.println("Name entered: "+ name);
	}
	

}
