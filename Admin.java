import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Admin extends User implements AdminInterface{
	Scanner input = new Scanner(System.in); 
	
	//constructor
	public Admin() {
		super(); 	
	}
	
	public Admin(String username, String password) {
		this.username= "Admin"; 
		this.password= "Admin001";  
	}
	
	public void viewCourseManagement() {
		System.out.println("Course Management Actions: "); 
		System.out.println("1 - Create a new course "); 
		System.out.println("2 - Delete a course "); 
		System.out.println("3 - Edit a course "); 
		System.out.println("4 - Display information for a given course "); 
		System.out.println("5 - Register a student "); 
		System.out.println("6 - Exit "); 	
	}
	
	public void viewReports() {
		System.out.println("Report Actions: "); 
		System.out.println("1 - View all courses ");
		System.out.println("2 - View all courses that are full "); 
		System.out.println("3 - Write to a file the list of courses that are full "); 
		System.out.println("4 - View the names of the students being registered in a specific course "); 
		System.out.println("5 - View the list of courses that a given student is being registered on "); 
		System.out.println("6 - Sort courses based on the current number of student registered "); 
		System.out.println("7 - Exit "); 
	}
	
	//course management 
	public Course createCourse(ArrayList<Course> courses) {
		//assume valid input 
		System.out.println("Enter course name: "); 
		String name = input.nextLine(); 
		System.out.println("Enter course ID: "); 
		String id = input.nextLine();
		System.out.println("Enter maximum number of students: "); 
		int max = Integer.parseInt(input.nextLine()); 
		System.out.println("Enter current number of students: "); 
		int current = Integer.parseInt(input.nextLine()); 
		System.out.println("Enter course instructor name: "); 
		String instructor = input.nextLine(); 
		System.out.println("Enter course section number: "); 
		int sec= Integer.parseInt(input.nextLine()); 
		System.out.println("Enter course location: "); 
		String location = input.nextLine(); 
		
		Course c = new Course (name, id, max, current, instructor, sec, location); 
		courses.add(c); 
		
		System.out.println(""); 
		System.out.println("Successfully created new course! "); 
		return c;
	}

	
	public void deleteCourse(ArrayList <Course> courses, ArrayList <Student> students) {
		//delete course by course ID + section number to identify unique row 		
		//use while loop - keep prompting if course not found
		boolean flag = false; 
		
		while (!flag) {
			System.out.println("Enter the course ID to be deleted: "); 
			String courseID = input.nextLine(); 
			System.out.println("Enter the course section to be deleted: ");
			int section = Integer.parseInt(input.nextLine()); 
			
			Course a= new Course(); 
			a = findCourse(courses, courseID, section); 
			
			if (a != null) {
				courses.remove(a); 	//remove course from courses list
				System.out.println(a.getCourseID() + " Section " + a.getSection() + " has been removed!"); 
				for (Student s: students) {
					if (s.getEnrolledCourses().contains(a)) {
						s.withdraw(courses, s); 	//withdraw student 
					}
				}
				flag = true; 
			}
			else {
				System.out.println("Course not found. Try again! "); 
			}
		}
	}
	
	public void editCourse(ArrayList <Course> courses) {
		//allow admin to edit any information on the course except the course ID and name
		//ask for name + course section to identify unique row

		boolean flag = false; 
		
		Course a= new Course(); 
		while (!flag) {
			System.out.println("Enter the course ID you want to edit: "); 
			String courseID = input.nextLine();
			System.out.println("Enter the course section you want to edit: "); 
			int section = Integer.parseInt(input.nextLine());  
			
			Course c= new Course(); 
			c = findCourse(courses, courseID, section); 
	
			if (c != null) {
				a= c; 
				flag= true; 
			}
			else {
				System.out.println("Course not found. Try again! "); 
			}
		}
		
		System.out.println("What information would you like to edit?"); 
		System.out.println(" 1- Maximum Number of Students Registered in Course"); 
		System.out.println(" 2- Current Number of Studnets"); 
		System.out.println(" 3- Lists of Students"); 
		System.out.println(" 4- Course Instructor"); 
		System.out.println(" 5- Course Section Number"); 
		System.out.println(" 6- Course Location"); 
		
		int selection = Integer.parseInt(input.nextLine()); 
			
		switch (selection) {
			case 1: 
				//change max number of students registered in course
				System.out.println("Current maximum number of students registered in course: "+ a.getMaxStudents());
				System.out.println("Enter the new maximum number of students registered in course: "); 
				int max = Integer.parseInt(input.nextLine()); 
				a.setMaxStudents(max); 
				System.out.println(""); 
				System.out.println("Successfully changed the maximum number of students registered in course to " + a.getMaxStudents()); 
				break; 
				
			case 2: 
				//change current number of students
				System.out.println("Current number of students registered in course: "+ a.getCurrentStudents());
				System.out.println("Enter the new current number of students registered in course: "); 
				int current = Integer.parseInt(input.nextLine()); 
				a.setCurrentStudents(current); 
				System.out.println(""); 
				System.out.println("Successfully changed the current number of students registered in course to " + a.getCurrentStudents()); 
				break; 
				
			case 3: 
				//change list of enrolled students in course 
				//allow admin to remove students from the course
					//to add students, admin can use registerStudent method
				System.out.println("Current enrolled students: ");
				if (a.getEnrolledStudents() != null) {
					for (Student stu: a.getEnrolledStudents()) {
						System.out.println(stu.getFirstName() + " " + stu.getLastName()); 
					}
				}
				else {
					System.out.println("No current enrolled students "); 
				}
				
				System.out.println(); 
				
				System.out.println("Change lists of students by removing student"); 
				System.out.println("Enter the student's first name: ");
				String fname = input.nextLine(); 
				System.out.println("Enter the student's last name: ");
				String lname = input.nextLine(); 
			
				for (Student s: a.getEnrolledStudents()) {
					if ((s.getFirstName().contentEquals(fname)) && (s.getLastName().contentEquals(lname))) {
						a.getEnrolledStudents().remove(s); 	//remove student from list of enrolled students
						a.setCurrentStudents((a.getCurrentStudents()-1)); //update current number of students in course
						s.getEnrolledCourses().remove(a); 	//remove course from student's list of enrolled courses
					}
				}
				System.out.println(); 
				System.out.println("Successfully removed the student from " + a.getName()); 
				break; 
			
			case 4: 
				//change course instructor
				System.out.println("Current course instructor: " + a.getInstructor()); 
				System.out.println("Enter the new course instructor: "); 
				String prof = input.nextLine(); 
				a.setInstructor(prof);
				System.out.println(""); 
				System.out.println("Successfully changed the course instructor to " + a.getInstructor()); 
				break; 
		
			case 5: 
				//change course section number
				System.out.println("Current course section number: " + a.getSection()); 
				System.out.println("Enter the new course section number: "); 
				int num = Integer.parseInt(input.nextLine()); 
				a.setSection(num);
				System.out.println(""); 
				System.out.println("Successfully changed the course section number to " + a.getSection()); 
				break; 
		
			case 6: 
				//change course location
				System.out.println("Current course location " + a.getLocation()); 
				System.out.println("Enter the new course location: "); 
				String loc = input.nextLine(); 
				a.setLocation(loc);
				System.out.println(""); 
				System.out.println("Successfully changed the course location to " + a.getLocation()); 
				break; 
				
			default: 
				System.out.println("Selection code is invalid! "); 
		}		
	}

	public void displayInfo(ArrayList <Course> courses) { 	
		boolean flag = false; 
		Course c= new Course(); 
		
		while (!flag) {
			//by course ID 
			System.out.println("Enter the Course ID for the course you want to display information for: "); 
			String courseID = input.nextLine(); 
			//ask for section number to identify unique row, as there could be multiple classes with the same course 
			System.out.println("Enter the course section number for the course: "); 
			int section = Integer.parseInt(input.nextLine()); 
			 
			c = findCourse(courses, courseID, section); 
			
			if (c != null) {
				System.out.println("Information for " + c.getCourseID()); 
				System.out.println("Course name: "+ c.getName()); 
				System.out.println("Course Instructor: " + c.getInstructor()); 
				System.out.println("Course Section Number: " + c.getSection()); 
				System.out.println("Course Location: " + c.getLocation()); 
				System.out.println("Maximum Number Of Students Registered in Course: " + c.getMaxStudents()); 
				System.out.println("Current Number of Students: " + c.getCurrentStudents()); 
				System.out.println("Student Names: "); 
				if (c.getEnrolledStudents().isEmpty()) {
					System.out.println("No students"); 
				}
				else {
					for (Student s: c.getEnrolledStudents()) {
						System.out.println(s.getFirstName() + " " + s.getLastName());
					}
				}
				flag = true; 
			}
			
			else {
				System.out.println("Course not found. Try again! "); 
			}
		}
	
	}
	
	public void registerStudent(ArrayList<Course> courses, ArrayList <Student> students) {									
		//allow admin to add a student without assigning to a course, check Req 11 for student's information 
		//register student into student ArrayList OR register student into enrolledStudents
		
		//ask for  student's first name + last name
		Random rand = new Random();
		System.out.println("Enter the new student's first name: ");
		String fname = input.nextLine(); 
		System.out.println("Enter the new student's last name: ");
		String lname = input.nextLine(); 
		
		//generate random username + password for students
		String user = fname + rand.nextInt(1000);
		String pw = lname + rand.nextInt(1000); 
		
		Student newstudent = new Student (user, pw, fname, lname); 
		
		System.out.println("Enter 's' to register student into system or 'c' to register student onto course: "); 
		String choice= input.nextLine(); 
		
		while ((!choice.equals("s")) && (!choice.equals("c"))) {
			System.out.println("Invalid input, try again! Enter 's' to register student into system or 'c' to register student onto course: ");
			String choice2 = input.nextLine(); 
			choice = choice2; 
		}
	
		if (choice.equals("s")) {
			//add student into student array list		
			if (! students.contains(newstudent)) {
				students.add(newstudent);
				System.out.println("Student registered into system.");
			}
		}
		
		else {
			//register student onto course 	
			boolean flag = false; 
			
			while (!flag) {
				//by course ID 
				System.out.println("Enter the course ID you want to register student to: "); 
				String courseID = input.nextLine(); 
				System.out.println("Enter the course section number: "); 
				int section = Integer.parseInt(input.nextLine()); 
				
				Course a= new Course(); 
				a = findCourse(courses, courseID, section); 
				
				if (a != null) { 
					//add student into enrolled course list 
					super.registerStudent(a, courseID, section, newstudent);
					flag = true; 
				}
				
				else {
					System.out.println("Course not found. Try again! "); 
				}
			}
		}
	}
	
	
	//reports
	public void viewAll(ArrayList <Course> courses) {
		//see list of course name, course id, current number of students registered and max number of students allowed to register
		//overrides User.viewAll
		for (Course a: courses) {
			System.out.println(a.getName()); 
			System.out.println("- Course ID: " + a.getCourseID()); 
			System.out.println("- Current Number of Students Registered: " + a.getCurrentStudents()); 
			System.out.println("- Maximum Number of Students Allowed to Register: " + a.getMaxStudents()); 
			System.out.println(""); 
		}
	}

	public ArrayList <Course> getFull(ArrayList<Course> courses){
		ArrayList<Course> fullcourses = new ArrayList <Course>(); 
		for (Course c: courses) {
			if (c.getCurrentStudents() == c.getMaxStudents()) {
				fullcourses.add(c); 
			}
		}
		return fullcourses; 
	}
	
	public void viewFull (ArrayList<Course> courses){										
		//view all courses that are full - reached the maximum number of students
		//display
		if (getFull(courses).isEmpty()) {
			System.out.println("No courses are full yet"); 
		}
		else {
			for (Course c: getFull(courses)) {
				System.out.println("List of full courses: "); 
				System.out.print("- " + c.getName() + " " + c.getSection());
			}
		}
	}
	
	public void writeFull (ArrayList<Course> courses) throws IOException {				
		//write to a file the list of course that are full 
		String file= "FullCourses.txt"; 
		FileWriter fw = new FileWriter(file); 
		BufferedWriter bw = new BufferedWriter(fw);
		for (Course c: getFull(courses)) {
			bw.write(c.getName() + " " + c.getSection()); 
			bw.newLine(); 
		}
		bw.close(); 
		System.out.println("Successfully wrote to a file the list of courses that are full "); 
	}
	
	public void viewRegisteredStudents(ArrayList<Course> courses) {
		//view all students registered in a specific course
		boolean flag = false; 
		
		while (!flag) {
			System.out.println("Enter the course ID for the course you want to view the list of registered students for: "); 
			String courseID = input.nextLine(); 
			System.out.println("Enter the course section number: "); 
			int section = Integer.parseInt(input.nextLine()); 
			
			Course c= new Course(); 
			c = findCourse(courses, courseID, section); 
			
			if (c!= null) {
				if (c.getEnrolledStudents() != null) {
					System.out.println("Registered Students for " + c.getName()); 
					for (Student s: c.getEnrolledStudents()) {
						System.out.println("- " + s.getFirstName() + " " + s.getLastName()); 
					}
				}
				flag = true; 
			}
			else {
				System.out.println("Course not found. Try again! "); 
			}
		}
	}	
	
	public void viewRegisteredCourses() {								
		///given a student first name + last name, display all courses that student is being registered in 

		//ask for  student's first name + last name
		Random rand = new Random();
		System.out.println("Enter the new student's first name: ");
		String fname = input.nextLine(); 
		System.out.println("Enter the new student's last name: ");
		String lname = input.nextLine(); 
		
		//generate random username + password for students
		String user = fname + rand.nextInt(1000);
		String pw = lname + rand.nextInt(1000); 
		
		Student newstudent = new Student (user, pw, fname, lname); 
		
		super.viewRegisteredCourses(newstudent);
	
	}
	
	
	//sort by current number of student registers and print to screen 
	public void sortCourses(ArrayList <Course> courses) {
		
		ArrayList<Course> list = new ArrayList<Course>(); 
		
		for (Course c: courses) {
			list.add(c); 
		}
		
		Collections.sort(list); 
		
		System.out.println("Courses sorted by current number of student registered: "); 
		for (Course c: list) {
			System.out.println(c.getName() + " " + c.getSection()); 
		}
	}
	
	
}






