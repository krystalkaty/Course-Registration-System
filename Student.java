import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Student extends User implements StudentInterface, Serializable{ 
	transient Scanner input = new Scanner(System.in); 
	
	private static final long serialVersionUID= 1L; 	

	private ArrayList <Course> enrolledCourses = new ArrayList <Course>(); 

	public Student () {
		super(); 
	}

	
	public Student (String username, String password, String firstName, String lastName) {
		super(username, password, firstName, lastName); 
	}
	

	public ArrayList<Course> getEnrolledCourses() {
		return enrolledCourses;
	}


	public void setEnrolledCourses(ArrayList<Course> enrolledCourses) {
		this.enrolledCourses = enrolledCourses;
	}

	public void viewCourseManagement() {
		System.out.println("Course Management Actions: "); 
		System.out.println("1 - View all courses "); 
		System.out.println("2 - View all courses that are not full"); 
		System.out.println("3 - Register on a course "); 
		System.out.println("4 - Withdraw from a course "); 
		System.out.println("5 - View all courses that the current student is being registered in "); 
		System.out.println("6 - Exit "); 
	}
		
	public void viewAvailable(ArrayList <Course> courses) {
		//view all courses that are not full 
		System.out.println("List of courses that are not full: "); 
		for (Course c: courses) {
			if (c.getCurrentStudents() < c.getMaxStudents()) {
				System.out.println(c.getName() + " " + c.getSection()); 
			}
		}
	}
	
	public void registerStudent(ArrayList<Course> courses, Student student) {	
		//student enter course name, section, student full name 
		//name will be added into the appropriate course 
		//student name already provided in main program - login  

		boolean flag = false; 
		
		while (!flag) {
			System.out.println("Enter the course name you want to register to: "); 
			String cname = input.nextLine(); 
			System.out.println("Enter the course section number: "); 
			int sec = Integer.parseInt(input.nextLine()); 
			
			Course a= new Course(); 
			a = findCourse(courses, cname, sec); 
			
			if (a != null) { 
				//add student into enrolled course list 
				super.registerStudent(a, cname, sec, student);
				flag = true; 
			}
			
			else {
				System.out.println("Course not found. Try again! "); 
			}
		}
	}
	
	public void withdraw(ArrayList <Course> courses, Student student) {
		//student enter course name, section
		//name will be removed from course's enrolled student list
		//student name already provided in main program - login  
		boolean flag = false; 
		while (! flag) {
			System.out.println("Enter the name of the course you want to withdraw from: "); 
			String cname = input.nextLine(); 
			System.out.println("Enter the course section number: "); 
			int sec = Integer.parseInt(input.nextLine()); 
			
			if (!(student.getEnrolledCourses().isEmpty())) {
				for (Course c: student.getEnrolledCourses()) {
					c = findCourse(courses, cname, sec); 
					if (c!= null) {
						student.getEnrolledCourses().remove(c);		//remove course from student's enrolled course list
						c.getEnrolledStudents().remove(student);	//remove student from course's enrolled student list 
						System.out.println("Successfully withdrawn from course. ");
						flag = true; 
					}
					else {
						System.out.println("Invalid input, you are not enrolled in this course! "); 
					}
				}
			}
			else {
				System.out.println("Course not found. Try again! "); 
			}
		}
	}	
	
	public Course findCourse(ArrayList <Course> courses, String cname, int section) {
		for (Course a: courses) {
			if ((a.getName().contentEquals(cname)) && (a.getSection()==section)) {
				return a; 
			}
		}
		return null; 
	}
}



