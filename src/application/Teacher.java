package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class Teacher {
    private int id;
    private  String name;
    private  String department;
    private  String status;
    private static final ArrayList<Teacher> allTeachers = new ArrayList<>();
    private static int idCount = 1;

    public Teacher(String name, String department, String status, Optional <Boolean>isLoading) {
    	this.id = idCount;
        this.name = name;
        this.department = department;
        this.status = status;
        
        allTeachers.add(this);
        
        idCount = isLoading.isPresent() ? idCount : idCount + 1;
        
        
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public String getStatus() {
        return status;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static void addTeacher(Teacher t) {
    	allTeachers.add(t);
    }
    
    public static String getNameById(Integer id) {
    	String name = "";
    	for (Teacher teacher : allTeachers) {
            if (teacher.getId() == id) {
            	name = teacher.getName();	
            }
        }
    	return name;
    }
    public void print() {
    	System.out.println( " Teacher id: " + id +
    			"\n name: " + name +
    			"\n department: " + department +
    			"\n status: " + status);
    };

    public static void printAll() {
    	for(int i = 0; i < allTeachers.size(); i++) {
    		System.out.println("--------------- Teacher:"+(i+1)+"---------\n");
	    	allTeachers.get(i).print();
    	}
    };

    public static ArrayList<Teacher> teacherList() {
    	return allTeachers;
    }
    
    public static ArrayList<String> teacherNameList() {
    	ArrayList<String> names = new ArrayList<>();
        for (Teacher teacher : allTeachers) {
            names.add(teacher.getId()+": "+teacher.getName());
        }
        return names;
    }
    
    public static String validateTeacher(String name, String department, String status) {
    	String error = "";
    	if (name.isBlank()) {
            error += "Please provide a Name \n";
        }
    	
    	if (department == null) {
            error += "Please select a Department \n";
        }
    	
    	if (status == null) {
            error += "Please select a Status \n";
        }

    	return error;

    }
    
    public static void exportTeacherToCSV(String filePath) throws IOException {
    	filePath = (filePath.isEmpty() ? "teachers.csv" : filePath);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("ID,Name,Department,Status\n"); // Header row
            for (Teacher teacher : allTeachers) {
                writer.write(teacher.id + "," + teacher.name + "," + teacher.department + "," + teacher.status + "\n");
            }
        }
        catch (IOException e){
        	System.out.println(e);
        }
    }

    public static void importTeacherFromCSV(String filePath) throws IOException {
        try (Scanner reader = new Scanner(new File("teachers.csv"))) {
            String line = reader.nextLine(); // Skip header row
            if (line == null || !line.equals("ID,Name,Department,Status")) {
                throw new IOException("Invalid CSV format.");
            }

            allTeachers.clear(); // Clear current data
            int maxId = 0; // To track the greatest ID

            while (reader.hasNextLine()) {
            	line = reader.nextLine();
                String[] fields = line.split(",");
                if (fields.length != 4) {
                    throw new IOException("Invalid data in CSV file.");
                }
                int id = Integer.parseInt(fields[0]); // Parse ID
                String name = fields[1];
                String department = fields[2];
                String status = fields[3];

                // Create a new Teacher with the given ID
                Teacher teacher = new Teacher(name, department, status, Optional.of(true));
                teacher.id = id; // Set the ID explicitly
                maxId = Math.max(maxId, id); // Update the maximum ID
            }

            // Update idCount to continue from the highest ID + 1
            idCount = maxId + 1;
        }
    }


}