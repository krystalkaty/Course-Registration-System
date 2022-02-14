import java.util.ArrayList;

public interface StudentInterface {

	void viewCourseManagement(); 
	void viewAvailable(ArrayList <Course> course); 
	void registerStudent(ArrayList<Course> courses, Student student); 
	void withdraw(ArrayList <Course> courses, Student student); 
	Course findCourse(ArrayList <Course> courses, String cname, int section);
}
