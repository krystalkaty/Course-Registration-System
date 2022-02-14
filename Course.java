import java.io.Serializable;
import java.util.ArrayList;

public class Course implements Serializable, Comparable<Course> {
	//data fields
	private static final long serialVersionUID= 1L; 	
	
	private String name; 
	private String courseID; 	
	private int maxStudents; 
	//assume current number of students registered in the course will always start from 0 
	private int currentStudents=0;
	private ArrayList <Student> enrolledStudents= new ArrayList <Student>(); 
	private String instructor; 
	private int section; 
	private String location; 

	//constructors 
	public Course() {}   
		
	public Course(String name, String courseID, int maxStudents, int currentStudents,
			String instructor, int section, String location) {
		this.name= name; 
		this.courseID= courseID; 
		this.maxStudents = maxStudents; 
		this.currentStudents = currentStudents; 
		this.instructor = instructor; 
		this.section = section; 
		this.location = location; 
	}
			
	//getters + setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCourseID() {
		return courseID;
	}

	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	public int getMaxStudents() {
		return maxStudents;
	}

	public void setMaxStudents(int maxStudents) {
		this.maxStudents = maxStudents;
	}

	public int getCurrentStudents() {
		return currentStudents;
	}

	public void setCurrentStudents(int currentStudents) {
		this.currentStudents = currentStudents;
	}

	public ArrayList<Student> getEnrolledStudents() {
		return enrolledStudents;
	}

	public void setEnrolledStudents(ArrayList<Student> enrolledStudents) {
		this.enrolledStudents = enrolledStudents;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public int getSection() {
		return section;
	}

	public void setSection(int section) {
		this.section = section;
	}

	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public void addStudent(Student student) {
		this.enrolledStudents.add(student); 
		this.currentStudents++; 
	}

	@Override
	public int compareTo(Course course) {
		return this.getCurrentStudents() - course.getCurrentStudents(); 
	}
		
	
	
}



