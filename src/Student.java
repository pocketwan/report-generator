import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private String studentNumber;
    private int yearOfStudy;
    private String degreeName;
    private String facultyName;
    private List<Course> courses; // Store a list of courses the student
    private List<Result> results; // List of results for each course

    public Student(String name, String studentNumber, String degreeName, String facultyName, int yearOfStudy, List<Course> courses) {
        this.name = name;
        this.studentNumber = studentNumber;
        this.degreeName = degreeName;
        this.facultyName = facultyName;
        this.yearOfStudy = yearOfStudy;
        this.courses = courses;
    }

    public String getName() {
        return name;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public String getDegreeName() {
        return degreeName;
    }
    
    public String getFacultyName() {
        return facultyName;
    }
    
    public int getYearOfStudy() {
        return yearOfStudy;
    }

    public List<Course> getCourses() {
        return courses;
    }
    
    public List<Result> getResults() {
        return results;
    }

    public void addResults(List<Result> results) {
        this.results = results;
    }
   
}
