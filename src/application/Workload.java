package application;
import java.util.ArrayList;
import java.util.Arrays;

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
    private double hours;
    private double atsr;
    private double ts;
    private double tlr;
    private double sa;
    private double other;

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
        this.hours = activityDuration * instances;
        this.atsr = instances * hours;
        this.ts = (instances*hours)*1.2;
        this.tlr = instances * hours;
        this.sa = instances * hours;
        this.other = 0;
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

    public void setActivityDuration(int activityDuration) {
        this.activityDuration = activityDuration;
    }

    public double getInstances() {
        return instances;
    }

    public void setInstances(int instances) {
        this.instances = instances;
    }

    public double getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
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
    
    // Static method to print all Workload objects
    public static void printWorkloads() {
        for (Workload workload : workloads) {
            System.out.println("ID: " + workload.getId() + ", Teacher ID: " + workload.getTeacherId() + ", Type: " + workload.getType() + ", Activity: " + workload.getActivity() + ", Description: " + workload.getDescription() + ", Name: " + workload.getTeacherId() + ", Year: " + workload.getYear() + ", Activity Duration: " + workload.getActivityDuration() + ", Instances: " + workload.getInstances() + ", Hours: " + workload.getHours() + ", ATSR: " + workload.getAtsr() + ", TS: " + workload.getTs() + ", TLR: " + workload.getTlr() + ", SA: " + workload.getSa() + ", Other: " + workload.getOther());
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
