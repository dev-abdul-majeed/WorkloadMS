package application;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Workload {
    // Instance variables
    private int id;
    private int teacherId;
    private String teacher_name;
    private String type;
    private String activity;
    private String description;
    private String year;
    private double activityDuration;
    private double instances;
    private double atsr= 0.0;
    private double ts=0.0;
    private double tlr=0.0;
    private double sa=0.0;
    private double other=0.0;

    // Static variable for unique ID generation
    private static int idCounter = 1;

    // ArrayList to store Workload objects
    private static final ArrayList<Workload> workloads = new ArrayList<>();
    private static final ArrayList<String> types = new ArrayList<>(Arrays.asList("ATSR", "TLR", "SA", "Other"));
    private static final ArrayList<String> activities = new ArrayList<>(Arrays.asList("Formal Scheduled Teaching", "Dissertation/Projects", "Module Leader", "Other duties - specify", "Scholarly, Currency & Development", "Programme Leader", "Deputy Head of Subject", "T&R Contract"));
    private static final ArrayList<String> years = new ArrayList<>(Arrays.asList("Trimester 1", "Trimester 2", "Trimester 3", "All Year"));


    // Constructor
    public Workload(int teacherId, String type, String activity, String description, String year, double activityDuration, double instances) {
        this.id = idCounter++;
        this.teacherId = teacherId;
        this.teacher_name = Teacher.getNameById(teacherId);
        this.type = type;
        this.activity = activity;
        this.description = description;
        this.year = year;
        this.activityDuration = activityDuration;
        this.instances = instances;
        setHours(type, activityDuration, instances);
        
        workloads.add(this);
        printWorkloads();
    }
    
    public Workload(int id, int teacherId, String teacher_name, String type, String activity, String description, String year, double activityDuration, double instances, double atsr, double ts, double tlr, double sa, double other) {
    	this.id = id;
    	this.teacherId = teacherId;
    	this.teacher_name = teacher_name;
    	this.type = type;
    	this.activity = activity;
    	this.description = description;
    	this.year = year;
    	this.activityDuration = activityDuration;
    	this.instances = instances;
    	this.atsr = atsr;
    	this.ts = ts;
    	this.tlr = tlr;
    	this.sa = sa;
    	this.other = other;
        
        workloads.add(this);
        printWorkloads();
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public String getTeacherName() {
        return teacher_name;
    }

    public void setTeacherName(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public double getActivityDuration() {
        return activityDuration;
    }

    public void setActivityDuration(double activityDuration) {
        this.activityDuration = activityDuration;
    }

    public double getInstances() {
        return instances;
    }

    public void setInstances(double instances) {
        this.instances = instances;
    }

    public void setHours(String type, double duration, double instances) {
//    	"ATSR", "TLR", "SA", "Other"
    	double hours = duration * instances;
    	switch (type){
    		case "ATSR":
    			this.atsr = hours;
    			this.ts = hours * 1.2;
    			
    			this.tlr = 0;
    			this.sa = 0;
    			this.other = 0;
    			break;
    		case "TLR":
    			this.tlr = hours;
    			
    			this.atsr = 0;
    			this.ts = 0;
    			this.sa = 0;
    			this.other = 0;
    			break;
    			
    		case "SA":
    			this.sa = hours;
    			
    			this.atsr = 0;
    			this.ts = 0;
    			this.tlr = 0;
    			this.other = 0;
    			break;
    			
    		case "Other":
    			this.other = hours;
    			
    			this.atsr = 0;
    			this.ts = 0;
    			this.tlr = 0;
    			this.sa = 0;
    			break;
    	}
    }

    public double getAtsr() {
        return atsr;
    }

    public void setAtsr(int atsr) {
        this.atsr = atsr;
    }

    public double getTs() {
        return ts;
    }

    public void setTs(int ts) {
        this.ts = ts;
    }

    public double getTlr() {
        return tlr;
    }

    public void setTlr(int tlr) {
        this.tlr = tlr;
    }

    public double getSa() {
        return sa;
    }

    public void setSa(int sa) {
        this.sa = sa;
    }

    public double getOther() {
        return other;
    }

    public void setOther(int other) {
        this.other = other;
    }

    // Static method to get all Workload objects
    public static ArrayList<Workload> getWorkloads() {
        return workloads;
    }
    
    public static ArrayList<String> getTypes() {
        return types;    
    }
    
    public static ArrayList<String> getActivities() {
        return activities;    
    }
    
    public static ArrayList<String> getYears() {
        return years;    
    }
    
    public static void exportWorkloadToCSV(String filePath) throws IOException {
    	filePath = (filePath.isEmpty() ? "workloads.csv" : filePath);
    	try (FileWriter writer = new FileWriter(filePath)) {
            // Write the header
            writer.append("ID,Teacher ID,Teacher Name,Type,Activity,Description,Year,Activity Duration,Instances,ATSR,TS,TLR,SA,Other\n");

            // Write the data
            for (Workload w : workloads) {
	            writer.append(String.format("%d,%d,%s,%s,%s,%s,%s,%.2f,%.2f,%.2f,%.2f,%.2f,%.2f,%.2f\n",
	                    w.id, w.teacherId, w.teacher_name, w.type, w.activity, w.description, w.year, 
	                    w.activityDuration, w.instances, w.atsr, w.ts, w.tlr, w.sa, w.other));

	            System.out.println("Data exported successfully to " + filePath);
            }
        } catch (IOException e) {
            System.err.println("An error occurred while exporting to CSV: " + e.getMessage());
        }
    }
    
    public static void importWorkloadFromCSV(String filePath) throws IOException {
        try (Scanner reader = new Scanner(new File("workloads.csv"))) {
            String line = reader.nextLine(); // Skip header row
            if (line == null || !line.equals("ID,Teacher ID,Teacher Name,Type,Activity,Description,Year,Activity Duration,Instances,ATSR,TS,TLR,SA,Other")) {
                throw new IOException("Invalid line CSV format.");
            }

            workloads.clear(); // Clear current data
            int maxId = 0; // To track the greatest ID

            while (reader.hasNextLine()) {
            	line = reader.nextLine();
                String[] fields = line.split(",");
                if (fields.length != 14) {
                    throw new IOException("Invalid data in CSV file.");
                }

                // Create a new workload with the given ID
                int id = Integer.parseInt(fields[0]);
                int teacherId = Integer.parseInt(fields[1]);
                String teacher_name = fields[2];
                String type = fields[3];
                String activity = fields[4];
                String description = fields[5];
                String year = fields[6];
                double activityDuration = Double.parseDouble(fields[7]);
                double instances = Double.parseDouble(fields[8]);
                double atsr = Double.parseDouble(fields[9]);
                double ts = Double.parseDouble(fields[10]);
                double tlr = Double.parseDouble(fields[11]);
                double sa = Double.parseDouble(fields[12]);
                double other = Double.parseDouble(fields[13]);
                
                new Workload( id, teacherId, teacher_name, type, activity, description, year, activityDuration, instances, atsr, ts, tlr, sa, other);
                 // Set the ID explicitly
                maxId = Math.max(maxId, id); // Update the maximum ID
            }

            // Update idCount to continue from the highest ID + 1
            idCounter = maxId + 1;
        }
    }
    // Static method to print all Workload objects
    public static void printWorkloads() {
        for (Workload workload : workloads) {
            System.out.println("ID: " + workload.getId() + ", Teacher ID: " + workload.getTeacherId() + ", Type: " + workload.getType() + ", Activity: " + workload.getActivity() + ", Description: " + workload.getDescription() + ", Name: " + workload.getTeacherId() + ", Year: " + workload.getYear() + ", Activity Duration: " + workload.getActivityDuration() + ", Instances: " + workload.getInstances() + ", ATSR: " + workload.getAtsr() + ", TS: " + workload.getTs() + ", TLR: " + workload.getTlr() + ", SA: " + workload.getSa() + ", Other: " + workload.getOther());
        }
    }
    
    public static String validateWorkload(String teacher, String type, String activity, String description, String year, String activityDuration, String instances) {
    	String error = "";
    	if (teacher == null || teacher.isBlank()){
    		error += "Please select a teacher \n";
    	}
    	if (type == null) {
    		error += "Please select a type \n";
    	}
    	if (activity == null) {
            error += "Please provide an activity \n";
        }
        if (description.isBlank()) {
            error += "Please provide a description \n";
        }
        if (year == null) {
            error += "Please provide a valid year time \n";
        }
        if (activityDuration.isBlank()) {
    		error += "Please provide an activity duration. \n";
        }
        else {
        	try {
        		Double.parseDouble(activityDuration);
        	}
        	catch(NumberFormatException e){
        		error += "Please provide a numeric activity duration. \n";
        	}
        }
        if (instances.isBlank()) {
            error += "Please provide a valid number of instances. \n";
        }
        else {
        	try {
        		Double.parseDouble(instances);
        	}
        	catch(NumberFormatException e){
        		error += "Please provide a numeric instance count. \n";
        	}
        }
    	return error;
    }
}
