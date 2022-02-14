import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class MainProgram {
		private static ArrayList <Course> courses = new ArrayList<Course>();
		private static ArrayList <Student> students = new ArrayList<Student>(); 
		private static User user = new User(); 
	
		public static void main (String[]args) throws IOException {
			Scanner input = new Scanner(System.in); 
			
			File tester1= new File("course.ser");
			File tester2= new File("student.ser");
			//if tester1 exists then tester2 exists
			if (tester1.exists()) {
				//deserialization
				deserialize(); 
				System.out.println("Deserialization complete! "); 
			}
			else {
				//read file 
				readFile(); 		
			}
			
			//show welcome message + ask for user identity 
			System.out.println("Welcome to the Course Registration System! "); 
			System.out.println("");
			System.out.println("Enter 'a' if you are an Admin and 's' if you are a Student: "); 
			String identity = input.nextLine(); 
			
			while ((!identity.equals("a")) && (!identity.equals("s"))) {
				System.out.println("Invalid input, try again! Enter 'a' for Admin and 's' for Student: "); 
				String identity2 = input.nextLine(); 
				identity = identity2; 
			}
			
			//ADMIN
			if (identity.equals("a")) { 
				System.out.println("You chose admin."); 
				//ask for username 
				System.out.println("Enter your username: "); 
				String username = input.nextLine(); 
				while (!username.equals("Admin")) {
					System.out.println("Invalid username, try again!"); 
					System.out.println("Enter your username: "); 
					String username1 = input.nextLine(); 
					username = username1; 
				}
				//ask for password 
				System.out.println("Enter your password: "); 
				String password = input.nextLine(); 
				while (!password.equals("Admin001")) {
					System.out.println("Invalid password, try again!"); 
					System.out.println("Enter your password: "); 
					String password1 = input.nextLine(); 
					password = password1; 
				}
				
				//create Admin object
				user = new Admin();
				
				//admin successfully logged in
				System.out.println("");
				System.out.println("Congrats, you have successfully logged in as an Admin!"); 
				System.out.println("What action would you like to perform? "); 
				System.out.println("Enter 'c' for Course Management and 'r' for Reports: "); 
				String adminAction = input.nextLine(); 
			
				while ((!adminAction.equals("c")) && (!adminAction.equals("r"))) {
					System.out.println("Invalid input, try again! Enter 'c' for Course Management and 'r' for Reports: "); 
					String adminAction2 = input.nextLine(); 
					adminAction = adminAction2; 
				}
				
				if (adminAction.equals("c")) {
					boolean flag = true; 
					while (flag) {
						System.out.println("");
						((Admin) user).viewCourseManagement();
						System.out.println("Enter the number for the action you want to perform: "); 
						int choice = Integer.parseInt(input.nextLine()); 
						
						switch (choice) {
						case 1: 
							//create new course
							((Admin) user).createCourse(courses); 
							break; 
							
						case 2: 
							//delete course
							((Admin) user).deleteCourse(courses, students); 	
							break; 
							
						case 3: 
							//edit course
							((Admin) user).editCourse(courses);
							break; 
							
						case 4: 
							//display information for a given course 
							((Admin) user).displayInfo(courses);
							break; 
							
						case 5: 
							//register a student
							((Admin) user).registerStudent(courses, students);
							break; 
							
						case 6: 
							//break while loop, exit 
							flag = false; 
							serialize();
							System.out.println("Changes has been saved! "); 
							System.out.println("Exiting program...");
							System.exit(0);
							break; 
							
						default: 
							System.out.println("");
							System.out.println("Invalid selection number, try again! "); 		
						}
					}	
				}
				
				else if (adminAction.equals("r")) {
					boolean flag = true; 
					while (flag) {
						System.out.println("");
						((Admin)user).viewReports(); 
						System.out.println("Enter the number for the action you want to perform: "); 
						int choice1 = Integer.parseInt(input.nextLine()); 
					
						switch (choice1) {
						case 1: 
							//view all courses
							((Admin) user).viewAll(courses);
							break; 
							
						case 2: 
							//view all courses that are full
							((Admin) user).viewFull(courses);
							break; 
							
						case 3: 
							//write to a file of courses that are full
							((Admin) user).writeFull(courses);	
							break; 
							
						case 4: 
							//view the names of students being registered in a specific course
							((Admin) user).viewRegisteredStudents(courses);	
							break; 
							
						case 5: 
							//view list of courses that a given student is being registered on
							((Admin) user).viewRegisteredCourses();
							break; 
							
						case 6: 
							//sort courses based on the current number of student registered 
							((Admin) user).sortCourses(courses);
							break; 
							
						case 7: 
							//break while loop, exit 
							flag = false; 
							serialize();
							System.out.println("Changes has been saved! "); 
							System.out.println("Exiting program...");
							System.exit(0);
							break; 
						default: 
							System.out.println("");
							System.out.println("Invalid selection number, try again! "); 		
						}			
					}
				}
			}
				
			
			//STUDENT
			else if (identity.equals("s")) {
				System.out.println("You chose student."); 
				//ask for username 
				System.out.println("Enter your username: "); 
				String username = input.nextLine(); 
				//ask for password 
				System.out.println("Enter your password: "); 
				String pw = input.nextLine(); 
				//enter name
				System.out.println("Enter your first name: ");
				String fname = input.nextLine(); 
				System.out.println("Enter your last name: ");
				String lname = input.nextLine(); 
		
				//create Student object
				user = new Student(username, pw, fname, lname); 
				
				//student successfully logged in 
				System.out.println("");
				System.out.println("Congrats, you have successfully logged in as a Student!");
				System.out.println("Welcome! What action would you like to perform? "); 
				
				boolean flag = true; 
				while (flag) {
					System.out.println("");
					((Student) user).viewCourseManagement();
					System.out.println("Enter the number for the action you want to perform: "); 
					int choice2 = Integer.parseInt(input.nextLine()); 
					
					switch (choice2) {
					case 1: 
						//view all courses
						((Student) user).viewAll(courses);
						break; 
					case 2: 
						//view all courses that are not full
						((Student) user).viewAvailable(courses);
						break; 
						
					case 3: 
						//register on a course
						((Student) user).registerStudent(courses, (Student) user);
						break; 
						
					case 4: 
						//withdraw from a course
						((Student) user).withdraw(courses, (Student) user);
						break; 
						
					case 5: 
						//view all courses that the current student is being registered in
						((Student) user).viewRegisteredCourses((Student) user);
						break; 
						
					case 6: 
						//break while loop, exit	
						flag = false; 
						serialize();
						System.out.println("Changes has been saved! "); 
						System.out.println("Exiting program...");
						System.exit(0);
						break; 
			
					default: 
						System.out.println("");
						System.out.println("Invalid selection number, try again! "); 	
					}	
				}
			}
		input.close();
	}

	public static void deserialize() {
		try {
			FileInputStream f1 = new FileInputStream("course.ser"); 
			FileInputStream f2= new FileInputStream("student.ser");
			ObjectInputStream in1 = new ObjectInputStream(f1); 
			ObjectInputStream in2 = new ObjectInputStream(f2); 
			courses = (ArrayList<Course>) in1.readObject(); 
			students = (ArrayList<Student>)in2.readObject(); 
			in1.close(); 
			f1.close(); 
			in2.close(); 
			f2.close(); 
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return; 
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace(); 
			return; 
		}
	}
	
	public static void serialize() {
		//serialization
			try {
				FileOutputStream fos1 = new FileOutputStream("course.ser"); 
				FileOutputStream fos2 = new FileOutputStream("student.ser");
				ObjectOutputStream out1 = new ObjectOutputStream(fos1); 
				ObjectOutputStream out2 = new ObjectOutputStream(fos2); 
				out1.writeObject(courses);
				out2.writeObject(students);
				out1.close();
				out2.close(); 
				fos1.close();
				fos2.close();
				System.out.println("Information has been updated! "); 
			} catch (IOException ioe) {
				ioe.printStackTrace();
				return;
			}
	}
	
	//read file + store course objects into an ArrayList
	public static void readFile() throws IOException { 
		File file = new File ("MyUniversityCourses.csv"); 
				
		//create a BufferedReader for the file
		BufferedReader br = new BufferedReader (new FileReader(file)); 
			
		//skip first line of the csv file - header
		br.readLine(); 
		
		//read data from file
		String line=""; 
				
		while ((line=br.readLine()) != null) {			
			String[] contents = line.split(","); 
				
			Course newCourse = new Course (contents[0], contents[1], Integer.parseInt(contents[2]), 
					Integer.parseInt(contents[3]), contents[5], Integer.parseInt(contents[6]), contents[7]); 
						
			courses.add(newCourse); 
		}	
		br.close(); 
	}
	
	

	
	
	
	
		
		
		
		
		
		
		
		
}


