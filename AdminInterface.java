import java.io.IOException;
import java.util.ArrayList;

public interface AdminInterface {

	void viewCourseManagement(); 
	void viewReports(); 
	Course createCourse(ArrayList<Course> courses); 
	void deleteCourse(ArrayList <Course> courses, ArrayList <Student> students); 
	void editCourse(ArrayList <Course> courses); 
	void displayInfo(ArrayList <Course> courses); 
	void registerStudent(ArrayList<Course> courses, ArrayList <Student> students); 
	void viewAll(ArrayList <Course> courses); 
	ArrayList <Course> getFull(ArrayList<Course> courses); 
	void viewFull (ArrayList<Course> courses); 
	void writeFull (ArrayList<Course> courses) throws IOException; 
	void viewRegisteredStudents(ArrayList<Course> courses); 
	void viewRegisteredCourses(); 
	void sortCourses(ArrayList <Course> courses); 
	
}
