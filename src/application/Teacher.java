package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Teacher {
    private int id;
    private  String name;
    private  String department;
    private  String status;
    private static final ArrayList<Teacher> allTeachers = new ArrayList<>();
    private static int idCount = getGlobalId();

    public Teacher(String name, String department, String status) {
    	this.id = idCount;
        this.name = name;
        this.department = department;
        this.status = status;
        
        allTeachers.add(this);
        
        idCount = idCount + 1;
        setGlobalId();
        
        
    }
    
    public Teacher(int id, String name, String department, String status) {
    	this.id = id;
        this.name = name;
        this.department = department;
        this.status = status;
        
        allTeachers.add(this);        
    }

    public int getId() {
        return id;
    }
    
    public static int getGlobalId() {
    	int global_id = 1;
    	try (Scanner reader = new Scanner(new File("teacherId.txt"))) {
            String line = reader.nextLine(); // Skip header row
            if (line == null || line.equals("")) {
                throw new IOException("File not found");
            }
            else {
            	global_id = Integer.parseInt(line);
            }
    	}
    	catch(IOException e) {
    		System.out.println(e);
    	}
    	return global_id;
    }
    
    public static void setGlobalId() {
    	try (BufferedWriter writer = new BufferedWriter(new FileWriter("teacherId.txt"))) {
            writer.write(""+idCount); // Header row
        }
        catch (IOException e){
        	System.out.println(e);
        }
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
    
    public static int partTimeCount() {
    	int count = 0;
    	for (Teacher teacher : allTeachers) {
            count = teacher.getStatus().equals("Part Time") ? count+1 : count;
        }
    	
    	return count;
    }
    
    public static int fullTimeCount() {
    	int count = allTeachers.size() - partTimeCount();
    	return count;
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
                Teacher teacher = new Teacher(id, name, department, status);
                teacher.id = id; // Set the ID explicitly
            }
        }
    }


}