package application;

import java.util.ArrayList;

public class Teacher {
    private final int id;
    private final String name;
    private final String department;
    private final String status;
    private static final ArrayList<Teacher> allTeachers = new ArrayList<>();
    private static int idCount = 1;


    public Teacher(String name, String department, String status) {
    	this.id = idCount;
        this.name = name;
        this.department = department;
        this.status = status;
        
        idCount += 1;
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
        return names;    }
}