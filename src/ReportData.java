import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ReportData {

    /* ACADEMIC YEAR DETAILS */
    private String acadYear;

    /* STUDENT DETAILS */
    private String name;
    private String studentNumber;
    private int yearOfStudy;
    private String degreeName;
    private String termGPA;
    private String cumulativeGPA;
    
    /* RESULTS DETAILS */ 
    private String course;  // Concatenated course ID and name
    private int credits;    // Credits from the Course class
    private int mark;    // Mark from the Result class
    private String grade;   // Calculated based on mark
    
    /* DATE FOR STAMP */
    private String date;
 
    public ReportData(String name, String studentNumber, int yearOfStudy, String degreeName, String acadYear) {
        this.name = name;
        this.studentNumber = studentNumber;
        this.yearOfStudy = yearOfStudy;
        this.degreeName = degreeName;
        this.acadYear = acadYear;
    }
    
    public ReportData(String course, int credits, int mark, String acadYear, String termGPA) {
        this.course = course;
        this.credits = credits;
        this.mark = mark;
        this.acadYear = acadYear;
        this.grade = calculateGrade(mark);
        this.termGPA = termGPA;
        cumulativeGPA = this.termGPA; // This can be changed later
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        this.date = today.format(formatter);
    }
        
    public String getName() {
        return name;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public int getYearOfStudy() {
        return yearOfStudy;
    }

    public String getDegreeName() {
        return degreeName;
    }
   
     public String getCourse() {
        return course;
    }

    public int getMark() {
        return mark;
    }

    public int getCredits() {
        return credits;
    }
    
    public String getAcadYear(){
        return acadYear;
    }
    
    public String getTermGPA(){
        return termGPA;
    }
    
    public String getCumulativeGPA(){
       return cumulativeGPA; 
    }
    
    public String getGrade() {
        return grade;
    }

    private String calculateGrade(int mark) {
          if (mark >= 75) {
           return "A+";
        } else if (mark >= 70) {
           return "A-";
        } else if (mark >= 65) {
           return "B+";
        } else if (mark >= 60) {
           return "B-";
        } else if (mark >= 55) {
           return "C+";  
        } else if (mark >= 50) {
           return "C-";  
        } else {
           return "F";
        }
    }
    
    public String getDate(){
        return date;
    }
  
}
