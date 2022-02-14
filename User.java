import java.util.ArrayList;

public class User{
	protected String username; 
	protected String password; 
	protected String firstName; 
	protected String lastName; 

	//constructors 
	public User() {
	}
	
	public User (String username, String password, String firstName, String lastName) {
		this.username= username; 
		this.password= password; 
		this.firstName= firstName; 
		this.lastName= lastName; 
	}
	
	//getters and setters 
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	
	public void registerStudent(Course c, String cname, int sec, Student newstudent) throws NullPointerException {	
		//register student onto course
		if ((c.getName().equals(cname) && (c.getSection()==sec))) {	
			if (c.getCurrentStudents() < c.getMaxStudents()) {					//check if course is full	
				if (! (c.getEnrolledStudents().contains(newstudent))) {			//check if student already registered 			
					//add student into enrolled course list, update course info
					c.addStudent(newstudent);  
					//add course into student's registered course list
					newstudent.getEnrolledCourses().add(c);		
					System.out.println(newstudent.getFirstName() + " " + newstudent.getLastName() + " have successfuly been registered. "); 
				}
			}
		}
	}			
			
	public void viewAll(ArrayList <Course> courses)  {
		for (Course a: courses) {
			System.out.println(a.getName()); 
			System.out.println("- Course ID: " + a.getCourseID()); 
			System.out.println("- Course Section " + a.getSection()); 
			System.out.println("- Course Instructor: " + a.getInstructor());
			System.out.println("- COurse Location: " + a.getLocation());
			System.out.println(""); 
		}
	}	

	public void viewRegisteredCourses(Student student) {									
		//view all courses that the current student is being registered in 
		if (student.getEnrolledCourses().isEmpty()) {
			System.out.println(student.getFirstName() + " " + student.getLastName() +
					" is not registered in any courses "); 
		}
		else {
			System.out.println("This is the list of courses " + student.getFirstName() + " "
					+ student.getLastName() + " is being registered in: "); 
			for (Course c: student.getEnrolledCourses()) {
				System.out.println("- " + c.getName() + " " + c.getSection());
			}
		}
	}

	public Course findCourse(ArrayList <Course> courses, String courseID, int section) {
		for (Course a: courses) {
			if ((a.getCourseID().contentEquals(courseID)) && (a.getSection()==section)) {
				return a; 
			}
		}
		return null; 
	}
	
}


